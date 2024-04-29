package com.luis.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/*Anotaciones para seguridad*/
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;
import com.luis.curso.springboot.app.springbootcrud.services.ProductService;
import com.luis.curso.springboot.app.springbootcrud.validation.ProductValidation;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
/*Cors
 * Intercambio de recursos de origen cruzado. Permite a una pagina o recurso
 * externo de nuestro servidor, consumier recursos de nuestro código
 * 
 * Propiedades
 * origins: endpoints a consumir, para consumir varios, se colocan
 * en llaves los endpoins
 * originPatterns: consumir cualquier endpoint con *
*/
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductValidation validation;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Product> listProduct(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    //Path Variable
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> viewOneProduct(@PathVariable Long id){
        Optional<Product> productOptional = this.service.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    /*https://es.stackoverflow.com/questions/375398/diferencia-entre-requestparam-y-requestbody
     * Request Body inyecta un objeto de un cuerpo de request. crea un objeto
     * a partir de un constructor vacio
     * Request Param inyecta datos clave-valor
    */
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveProduct(@Valid  @RequestBody Product product,
    BindingResult result) {
        if(result.hasFieldErrors()){
            return this.validation(result);
        }
        Product productNew = this.service.save(product);
        /*La persistencia se hace cuando el id de un objeto viene null*/
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }
    /*Binding Result
     * Objeto que encapsula todos los errores obtenidos de una validación que se obtienen
     * del request.
     * 
     * 
     * Un objeto Binding Result siempre debe estar a la derecha del objeto que o atributo
     * que se valida en una petición REQUEST
     * 
     * BindingResult in Spring is an object that stores the results of the validation process 
     * of a form object (or any object) and provides error information and validation results. 
     * This is often used in the context of form processing, especially with Spring MVC.
     *
     *The basic tasks of BindingResult are:
     *
     *Validation Error Storage:
     *When object validation fails, BindingResult contains information about validation errors, 
     such as errors related to individual form fields.
     *Decision to Continue or Discontinue Processing:
     *Based on the validation results, you can decide to continue the processing process 
     *(e.g. saving data to the database) or interrupt the process in case of errors.
     *
     * 
     * 
     * Funciona como un validator, pero dentro de un Controller
    */



    /*Valid
     * Marca a un parametro, una propiedad o un retorno de método como que DEBE ser validado.
     * 
     * En este caso, le dice a Spring que cheque el contendio dequest BODY, todo aquello que
     * desee ser validado debe ser anotado con anotaciones de validación de JAKARTA, como
     * en el caso de Product, al verificar la validaación, verificara que los atributos del
     * objeto producto pasado como parámetro sean validos.
     * 
     * Si se quisiera validar el PathVariable, seria posible, pero además de anotar la etiqueta
     * @Valid, debes anotar etiquetas de @Constrait al parametro id para indicar que validar
    */
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, 
    BindingResult result, @PathVariable Long id) {
        /*VALIDANDO POR CLASE ESPECIFICA DE VALIDACION*/
        this.validation.validate(product, result);
        /*Si bien valdiate no retornea un valor, si que, cuando se llama, por detras
         * de escena le pasa las trasas de error a result (siendo una especificacion
         * de BindingResult), para que cuando se verifique si result tiene errores, retorne
         * los errores, sino, actualizar un campo
         *  
        */
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Product> productOptional = this.service.update(id, product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    //Path Variable
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOneProduct(@PathVariable Long id){
        Product product = new Product();
        product.setId(id);
        Optional<Product> productOptional = this.service.delete(product);
        /*Elimina por la comparación de equals*/
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            //Por cada campo del objeto que se valida, obtenemos su error y lo convertimos
            //A una estructura JSON
            /* {
                "name": "error" - Ejemplo
             * }
            */
        });
        return ResponseEntity.badRequest().body(errors);
        //Cuando se evalua un error, es mejor siempre colocar .badStatus() ya
        //que se encarga de crear la request para el error 400 osea un BADREQUEST

        /*Devolvemos una respuesta a la requst, que es un error 400 con una JSON
        * mapeado a trazas de error
        */
    }  
}

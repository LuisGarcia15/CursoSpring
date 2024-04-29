package com.luis.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.app.springbootcrud.entities.User;
import com.luis.curso.springboot.app.springbootcrud.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    //Ruta base
    public List<User> list(){
        return service.findAll();
    }

    @PostMapping
    /*Método para crear Usuarios con el rol Admin. Este verbo solo sera
     * accesible cuando se accese de manera segura
    */
    //@PreAuthorize("hasRole('ADMIN')")
    /*PreAuthorize
     * Permite securizar endpoints sin una clase de autentication
     * a los enspoints que no se securizan no se anotan con nada
     * 
     * hasRole() - para un role
     * hasAnyRole() - para varios roles
     * 
     * SOLO da seguridad pero no obtiene más datos
    */
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
 
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(user));
    }

    @PostMapping("/register")
    /*Método para crear Usuarios con el rol User y aunque se cree un usuario con Admin,
     * solo crea un usuario común
    */
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        user.setAdmin(false);
        return this.create(user, result);
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);

    }  
}

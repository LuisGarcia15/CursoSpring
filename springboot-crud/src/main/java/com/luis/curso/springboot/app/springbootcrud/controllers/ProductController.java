package com.luis.curso.springboot.app.springbootcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;
import com.luis.curso.springboot.app.springbootcrud.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> listProduct(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    //Path Variable
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
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productNew = this.service.save(product);
        /*La persistencia se hace cuando el id de un objeto viene null*/
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = this.service.update(id, product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    //Path Variable
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
    
}

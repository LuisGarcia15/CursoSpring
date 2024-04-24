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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
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

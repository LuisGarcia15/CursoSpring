package com.luis.springboot.error.springbooterror.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.springboot.error.springbooterror.controller.domain.User;
import com.luis.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.luis.springboot.error.springbooterror.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;
    @GetMapping
    public String index(){
        @SuppressWarnings("unused")
        int value = 100 / 0;
        return "OK 200";
    }

    @GetMapping("/dos")
    public String recuerdo(){
        int value = Integer.parseInt("100x");
        System.out.println(value);
        return "OK 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") int id){
        User user = service.findID(id);
        if (user == null) {
            /*Se esta lanzando una exepción con un mensjae personalizado, pero
             * no se esta capturando la exepción en esta línea de código
             */
            throw new UserNotFoundException("Error: El usuario no existe");
        }
        System.out.println(user.getName());
        return this.service.findID(id);
    }
}

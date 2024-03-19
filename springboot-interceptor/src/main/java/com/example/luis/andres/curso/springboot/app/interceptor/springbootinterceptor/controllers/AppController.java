package com.example.luis.andres.curso.springboot.app.interceptor.springbootinterceptor.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/foo")
    public Map<String,String> foo() {
        return Collections.singletonMap("message", "Hadler foo | controlador");
    }

    @GetMapping("/bar")
    public Map<String,String> bar() {
        return Collections.singletonMap("message", "Hadler bar | controlador");
    }

    @GetMapping("/vaz")
    public Map<String,String> vaz() {
        return Collections.singletonMap("message", "Hadler vaz | controlador");
    }
    
}

package com.luis.curso.springboot.app.aop.springbootaop.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.app.aop.springbootaop.services.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GreatingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap(
            "greeting", this.greetingService.sayHello(
                "Luis", "Hola que tal!")));
    }

    @GetMapping("/error")
    public ResponseEntity<?> error() {
        return ResponseEntity.ok(Collections.singletonMap(
            "greeting", this.greetingService.sayError()));
    }
    
}

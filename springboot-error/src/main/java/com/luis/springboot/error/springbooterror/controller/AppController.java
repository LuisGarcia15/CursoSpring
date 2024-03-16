package com.luis.springboot.error.springbooterror.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/app")
    public String index(){
        @SuppressWarnings("unused")
        int value = 100 / 0;
        return "OK 200";
    }

    @GetMapping("/app2")
    public String recuerdo(){
        int value = Integer.parseInt("100x");
        System.out.println(value);
        return "OK 200";
    }
}

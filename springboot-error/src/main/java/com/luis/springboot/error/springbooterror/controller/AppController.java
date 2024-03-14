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
}

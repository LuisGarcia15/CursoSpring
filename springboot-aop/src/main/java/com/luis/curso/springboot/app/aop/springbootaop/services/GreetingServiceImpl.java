package com.luis.curso.springboot.app.aop.springbootaop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService{

    @Override
    public String sayHello(String person, String phrase) {
        String greeting = phrase + " " + person;
        System.out.println(greeting);
        return greeting;    
    }

    @Override
    public String sayError() {
        throw new RuntimeException("*** Error intencional ***");
    }

}

package com.luis.springboot.error.springbooterror.configurations;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luis.springboot.error.springbooterror.controller.domain.Role;
import com.luis.springboot.error.springbooterror.controller.domain.User;

@Configuration
public class AppConfiguration {

    @Bean("registersUsers")
    public ArrayList<User> data(){
        ArrayList<User> users = new ArrayList<>(); 
        users.add(new User(1, "Luis", "Garcia", new Role("Doctor")));
        users.add(new User(2, "Pepe", "Navia", new Role("Veterinario")));
        users.add(new User(3, "Alan", "Lara", new Role("Dev")));
        users.add(new User(4, "Mario", "Santillan", new Role("Inge")));
        users.add(new User(5, "Alberto", "Gonzales",new Role("Maestro")));
        return users;
    }
}

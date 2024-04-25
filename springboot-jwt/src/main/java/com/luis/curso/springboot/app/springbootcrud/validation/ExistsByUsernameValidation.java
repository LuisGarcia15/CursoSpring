package com.luis.curso.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luis.curso.springboot.app.springbootcrud.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
//Es un componente para inyectar la validacion
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{
    /*ConstraintValidator
     * Define la logica para un constraint existente en el Objeto A de un tipo T
    */
    @Autowired
    private UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        System.out.println(this.service.getClass());
        return !service.existsByUsername(username);
    }

}

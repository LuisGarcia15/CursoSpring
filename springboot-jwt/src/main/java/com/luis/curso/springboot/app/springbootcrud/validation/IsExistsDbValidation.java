package com.luis.curso.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luis.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{
    
    @Autowired
    private ProductService service;

    @Override
    //Encargado de validar
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //Verifica con la Base de datos si el campo existe en la DB
        if(this.service == null) {
            return true;
        }
    return !this.service.existsBySku(value);
    }
    
}

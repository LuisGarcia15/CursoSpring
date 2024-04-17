package com.luis.curso.springboot.app.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsExistsDbValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDb {
    String message() default "Ya existe en la DB!!!";
    //Son atributos que tienen todos las anotaciones de validacion
    //Por ende solo se necesita crear varios RequiredValidation para
    //Evaluar el tipo de campo distinto
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };    
}

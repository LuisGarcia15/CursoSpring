package com.luis.curso.springboot.app.springbootcrud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired,String>{

     /*ConstraintValidator
     * Define la lógica para validar un constraint (clase que por anotaciones
     * valida si un campo esta correcto) para un objeto dado. El srgundo parámetro
     * será lo que evalue esta clase.
     * 
     * Se enlaza la clase RequiredValidation con el constraint isRequired por la
     * interfaz ConstraintValidator, donde el primer párametro es la clase
     * Constraint a evaluar
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !(value.isEmpty()) && !(value.isBlank())) {
            return true;
        }else{
            return false;
        }
    }
    
}

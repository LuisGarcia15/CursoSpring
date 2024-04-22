package com.luis.curso.springboot.app.springbootcrud.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;

@Component
/*Clase que se encarga de validar atributos de un objeto
 * 
 * Validando inyectando un objeto de una clase especifica para validacion
*/
public class ProductValidation implements Validator{

    @Override
    public boolean supports(@SuppressWarnings("null") Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
        //Verifica si una clase pasada como parametro es propia de
        //una clase especifivamente de la clase Product
    }

    @SuppressWarnings("null")
    @Override
    /*target: Es el objeto que se valida en un cotroller
     * errors: Es el binding result, contiene todos los errores
     * resultates de una validación ineficaz
    */
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        /*ValidationUtils
         * Se encarga de invoccar a un Validator y rechazar aquellos
         * campos que son vacios.
         * 
         * EL errorCode es el atributo que se encuentra en la clase(tarjet)
         * y la anotación de validación que tiene el atributo.
         * PUEDE SER NULO, de ser asi,  colocamos un mensaje default para 
         * cunado su la validación sea rechazada
         * 
         * el field es el nombre del atributo que se validara
         * 
         * Valida sin if
        */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", 
        "NotEmpty.product.name");
        //Validación con if
        if(product.getDescription() == null || product.getDescription().isEmpty()){
        errors.rejectValue("description", "400","REQUERIDO !!DESCRIPTION | D");
        //Registra un campo para el atributo de la clase llamado igual en caso una
        //Validacion sea incapaz de reconocerlo
        }
        if(product.getPrice() == null){
            errors.rejectValue("price", null,"REQUERIDO !!DESCRIPTION P");
        }
    }

}

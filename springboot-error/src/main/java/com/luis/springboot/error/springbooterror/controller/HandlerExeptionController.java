package com.luis.springboot.error.springbooterror.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/*Permite mapear exepciones, a diferencia de un RestController que esta mapeado
 * a una URL, se mapea a una exepcion especifica.
 * 
 * Es una epecialización de un componente @Component que permite caputar
 * exepciones y traducirlas a respuestas HTTP.
 * 
 * 
 */
public class HandlerExeptionController {

    @ExceptionHandler(ArithmeticException.class)
    /*Anotación que permite mapear exepciones, cuando se ejecute una exepción, este
     * controlador, si esta mapeada, se ejecutará. Mapea las clases de los errores.
     * 
     * En caso de que existan más de una exepción que maneje la anotación, se colocan
     * en llaves
     * 
     * Como @RestControllerAdvice es un componente de Spring, los métodos anotados con
     * @ExeptionHandler pueden ser usados de manera global.
    */
    public ResponseEntity<?> divisionByZero(Exception ex){
        /*Acepta como parámetro la exepción que se captura */
        return ResponseEntity.internalServerError().body("Error: División por cero");
        /*ResponseEntity representa toda la respuesta HTTP, incluyendo código de estatus,
         *Headers y Body, por ello, podemos configurar por complero una respuesta HTTP con
         esta instancia / Clase
        */
    }
}

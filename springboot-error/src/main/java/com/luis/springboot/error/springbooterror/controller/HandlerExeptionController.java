package com.luis.springboot.error.springbooterror.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.luis.springboot.error.springbooterror.models.ErrorClass;

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
    public ResponseEntity<ErrorClass> divisionByZero(Exception ex){
        /*Acepta como parámetro la exepción que se captura */
        ErrorClass error = new ErrorClass();
        error.setDate(new Date());
        error.setError("Error: División por cero");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.internalServerError().body(error);
        /*ResponseEntity representa toda la respuesta HTTP, incluyendo código de estatus,
         *Headers y Body, por ello, podemos configurar por completo una respuesta HTTP con
         esta instancia / Clase
        */

        /*Segunda opción para retornar una respuesta del request
         * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value).body(error);
         */
    }
    /*No se esta usando el DTO de errores */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String,?>> notFoundEx(NoHandlerFoundException ex){
    Map<String,Object> error = new HashMap<>();
    error.put("date",new Date()); 
    error.put("error", "API Rest no encontrada");
    error.put("message", ex.getMessage());
    error.put("code status", HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(404).body(error);
    /*Con el método status() estamos enviando el códgio de status a la
     * respuesta del request
     */
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    /*Envia el codigo de estatus en la respuesta hacia el request ya que
     * no la enviamos junto en el return, en este método solo estamos 
     * enviando lo que tendra el body
    */
    public Map<String, Object> numberFormatExcep(Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put("date",new Date()); 
        error.put("error", "No es posible convertir el valor a int");
        error.put("message", ex.getMessage());
        error.put("code status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
        //Error sera algo como parámetro del body
    }
}

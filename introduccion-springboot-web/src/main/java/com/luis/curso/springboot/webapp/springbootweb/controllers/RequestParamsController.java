package com.luis.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.webapp.springbootweb.dto.ParamDTO;
import com.luis.curso.springboot.webapp.springbootweb.dto.ParamMixDTO;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDTO foo(@RequestParam (required = false, defaultValue = "Sin parametro",
    name = "mensaje") String message){
        
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setMessage(message);
        return paramDTO;
    }
    /*RequestParam
     * Permite obtener parámtetros de una solicitud HTTP y enviarlos a 
     * la aplicación
     * 
     * Anotación utilizada para extraer parámetros de una solicitud HTTP y
     * vincular esos parámetros a los parámetros del método del controlador.
     * 
     * De manera manual, en el endpoint se colca
     * ?[nombreDelParámetro]=valorDelParámetro
     * 
     * El valor name permite llamar bajo un nombre determinado al parámetro
     * de la solicitud, aunque por conveniencia, deberia llamarse igual que
     * la variable definida para el parámetro de la solicitud (en este caso
     * message)
     * 
     * La anotación convierte automaticamente el parámetro URL al tipo
     * declarado, si son tipos de datos diferentes, se debe manejar
     * la exepción (No es con un try catch ya que es a nivel de anotaciones
     * no a nivel de códgio java)
     * 
     * Envia parametros atraves del query de la ruta endpoint (Parámetros del
     * request). Permite el envío de parámetros desde un controlador hasta
     * la aplicación.
    */

    @GetMapping("/bar")
    public ParamMixDTO bar(@RequestParam String text, @RequestParam Integer code) {
        ParamMixDTO params = new ParamMixDTO();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }
    /*Request Param no admite parametros como instancias u objetos, de ser
     * necesario tranferir uno, se usa un formato JSON. Podemos separar los
     * parámetros con & en la url
    */

    @GetMapping("/request")
    public ParamMixDTO request(HttpServletRequest request) {
        Integer code;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {
            code = 0;
        }
        ParamMixDTO params = new ParamMixDTO();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }

    /* Con un objeto HttpServletRequest podemos obtener los 
     * parámetros asociados a la url al igual que @requestparam
     * solo que al declarar un objeto que contendra la información
     * de la url, debemos transformar los parametros al
     * tipo declarado de fotma manual. Este objeto almacena las 
     * características de una petición HTTP, por eso podemos
     * obtener los parámetros
    */
}

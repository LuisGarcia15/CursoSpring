package com.luis.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/redirect")
    public String home(){
        return "redirect:/list";
        /*redirect:/ 
         * Permite redireccionar a vistas de la ruta
         * pasada como parametro. No se usa en peticiones
         * REST. Los parámetros del request actual se pierden
         * para llegar a la ruta /list. 
         * REDIRIGE, Reinicia el request y refresca la paágina
         * haciendo que los parámetros antes de refrescar la página
         * se pierdan, genera una nueva
         * petición a una ruta url dentro del proyecto dentro
         * del mismo controlador o de otro controlador.
         * Cambia la petición
         * 
         */
    }

    @GetMapping({"","/","/home"})
    /*Permite enviar una peticion a cualquiera de las rutas
     * dadas
     */
    public String out(){
        return "forward:/details";
        /*forward:/ 
         * No se usa en peticiones REST
         * Los parámetros del request actual NO se pierden
         * para llegar a la ruta /list. NO cambia de ruta 
         * 
         * Despacha otra accion del controlador sin perder los
         * parámetros del request actual. No cambia la petición
         * 
         */
    }
}

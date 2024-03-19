package com.example.luis.andres.curso.springboot.app.interceptor.springbootinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
    /*WebMvcConfigurer
    Esta interfaz proporciona métodos de configuración que permiten personalizar la 
    configuración del entorno de Spring MVC en una aplicación web*/

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    /*Método que agrega un ciclo de vida MVC para pre o post procesar
     * invocaciones de método y recursos de manejo de request
    */
    @SuppressWarnings("null")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns(
            "/app/bar", "/app/foo");
        /*La implementación del método addInterceptor de la interfaz 
         * WebMvcConfigurer es opcional cuando implementas la interfaz
         * pero forzosa para agregar un interceptor.
         * 
         * el método .addPathPartterns() permite agregar el endpoint o
         * los endpoints (separados por comas) que serán afectador por 
         * los interceptores
         * 
         * registry.addInterceptors().excludePathPatterns()
         *Es el contrario del definido en el código. excluye el endpoint
         * o los endpoints (separados por comas) que serán afectados por los
         * interceptores
         * 
         * /app/**
         * Permite ejecutar o excluir los interceptores en cualquier endpoint del
         * controlador con @RequestMapping("/app")
        */
    }

    
}

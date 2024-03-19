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
        registry.addInterceptor(timeInterceptor);
    }

    
}

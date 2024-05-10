package com.luis.springboot.conexion.react.springbootreactapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luis.springboot.conexion.react.springbootreactapp.entities.Product;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer{
    /*RepositoryRestConfigurer
     * Componente para configurar y customizar el modulo Spring Data Rest
     * Modulo que con CrudRepository se encarga de las operaciones básicas
     * de persistencia automaticamente sin la implementacion necesaria
    */

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        /*Método para configurar las opciones de Spring Data REST, en este caso
         * lo usamos para que, al obtener un get de un registro, obtengamos
         * tambien el id, por defecto no se muestra el id
        */
        config.exposeIdsFor(Product.class);
    }

    
}

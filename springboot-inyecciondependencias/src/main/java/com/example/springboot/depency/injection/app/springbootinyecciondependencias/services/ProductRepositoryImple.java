package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepository;

@RequestScope
@Repository("productList")
/* @Repository
Estereotipo de un componente, se indica que es un
 * repository y no solo un componente genérico.
 * 
 * Es una anotación que se coloca a una clase DAO
 * (Data Access Object), es una clase que actua como
 * mecanismo de encapsulamiento para los datos
 * necesarios de la aplicación y sera la clase con
 * esta anotación la encargada de entablar una relación
 * con la estructura de almacenamiento de datos, siendo
 * desde una lista, pasando por una API o texto plano
 * hasta una Base de datos.
 * 
 * Un componente especifico de acceso a datos. Implementa
 * un patrón de diseño DAO.
 * 
 * Un conponente en Spring puede ser identificado por su nombre
 * de clase, iniciando con la primera palabra en minúscula, este
 * identificador puede cambiar al darle como parámetro a la
 * anotación @Component o sus derivados en nombre del nuevo
 * indentificador.
 */
public class ProductRepositoryImple implements ProductRepository{
    //Clase que actuará como una base de datos
    //Clase para almacenar datos
    /*Repository se encarga de la conección con los datos
     * en el mundo exterior
     */
    private List<Product> data;

    public ProductRepositoryImple() {
        this.data = Arrays.asList(
        new Product(1, "Memoria RAM", 300.00),
        new Product(2, "CPU Intel Core i9", 400.00),
        new Product(3, "Mouse Rayzer", 100.00),
        new Product(4, "Monitor Samsung", 400.00)
        );
    }

    //Retorna la lista de datos
    @Override
    public List<Product> findAll(){
        return this.data;
    };

    @Override
    public Product findById(int id){
        return this.data.stream().
        filter(p->p.getId() == id).findFirst().orElse(null);
    }

}

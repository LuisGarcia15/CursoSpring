package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.Arrays;
import java.util.List;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;

public class ProductRepository {
    //Clase que actuará como una base de datos
    //Clase para almacenar datos
    /*Repository se encarga de la conección con los datos
     * en el mundo exterior
     */
    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(
        new Product(1, "Memoria RAM", 300.00),
        new Product(2, "CPU Intel Core i9", 400.00),
        new Product(3, "Mouse Rayzer", 100.00),
        new Product(4, "Monitor Samsung", 400.00)
        );
    }

    //Retorna la lista de datos
    public List<Product> findAll(){
        return this.data;
    };

    public Product findByID(int iD){
        return this.data.stream().
        filter(p->p.getId() == iD).findFirst().orElse(null);
    }

    

}

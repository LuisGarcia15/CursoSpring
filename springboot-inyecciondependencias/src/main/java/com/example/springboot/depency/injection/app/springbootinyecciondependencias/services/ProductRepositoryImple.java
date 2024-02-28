package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepository;

@Component
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

package com.luis.curso.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

    boolean existsBySku(String sku);
    //Método basado en el nombre
}

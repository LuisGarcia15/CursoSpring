package com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories;

import java.util.List;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;

public interface ProductRepository {
    
    List<Product> findAll();

    Product findById(int id);
}

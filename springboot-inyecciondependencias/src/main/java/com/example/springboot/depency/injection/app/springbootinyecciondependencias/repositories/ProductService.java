package com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories;

import java.util.List;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;

public interface ProductService {
    /*Permite realizar Generalidades en los service y 
     * repositories gracias a métodos genéricos los cuales
     * pueden ser implementados por cualquier clase
     * Aunque van dirigido a services y repositories
     */

    List<Product> findAll();

    Product findById(int id);
}

package com.luis.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.app.springbootcrud.entities.Product;
import com.luis.curso.springboot.app.springbootcrud.repositories.ProductRepository;


@Service
//Se encarga de la lógica de negocio al trabajar
//al obtener los datos de la DB
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repository;
    //Son métodos transaccionales, por lo que se conectaran
    //Directamente con la DB
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List)this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Product save(Product product) {
       return this.repository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = this.repository.findById(id); 
        if(productOptional.isPresent()) {
            Product productNew = productOptional.orElseThrow();
            productNew.setName(product.getName());
            productNew.setPrice(product.getPrice());
            productNew.setDescription(product.getDescription());
            return Optional.of(this.repository.save(productNew));
        }else{
        return productOptional;
        }
    }

    @Override
    public Optional<Product> delete(Product product) {
        Optional<Product> productOptional = this.repository.findById(product.getId()); 
        productOptional.ifPresent(prod -> {
            this.repository.delete(prod);
        });
        return productOptional;
    }
}

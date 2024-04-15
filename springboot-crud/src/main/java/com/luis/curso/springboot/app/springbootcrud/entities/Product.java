package com.luis.curso.springboot.app.springbootcrud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{NotEmpty.product.name}") //Personalizando menajes de error
    //Valida que un campo no sea vacio
    @Size(min = 3, max = 30)
    //Valida la longitud mínma y máxima
    private String name;
    //@Pattern: Valida con respecto a una Expresion Regular
    @Min(0)
    //Valida que el menor valor posible sea n valor
    @NotNull(message = "{NotNull.product.price}")
    //Valida que un objeto NO sea nulo
    private Double price;
    @NotBlank(message = "{NotBlank.product.description}")
    //Valida que un campo no sea vacio
    private String description;

    public Product() {
    }
    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "}";
    }
    
}

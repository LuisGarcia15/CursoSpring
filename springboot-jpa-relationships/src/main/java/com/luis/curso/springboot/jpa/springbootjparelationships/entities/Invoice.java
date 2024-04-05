package com.luis.curso.springboot.jpa.springbootjparelationships.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double total;
    @ManyToAny
    /*ManyToAny
     * Anotación que permite relacionar entidades cual relación
     * de tablas en SQL. El primer valor es el que se refiere a
     * la clase actual, el segundo valor es el referente a la
     * clase con la que se relacion.
     * 
     * Ejemplo
     * Many: Invoices
     * to
     * One: Client
     * Muchas facturas son las que tiene un cliente
     * 
     * Por convención, la llave foranea tendra el nombre del
     * atributo y se le concatena "_id"
     * Ejemplo
     * client_id
    */
    private Client client;
    
    public Invoice() {
    }

    public Invoice(Long id, String description, Double total) {
        this.id = id;
        this.description = description;
        this.total = total;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "{id=" + id + ", description=" + description + ", total=" + total + "}";
    }
}

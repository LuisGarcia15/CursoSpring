package com.luis.curso.springboot.jpa.springbootjparelationships.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isPremium;
    private Integer points;
    @OneToOne
    /*OneToOne
     * Relacion Uno a Uno
     * Un cliente tiene un detalle de su cuenta y un 
     * detalle de cuenta tiene un solo cliente
     * 
     * Por defecto donde este la llave es el atributo de llave foranea,
     * tendrá su tabla la llave foranea
     * 
     * Por defecto la columna de llave foranea se llamara
     * [nombreDeAtributo] + _id
    */
    @JoinColumn(name = "id_client")
    //Dueña de la relación
    private Client client;

    public ClientDetails() {
    }
    public ClientDetails(boolean isPremium, Integer points) {
        this.isPremium = isPremium;
        this.points = points;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isPremium() {
        return isPremium;
    }
    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }   
    @Override
    public String toString() {
        return "{id=" + id + ", isPremium=" + isPremium + ", points=" + points + "}";
    }
}

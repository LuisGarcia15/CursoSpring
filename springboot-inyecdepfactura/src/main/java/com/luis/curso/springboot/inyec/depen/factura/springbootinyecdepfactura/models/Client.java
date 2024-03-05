package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Value("${client.name}")
    //Value solo puede ser usado en Componentes
    private String name;
    @Value("${client.lastname}")
    private String lastName;

    public Client() {
    }
    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

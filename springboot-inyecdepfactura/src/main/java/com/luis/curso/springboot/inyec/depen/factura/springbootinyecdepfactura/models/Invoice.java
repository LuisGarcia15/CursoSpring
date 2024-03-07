package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Invoice {
//Clase de factura

    @Autowired
    private Client client;
    /*Es posible anotar desde un inicio con Autowired a la instancia cliente
     * para inyectarlo una vez se cree un objeto de tipo factura pues la clase
     * Cliente es un componente
     */
    @Value("${invoice.description}")
    private String description;
    @Autowired
    private List<Item> items;

    public Invoice() {
    }

    public Invoice(Client client, String description, List<Item> items) {
        this.client = client;
        this.description = description;
        this.items = items;
    }


    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Progamación Declarativa
    public int getTotal(){
        /* .map en stream permite modificar el flujo de los datos, 
         * en este caso de trabajar con objetos Item, trabajamos con
         * el importe de esos objetos ITEM. Convertimos de instancia
         * ITEM a primitivo int
        */
        int total;
        total = items.stream().map(item -> item.getImport())
        .reduce(0, (sum, importe) -> sum + importe);
        return total;
    }

    /*Programación Imperativa
    public int getTotal(){
        int total = 0;
        for (Item item : items) {
            total += item.getImport();
        }
        return total;
    }
    */
}

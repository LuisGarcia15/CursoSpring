package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
/*No olvidar que un componente que se encuentra en el contenedor de Spring
 * Component, Repository, Service tienen un estado Singlentton, por lo que un
 * objet instanciado de una clase con alguna de esas anotaciones es compartida
 * por todo la aplicación
 */
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
    /*@Autowired
     * Anotación para indicar que va a inyectar una instancia del contenedor
     * de Spring
     * @Qualifier
     * Aanotación para indicar que Bean o Clase del contenedor de Spring se
     * va a inyectar
     */
    @Qualifier("itemsToys")
    private List<Item> items;

    /*Por convención, luego de los atributos y antes de los métodos (Sin incluir
     * los constructores), se colocan aquellos métodos del ciclo de vida de la clase 
     * @PostConstruct o @PreDestroy
     */
   
    /*@PostConstruct
     * Permite ejecutar alguna lógica luego de la creación de la instancia Singlentton
     * (luego de la llamada al constructor), en la primera etapa de vida de la instancia 
     * Singlentton
     */
    @PostConstruct
    public void init(){
        System.out.println("Se crea la instancia Singlentton");
        /*La diferencia entre colocar estas intrucciones en el constructor o
         * con la anotación PostConstruct, es que en el momento del consctructor,
         * los atributos la instancia son nulos. por el contrario, al colcar
         * lógica en un método con PostConstruct,todos los atrivutos de la instancia
         * estan inicializados con los valores previstos
         */
    }

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
        System.out.println(this.items);
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

    @Override
    public String toString() {
        return "Invoice [client=" + client + ", description=" + description + ", items=" + items + "]";
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

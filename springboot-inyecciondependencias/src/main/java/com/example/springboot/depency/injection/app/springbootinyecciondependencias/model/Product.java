package com.example.springboot.depency.injection.app.springbootinyecciondependencias.model;

public class Product implements Cloneable{
    /*Implementa la inteerfaz Clonable para permitir que la clase
     * clone una instancia de la misma
     */
    
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.id, this.name, this.price);
            /*En caso de que no pueda clonar el objeto, retorna un
             * objeto con los mismos atributos actuales
             */
        }
    }

    
}

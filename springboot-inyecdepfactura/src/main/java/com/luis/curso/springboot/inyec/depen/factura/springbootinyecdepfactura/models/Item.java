package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

public class Item {
    /*Cuando devuelves una instancia, el request devuelve
     * todos los valores de los métodos con nomenclatura get.
     * Si los valores get tienen un parámetro, deben de pasar
     * ese parámetro para que el controller designado lo pueda
     * pasar
     */

    private Product product;
    private Integer quantity;

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Item() {
        
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getImport(){
        return quantity * this.product.getPrice();
    }

    public String prueba(){
        return "Prueba";
    }

    

}

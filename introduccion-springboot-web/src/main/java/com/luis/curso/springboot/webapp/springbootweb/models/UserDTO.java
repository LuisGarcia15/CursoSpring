package com.luis.curso.springboot.webapp.springbootweb.models;

public class UserDTO {
    /*Un DTO tambien es un POJO pero
     * de propocito especifico al solo
     * ser usado para el almacenamiento
     * y transporte de datos. Es necesario
     * Adaptar el DTO apartir de las reglas
     * de negocio con la que se este trabajando
    */
    private String title;
    private User user;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
}

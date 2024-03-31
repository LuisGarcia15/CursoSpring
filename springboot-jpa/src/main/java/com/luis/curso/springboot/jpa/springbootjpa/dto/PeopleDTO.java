package com.luis.curso.springboot.jpa.springbootjpa.dto;

public class PeopleDTO {
    //Clase que funciona entre la tabla y la entidad
    //Clase simplificada de una Entity
    /*A diferencia de una Entity, un DTO no necesita 
     * un constructor vacio por defecto cuando tiene
     * un constructor personalizado
     * 
     * 
     * Incluso, a diferencia de un Entity que JPA instancia
     * el objeto por detras de la app, un DTO vamos a 
     * instanciarlo nosotros mismos
     * 
     * Un DTO no esta en el contenedor de Spring, por lo que
     * para utilizarlo en alguna consulta es necesario
     * utilizar la dirección del paquete completo junto
     * al nombre del DTO
    */
    private String name;
    private String lastname;

    public PeopleDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    @Override
    public String toString() {
        return "PeopleDTO [name=" + name + ", lastname=" + lastname + "]";
    }
}

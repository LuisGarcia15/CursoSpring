package com.luis.curso.springboot.jpa.springbootjparelationships.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    /*OneToMany
     * Una relación de uno a muchos, necesita una lista para guardar
     * los datos en Java, lista que necesita ser inicializada. Por
     * defecto OneToMany crea una tabla intermedia entre clients y addresses
     * por convención se llamara con el nombre de la clase clients un _ y
     * el nombre de la clase addresses dando como resultado: clients_addresses.
     * Por defecto agregara un constraint de unique a la primera columna de
     * la tabla, en caso solo haya datos
     * 
     * cascade: Atributo que actua como CASCADE en SQL, puede definirse
     * para cuando se persiste en la DB, cuando se elimina un elemento
     * o cuando ocurre algo en la DB con .ALL. Esto hace que se elimine
     * la relación entre los cliente-direcciones por lo que quedan los
     * registros sin relación pero existiendo en la DB
     * 
     * orphanRemoval: Atributo que elimina los registros huerfanos que 
     * quedan existiendo al eliminar las relaciones por cascade.
    */
    @JoinTable(name = "tbl_clientes_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), 
    inverseJoinColumns = @JoinColumn(name = "id_direc"), 
    uniqueConstraints = @UniqueConstraint(columnNames = "id_direc"))
    /*JoinTable
     * + Anotación para personalizar la creación de una tabla intemedia que se genera por
     * defecto con @OneToMany.
     * + name: nombre de la tabla
     * + joinColumns: Define el nombre de las columnas que tendran los id´s relacionados,
     * se usa JoinColumn para designar los nombres. Puede repetir datos
     * inverseJoinColumns: Define el nombre de las columnas que tendrán los id's relacionados,
     * no puede repetir datos, por loq ue debemos asignar uns cosntraint de unique
     * + uniqueConstraints: se usa para definir constraints a columnas definidas
     * + UniqueConstraint: se usa para definir el constraint a una columna especifica
    */
    private List<Address> addresses;
    /* @JoinColumn(name = "client_id")
     * Como se menciono, @ManyToOne crea ppor defecto una tabla intermedia entre
     * las tablas que utilizan la relación, pero si por alguna opción, NO queremos
     * que se cree esa tabla intermedia, simplemente en la Entidad que lleve
     * el atributo de la llave foranea, se usará @JoinColumn para definir en 
     * la Entidad de menos cardinalidad la existencia de una columna de foreign key
    */
    
    public Client() {
        this.addresses = new ArrayList<>();
    }

    public Client(String name, String lastname) {
        this();
        /*Llama al constructor vacio, esto es para inicializar 
         * la lista que contendrá la relación @ManyToOne
        */
        this.name = name;
        this.lastname = lastname;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", lastname=" + lastname + ", addresses=" + addresses + "}";
    }

    
}

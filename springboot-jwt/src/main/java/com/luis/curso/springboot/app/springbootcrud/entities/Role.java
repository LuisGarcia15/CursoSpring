package com.luis.curso.springboot.app.springbootcrud.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    //Colocamos el constraint al valor name como Unique
    //Un nombre de rol debe ser unico en todos los registros
    private String name;

    @JsonIgnoreProperties({"roles" , "handler", "hibernateLazyInitializer"})
    /*En una relacion bidireccional, debemos de omitir la visualización de los atributos
     * foraneas de la otra tabla relacionada, ya que sino se genera un bucle
     * 
     * En este ejemplo, se omite la variable roles de usuarios (que es la variable que hace
     * referencia a la relacion bidireccional), esto por que si llamamos a users, obtendria los
     * roles, pero los roles tienen a los usuarios, asi que se genera un bucle y la aplicacion 
     * se corrompe.
     * 
     * Por buenas bracticas, se debe ignorar los campos de la relacion en ambas entidades
    */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    
    public Role() {
        this.users = new ArrayList<>();
    }
    public Role(String name) {
        this.name = name;
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
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + "}";
    }
}

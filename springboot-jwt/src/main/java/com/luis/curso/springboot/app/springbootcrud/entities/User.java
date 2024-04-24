package com.luis.curso.springboot.app.springbootcrud.entities;

import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//Todo acerca de persistencia es propio de Jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    //Colocamos el constraint al valor username como Unique
    //Un username de user debe ser unico en todos los registros
    @NotBlank //Anotaciones para verificar la validaciones con BindingResult
    private String username;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    /* JsonProperty
     *Una contaseña, aun asi sea cifrada, no debe de mostrarse en el JSON, 
     *para ello existe la anotación JsonProperty que permite definir el
     *acceso a datos que seran mostrados en un JSON.
     *
     * Como la contraseña no debe mostrase en el JSON, colocamos su acceso
     * a solo de ESCRITURA, esto es, solo puede mostrar / manipular la contraseña
     * cuando se transforma el JSON en una clase, osea se Deserializa o se escribe
     * la contraseña
    */
    //@JsonIgnore
    /*JsonIgnore
     * Otra alternativa para ignorar campos al serializarlos de una clase a un JSON
     * es ignorarlos, pero CUIDADO, esta anotación ignora el atributo de cualquier
     * utilización que se le de, osea que no se podria usar para crear un Usuario pues
     * no tomaria en cuenta este campo, los ignora de Lectura/Escritura
    */
    private String password;
    @ManyToMany
    /*Esta direccion sera unidireccional, un usuario puede ver
     * sus roles, pero un rol no podra ver a que usuarios pertenecesn
    */
    @JoinTable(name = "users_roles",
    //Foreign key principal pues es unidireccional
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;

    //No es un campo de la BD
    //Solo es un campo que nos ayudará a saber si User es admin dentro
    //de la app de spring

    @Transient
    /*Transient
     * Anotación que le indica a Spring y Spring JPA que este atributo no
     * esta mapeado a una BD, solo es un campo propio de la aplicación de 
     * Spring
    */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAdmin;
    /*Verifica si un usuario es un Admin, si lo es se le asocioa el rol
     * admin
    */

    private boolean enabled;
    /*Valor booleano que permite habilitar o no un usuario
    */
    @PrePersist
    /*Como enabled no puede ser Nulo, utilizamos un método de PrePersist
     * para setear el valor a true antes de persistir un objeto, Es mejor
     * practica hacer esto que darle un valor al atributo cuando se 
     * inicizaliza
    */
    public void prePersist(){
        this.enabled = true;
    }
    public User() {
        this.roles = new ArrayList<>();
    }
    public User(String username, String password, List<Role> roles) {
        this();
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        return result;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "User {id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "}";
    }
}

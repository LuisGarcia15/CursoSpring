package com.luis.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
/*Toda clase que sea de persistencia de JPA o Hibernate
 * debe ser anotada con @Entity para indicar que es una
 * clase de persistencia
*/
@Table(name = "people")
/*Table
 * Anotación que sirve para mapear una clase con una tabla
 * de una DB cuando la tabla se llama diferente a la clase.
 * Se asigna el nombre con la propiedad name
 * 
 * SI la clase se llama igual a la tabla, no es necesaria la
 * anotación
*/
public class People {

    @Id
    /*Id
     * Anotación para especificar el atributo de una entidad que
     * sera la llave primaria. 
     * 
     * El tipo de dato debe ser un ripo primitivo o un tipo envoltorio
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*GeneratedValue
    * Se usa comunmente con la anotación @Id
    *se utiliza para indicar que el valor de esa clave primaria debe ser 
    *generado automáticamente por el proveedor de persistencia (como Hibernate 
    *en el caso de Spring Boot).
    *
    *Se usa para generar valores unicos dentro de las tablas de la DB
    *
    *
    * Se informar a Hibernate que cree un valor unico para el campo mientras
    * perista la entidad en la BD
    *
    * EL parametro GenerationType indica como debe generar el ID de manera automática
    */
    private Long id;
    private String name;
    private String lastname;
    @Column(name = "programming_lenguage")
    /*Column
    * Anotación que sirve para mapear un atributo con una registro de
    * una DB cuando la columna se llama diferente al atributo.
    * Se asigna el nombre con la propiedad name
    * 
    * SI el atributo se llama igual a la tabla, no es necesario la
    *anotación
    */
    private String programmingLenguage;

    @Embedded
    /*Embedded
     * Permite incrustar un objeto de una clase con la anotación @Embeddable
     * para obtener sus atributos(columnas) y métodos genéricos
    */
    private Audit audit = new Audit();

    public People() {
    }
    /*Una clase Entity, si se define un constructor con parametros, por regla
     * debe definirse un constructor vacio explicitamente, pues JPA utilizará 
     * ese contructor vacio para crear la instancia de la entidad. Siempre debe
     * ser público
    */

    /*En resumen, mientras que @PrePersist en Spring JPA se utiliza para ejecutar 
    lógica específica antes de que una entidad sea persistida, la cascada en una 
    base de datos se refiere a la propagación de ciertas operaciones (como 
    inserciones, actualizaciones o eliminaciones) desde una entidad principal a 
    sus entidades relacionadas.*/

    /*@PrePersist
    public void prePersist(){
        System.out.println("****++++ Evento del ciclo de vida de la Entidad People - PrePersist ****++++");
        this.createAt = LocalDateTime.now();
    }*/
    /*Los métodos de ciclo de vida de Jakarta ayudan como @PreCreate o @PreDestroy. Ayudan
     * a ejecutar lógica necesaria para continual con un proceso. En este caso estas anotacions
     * @Pre y @Post ayudan a ejecutar código de ayuda antes de que se persista data en la
     * base de datos (@Pre) y luego de que persista data en la base de datos (@Post)
     * 
     * 
     * Cualquier forma de persistencia tiene su @Pre y su @Post
    */
    /*@PreUpdate
    public void preUpdate(){
        System.out.println("Evento del ciclo de vida de la Entidad People - PreUpdate");
        this.updateAt = LocalDateTime.now();
    }*/

    public People(Long id, String name, String lastName, String programmingLenguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.programmingLenguage = programmingLenguage;
    }

    public People(String name, String lastname) {
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

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getProgrammingLenguage() {
        return programmingLenguage;
    }

    public void setProgrammingLenguage(String programmingLenguage) {
        this.programmingLenguage = programmingLenguage;
    }

    @Override
    public String toString() {
        return "People [id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLenguage="
                + programmingLenguage + ", audit=" + this.audit + "]";
    }

    
    

}

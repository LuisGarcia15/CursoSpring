package com.luis.curso.springboot.jpa.springbootjparelationships.entities;

import java.util.HashSet;
import java.util.Set;

import com.luis.curso.springboot.jpa.springbootjparelationships.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String lastname;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    /*ManyToMany
     * Crea en cascada una tabla intermedia que tendra los id's de
     * la tabla students y courses
    */
    //Cascade depende del modelo ya que si
    //la regla de negocio indica que a partir de
    //los cursos de crean los estudiantes
    //cascade se coloca en Course
    /*No se borra en cascada, ya que si un curso tiene muchos
     * estudiantes, tendriamos que borrar la relación entre esos
     * estudiantes con los cursos primero
     */
    @JoinTable(name = "tbl_std_crs", joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"), 
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
    /*JoinTable
     * JoinTable nos sirve para definir la configuración de una tabla
     * intermedia si la vamos o crear con esa configuración o si ya existe
     * 
     * 
     * name: Identifica la tabla intermedia creada
     * JoinColumns: Identifica las llaves foraneas de la tabla con
     * JoinColumn
     * inverseJoinColumn: Identifica el esbirro de la relación por su llave
     * primari
     * @uniqueConstraints: Identifica el constraint de UNIQUE de las llaves
     * foraneas o ambas llaves foraneas son UNIQUE, con UniqueConstrains
     * 
     * 
     * MAPPEDBY
     * para hacer referencia a la otra entidad relacionada, se coloca en la 
     * Entidad contraria donde se encuentra la anotación @JoinColumn
    */
    private Set<Course> courses;
    //Una relacion de Muchos siempre se maneja con SET
    public Student() {
        this.courses = new HashSet<>();
    }
    
    public Student(String nombre, String lastname) {
        this();
        //Llama por defecto al constructor principal
        this.nombre = nombre;
        this.lastname = lastname;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addSCourse(Course course){
        this.courses.add(course);
        course.getStudents().add(this);
    }
    /*Funciones que agrega un curso desde método de la clase*/
    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getStudents().remove(this);
    }
     /*Funciones que elimina un curso desde método de la clase*/

    @Override
    public String toString() {
        return "{id=" + id + ", nombre=" + nombre + ", lastname=" + lastname + ", courses=" + courses + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (lastname == null) {
            if (other.lastname != null)
                return false;
        } else if (!lastname.equals(other.lastname))
            return false;
        return true;
    }
    
}

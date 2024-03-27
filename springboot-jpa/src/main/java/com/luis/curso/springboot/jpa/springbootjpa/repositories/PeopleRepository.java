package com.luis.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjpa.entities.People;

public interface PeopleRepository extends CrudRepository<People, Long>{
    /*Interface que proporciona métodos de CRUD para operaciones en DB con SQL
     * 
     * 
     * Por defecto al heredar de crudrepository ya es un componente de spring
     * que podemos inyectar y no es necesario colocar la anotacion @Repository
     * para convertirla en un componente
     * 
     *  La interfaz PersonRepository es el repositorio que se utiliza para 
     * interactuar con la tabla de la base de datos correspondiente a la 
     * entidad Person. Long es el tipo de dato de las llaves primarias de la
     * entidad
     * 
     * CrudRepository es una interfaz que hereda sus métodos a otras interfaces,
     * como hereda los métodos no hay una relación de contrato y por ello no se
     * usa implements, sino extends
    */

    /*La razón principal por la que CrudRepository no obliga a implementar sus 
    * métodos es porque Spring Data JPA proporciona la implementación real de estos 
    * métodos en tiempo de ejecución. Cuando extiendes CrudRepository en tu interfaz 
    * de repositorio (por ejemplo, UsuarioRepository extends CrudRepository<Usuario, 
    * Long>), Spring Data JPA se encarga de generar una implementación en tiempo de 
    * ejecución que se adapta a tu entidad y la base de datos subyacente.

    * Este enfoque es posible gracias a la capacidad de Spring Data JPA para generar 
    * consultas dinámicas y realizar operaciones CRUD de manera eficiente basándose 
    * en el nombre de los métodos definidos en tu interfaz de repositorio. 
    * Por ejemplo, si defines un método llamado findByNombre(String nombre) 
    * en tu interfaz de repositorio, Spring Data JPA generará la consulta 
    * correspondiente para buscar usuarios por su nombre en la base de datos. */

    /*JPARepository ayuda al ordenamiento y paginación de registros incluyendo 
     * todos los métodos de CrudRepository
     * 
     * 
     * si necesitas utilizar funcionalidades más avanzadas de JPA, como consultas 
     * personalizadas o manejo de transacciones, JpaRepository puede ser una opción 
     * más adecuada. Sin embargo, si solo necesitas operaciones CRUD básicas, 
     * CrudRepository puede ser suficiente */


    public List<People> findByProgrammingLenguage(String programmingLenguage);
    /*Podemos crear consultas personalizadas a partir de cierta nomenclatura
     * que nos provee Springboot Data JPA, esta nomenclatura se puede revisar 
     * en la documentación de la API de Springboot Data JPA. En este ejemplo,
     * se consulta todos los registros que tengan el valor del parámetro dado.
     * Realiza un where
     * 
     * 
     * Es importante seguir las reglas de nomenclatura establecidas por la API
     * de lo contrario, se genera un error ya que la API buscará y ejecutará
     * consultas personalizadas bajo el nombre de método
    */
    @Query("select p from  People p")
    /* Query
     * Permite escribir un query que el método, al llamarse, realizara
     * pero no con lenguaje SQL, debe ser lenguaje JPASQL, osea el lenguaje
     * de consulta de la API JPA.
     * 
     * Esto se demuestra ya que escribimos la consulta en base a las entidades
     * no a las tablas en mySQL
    */

    /*Se puede escribir cualquier método, anotarlo con la anotación Query
     * y proporcionale una consulta personalizada, así, el query no depende
     * de la nomeclatura del nombre de método sino del Query que se le pase 
     * como argumento
     */
    public List<People> imprimirTodos();

    @Query("select p from  People p where p.programmingLenguage = ?1")
    public List<People> imprimirPorLenguaje(String programmingLenguage);
    /*Se pueden crear tantas consultas y tan complejas como se quiera
     * con la anoación @Query y con el lenguaje JPA SQL
    */
    @Query("select p from  People p where p.programmingLenguage = ?1 and p.name = ?2")
    public List<People> imprimirPorLenguajeNombre(String nombre, String programmingLenguage);

    public List<People> findByProgrammingLenguageAndName(String nombre, String programmingLenguage);

    /*Método que se encarga de devolver solo ciertas columnas de los registros
     * Es una lista que contiene arreglos genéricos
    */
    @Query("select p.name, p.programmingLenguage from People p")
    public List<Object[]> obtenerPeopleData();
    /*Los parametros deben de ir ordenados segun el número que le toque en el query JPASQL*/
    @Query("select p.name, p.programmingLenguage from People p where p.programmingLenguage=?1 and p.name=?2")
    public List<Object[]> obtenerPeopleData(String progammingLenguage, String name);
}

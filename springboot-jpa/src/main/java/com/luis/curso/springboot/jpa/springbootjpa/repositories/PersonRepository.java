package com.luis.curso.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
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

}

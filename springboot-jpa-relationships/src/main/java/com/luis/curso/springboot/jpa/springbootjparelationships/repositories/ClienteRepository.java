package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Client;

public interface ClienteRepository extends CrudRepository<Client,Long>{

    @Query("select c from Client c join fetch c.addresses")
    /*fetch
     * propio de JPA, nos sirve para traer las relaciones de la
     * entidad (como todas las direcciones) sin hacer una consulta
     * por cada direccion, sino que trae el cliente con todas 
     * sus ddirecciones por defecto
     * 
     * Esto ayuda a que no genere error Lazy al hacer una consulta
     * sin conexión
    */
    Optional<Client> findOne(Long id);
}

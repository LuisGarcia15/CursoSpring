package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Client;

public interface ClienteRepository extends CrudRepository<Client,Long>{

    @Query("select c from Client c left join fetch c.addresses where c.id = ?1")
    /*fetch
     * propio de JPA, nos sirve para traer las relaciones de la
     * entidad (como todas las direcciones) sin hacer una consulta
     * por cada direccion, sino que trae el cliente con todas 
     * sus ddirecciones por defecto
     * 
     * Esto ayuda a que no genere error Lazy al hacer una consulta
     * sin conexión
     * 
     * left join: trae todos los clientes que tengan facturas y que no
     * posean facturas
    */
    Optional<Client> findOneAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1")
    Optional<Client> findOneInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses left join fetch c.clientDetails where c.id = ?1")
    Optional<Client> findOne(Long id);
    //Si quieres traer multiples join entre varias tablas, al consulta de arriba es viable
    /*Sin embargo, estos valores no se pueden guardar en Listas, pero pueden
     * cambiarse a estructuras de datos SET(conjunto).
     * 
     * Por lo que los atributos que son Listas de Invoices y Address en la entidad
     * Client, debe ser cambiado a SET. Y agregar nuevos registros por SET
     * 
     * Esto no pasa actualmete por la configuración .properties, pero sino, deberiamos
     * cambiar las estructuras de datos de Client a Set
    */

}

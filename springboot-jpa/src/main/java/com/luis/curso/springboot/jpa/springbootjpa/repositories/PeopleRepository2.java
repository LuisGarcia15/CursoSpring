package com.luis.curso.springboot.jpa.springbootjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjpa.entities.People;

public interface PeopleRepository2 extends CrudRepository<People, Long>{
/*Cuando trabajamos con Spring DATA JPA, es mejor usar la clase Optional
 * para envolver la consulta en un wrapper seguro
*/

@Query("select p from People p where p.id = ?1")
public Optional<People> findOne(Long id);

/*Busca un elemento por coincidencia con la palabra reservada LIKE de SQL
 * mediante una consulta personalizada con la notacion JPAQUERY
*/
@Query("select p from People p where p.name like %?1%")
public Optional<People> findOneLikeName(String name);

/*Busca un elemento por coincidencia con la palabra reservada LIKE de SQL
 * pero la consulta se basa en el nombre del método*/
public Optional<People> findByNameContaining(String name);
}

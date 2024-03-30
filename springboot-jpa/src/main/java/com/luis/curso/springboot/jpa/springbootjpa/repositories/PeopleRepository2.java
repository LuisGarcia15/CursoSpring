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

/*Obtiene un valor de un registro, correpondiento al tipo de dato, si obtenemos
 * un nombre, lo obtenemos por String por ejemplo, un id seria un Integer
*/
@Query("select p.name from People p where p.id = ?1")
public String getNameById(Long id);

@Query("select p.id from People p where p.id = ?1")
public Long getIdById(Long id);

//Utilizando la función de SQL concat() para concatenar campos
// y obtenerlos como un solo valor en Spring, además de obtenerlo
//en su tipo equivalente en JAVA. Varchar = String
@Query("select concat(p.name, ' ', p.lastname) as fullname from People p where p.id = ?1")
public String getFullNameById(Long id);

//Obteniendo todo un registro
@Query("select p.id, p.name, p.lastname, p.programmingLenguage from People p where p.id = ?1")
public Object obtenerFullDataPerson(Long id);
/*Spring Data JPA
 * Para obtener los datos de un registro, lo lógico seria pensar que tenemos que devolver un
 * arreglo, pero la configuración de este modulo, obliga a devolver un objeto y en la implementacion
 * de devolución, castear a un arreglo para poder obtener cada uno de los datos de un registro
 * de manera separada
*/
}



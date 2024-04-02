package com.luis.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjpa.dto.PeopleDTO;
import com.luis.curso.springboot.jpa.springbootjpa.entities.People;

public interface PeopleRepository2 extends CrudRepository<People, Long>{
/*Cuando trabajamos con Spring DATA JPA, es mejorxs usar la clase Optional
 * para envolver la consulta en un wrapper seguro
*/

//UNA CONSULTA JPA ES UN QUERY EN CRUDO

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
@Query("select p, p.programmingLenguage from People p")
public List<Object[]> findAllMixPersonDataList();

/*People() es una variable que instancia por detras JPA, aqui se instancia de forma explicita
 * y lo que hacemos es consultar aquellos registros unicamente por el nombre y apellido (basado
 * en un constructor). cuando la consulta se realice, todos los otros campos exepto nombre
 * y apellido seran nulos
*/
@Query("select new People(p.name, p.lastname) from People p")
public List<People> findAllPersonPersonalized();

/*Similar a  la consulta de arriba, la diferencia es que instancias un objeto DTO para mostrar ciertos
 * campos de un registro. En este caso, al mostrar el nombre y apellido, los demas campos de id y
 * lenguaje de programación no se mostraran como nulos, ni siquiera apareceran en la consulta
*/
@Query("select new com.luis.curso.springboot.jpa.springbootjpa.dto.PeopleDTO(p.name, p.lastname) from People p")
public List<PeopleDTO> findAllPersonPersonalizedDTO();

@Query("Select p.name from People p")
public List<String> findAllNames();

@Query("Select distinct(p.name) from People p")
public List<String> findAllNamesDistinct();

@Query("Select p.programmingLenguage from People p")
public List<String> findAllLenguages();

@Query("Select distinct(p.programmingLenguage) from People p")
public List<String> findAllLenguagesDistinct();

@Query("Select count(distinct(p.programmingLenguage)) from People p")
public Long countAllLenguagesDistinct();

//@Query("select concat(p.name, '', p.lastname) from People p")
@Query("select p.name || ' ' || p.lastname from People p")
public List<String> findFullNameConcatPipe();

@Query("select upper(concat(p.name, ' ', p.lastname)) from People p")
public List<String> findFullNameConcatUpper();

@Query("select lower(concat(p.name, ' ', p.lastname)) from People p")
public List<String> findFullNameConcatLower();

@Query("select lower(concat(p.name, ' ',p.lastname)), upper(p.programmingLenguage) from People p")
public List<Object[]> findFullNameUpperLower();
}



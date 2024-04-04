package com.luis.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjpa.entities.People;

public interface PeopleRepository3 extends CrudRepository<People, Long>{

    //En las 4 consultas, el elemento limite (10 o R o param2) no se incluyen en la consulta
    //Solo se incluye desde el elemento inicial (2 o L, param1) hasta antes del elemento limite
    @Query("select p from People p where p.id between 2 and 10")
    public List<People> findAllPeopleBetweenNumeric();

    @Query("select p from People p where p.name between 'L' and 'R'")
    public List<People> findAllPeopleBetweenCharacters();

    @Query("select p from People p where p.name between ?1 and ?2")
    public List<People> findAllPeopleBetweenCharactersParam(String c1, String c2);

    @Query("select p from People p where p.id between ?1 and ?2")
    public List<People> findAllPeopleBetweenNumericParam(Integer id1, Integer id2);

    //Consultas con between pweo basada en el nombre, no personalizada
    public List<People> findByIdBetween(Long id1, Long id2);
    public List<People> findByNameBetween(String char1, String char2);

    //Consultar utilizando Orden By com Between con método personalizado
    //Si se colocan varios parametros, y al final colcamos el modo de orden (asc o desc)
    //todos los campos de ordenaran por el único modo de orden a menos que se coloque otro
    @Query("select p from People p where p.id between ?1 and ?2 order by p.id desc")
    public List<People> findAllPeopleBetweenOrderByNumericParam(Integer id1, Integer id2);
    @Query("select p from People p where p.name between ?1 and ?2 order by p.name desc, p.lastname asc")
    public List<People> findAllPeopleBetweenCharactersOrderByParam(String c1, String c2);

    //Consultar utilizando Orden By con Between con método basado en nombre
    public List<People> findByIdBetweenOrderByIdDesc(Long id1, Long id2);
    public List<People> findByIdBetweenOrderByNameDesc(Long id1, Long id2);
    public List<People> findByIdBetweenOrderByNameDescLastnameAsc(Long id1, Long id2);

    //Método basado en consulta personalizada con Order By
    @Query("select p from People p order by p.name desc")
    public List<People> getAllOrdered();
    //Método basado en el nombre del método con Order By
    public List<People> findAllByOrderByNameDesc();

    // --------- Funciones de agregaciones para contar y mas operaciones en las tablas-------------
    // Las funciones de agregación siempre devulven valores númericos
    @Query("select count(p) from People p")
    public Long totalPeople();
    //El valor de retorno del método dependera del valor de atributo en la tabla
    //Si el id fuese long, el retorno será long. Similar si fuese int
    @Query("select min(p.id) from People p")
    Long minId();
    //min() en sql devuelve el valor mínimo de todos los registros
    @Query("select max(p.id) from People p")
    Long maxId();
    //max() en sql devuelve el valor máximo de todos los registros

    /*length
     * Permite el tamaño de un dato.
     * Consulta que por cada registro obtiene su nombre y la longitud de este
    */
    @Query("select p.name, length(p.name) from People p")
    public List<Object[]> getPeopleNamesLegth();

    @Query("select min(length(p.name)) from People p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from People p")
    public Integer getMaxLengthName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from People p")
    public Object getResumeAggregationFunciton();

    //Subconsultas
    /*Obtiene el nombre más largo. El método devuelve una lista
     * de arreglos por que puede varios nombre tengan la misma longitud
    */
    @Query("select p.name, length(p.name) from People p where length(p.name) = (select max(length(p.name)) from People p)")
    public List<Object[]> getLargeNames();

    @Query("select p.name, length(p.name) from People p where length(p.name) = (select min(length(p.name)) from People p)")
    public List<Object[]> getMinNames();

    @Query("select p from People p where p.id = (select max(p.id) from People p)")
    public Optional<People> getLastRegitration();

    @Query("select p from People p where p.id = (select min(p.id) from People p)")
    public Optional<People> getFirstRegitration();
    //Where con IN
    //Busca elementos en un rango de atributos gracias a IN
    @Query("select p from People p where p.id in ?1")
    public List<People> getPeopleByIds(List<Long> ids);
}

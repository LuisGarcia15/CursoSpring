package com.luis.springboot.conexion.react.springbootreactapp.repositories;

import com.luis.springboot.conexion.react.springbootreactapp.entities.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "products")
/*RepositoryRestResource
 * Permite crear un repositorio completo a travez de servicios REST, implementando
 * HATEOAS, las API REST debe proporcionar las informacion necesaria para que el
 * cliente pueda manejar la app, de manera dinamica y automática
 * 
 * Mediante CrudRepository, implementa de forma automatica las operaciones básicas
 * de persistencia. Donde el path es la BD a la cual aplicamos las operaciones de
 * persistencia
 * 
 * Se encarga además de los codigos de estados de los metodos, dando 404 si no existe
 * un registro o 200 si el api dio resultado exitoso
*/
public interface ProductRepository extends CrudRepository<Product, Long>{

}

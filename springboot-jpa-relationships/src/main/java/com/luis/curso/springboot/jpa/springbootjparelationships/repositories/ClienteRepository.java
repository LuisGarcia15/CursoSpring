package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Client;

public interface ClienteRepository extends CrudRepository<Client,Long>{

}

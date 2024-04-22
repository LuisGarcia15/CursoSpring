package com.luis.curso.springboot.app.springbootcrud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.app.springbootcrud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

    //Busca un rol por su nombre
    Optional<Role> findByName(String name);
}

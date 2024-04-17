package com.luis.curso.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.app.springbootcrud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}

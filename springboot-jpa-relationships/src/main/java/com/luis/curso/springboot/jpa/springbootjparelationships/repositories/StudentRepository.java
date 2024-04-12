package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
        
}

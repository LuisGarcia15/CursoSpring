package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.Course;

public interface CoursesRepository extends CrudRepository<Course, Long>{

}

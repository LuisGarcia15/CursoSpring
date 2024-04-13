package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.Course;

public interface CoursesRepository extends CrudRepository<Course, Long>{

    @Query("select c from Course c left join fetch c.students where c.id=?1")
    Optional<Course> findOneWithStudents(Long L);
}

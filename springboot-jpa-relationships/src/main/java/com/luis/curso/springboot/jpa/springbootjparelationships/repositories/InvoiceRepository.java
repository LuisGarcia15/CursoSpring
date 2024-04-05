package com.luis.curso.springboot.jpa.springbootjparelationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice,Long>{

}

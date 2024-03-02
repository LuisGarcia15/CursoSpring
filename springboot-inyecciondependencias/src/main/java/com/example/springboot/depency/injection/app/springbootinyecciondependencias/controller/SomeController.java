package com.example.springboot.depency.injection.app.springbootinyecciondependencias.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductService;

@RestController
/* RestController
Componente más especifico, establece será un componente
de tipo controller y se encargará de manejar peticiones
y respuetsa de tipo HTTP*/
@RequestMapping("/api")
public class SomeController {
    /*Un controlador es en si mismo una clase Singlentton ya que a ella
     * llegan miles de peticiones REST pero no existe un controlador por
     * cada petición
     */

    private ProductService service;
    /*El atributo de service debe ser de la clase, no propio del request,
     * pero debe ser inmutable. Una mejor práctica es injectar a travez de
     * una interfaz, mediante una implementación. La forma en que Spring 
     * lo inyecta, es buscar un bean que implemente la interfaz (en este caso
     * un ProductServiceImple y esta implenta la interfaz ProductService). 
     * Si hay dos o más clases que implementen la interfaz, habrá una 
     * ambiguedad y mostrará un error por esto.
     */
    
    @GetMapping()
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product list(@PathVariable int id){
        return service.findById(id);
    }
}

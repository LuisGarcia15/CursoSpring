package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepository;

@Primary
/* Primary
 * Permite inyectar esta clase en instancias que neceiten inyectar objetos
 * bajo cierta interfaz, dando la preferencia a esta en caso de que existan 
 * dos o más candidatas. Indica cual es la clase principal que se va a 
 * inyectar. Solo puede existir una clase que implemente @Primary
 * 
 * @Autowired siempre enlzaca o inyecta con la clase con la anotación
 * Primary
*/
@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository{
    /*Implementando la interfaz, visualizando como los métodos declarados en una
     * interface, son genéricos
     */

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1,"Switch",600.0));
    }

    @Override
    public Product findById(int id) {
        return new Product(id,"Switch",600.0);
    }
    
}

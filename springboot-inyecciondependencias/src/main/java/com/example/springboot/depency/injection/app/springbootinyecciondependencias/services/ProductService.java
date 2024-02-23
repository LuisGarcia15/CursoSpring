package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;

public class ProductService {
    //Clase que actuará como una base de datos
    //Clase para almacenar datos
    /*a diferencia radica en que un servicio
     * tambien puede obtener datos como una
     * base de datos y además puede trabajar 
     * con esos datos obtenidos o trabajar junto
     * con otros servicios
     * 
     * Service accede a los datos mediante el
     * reposirory. Tiene los mismos métodos que 
     * repository e incluso por convesión, pueden
     * llamarse igual las caracteristicas
     * 
     * Service aplicará lógica de negocio para los
     * datos suministrados, entre el bloque de código
     * de cada método que tenga. Cada método es igual
     * al de la clase Repository, pero entre sus bloques
     * de códgio podemos colocar algún algoritmo. Esto 
     * último lo hace Service y solo service, no es
     * trbajo de otra clase esta clase de transacción.
     * 
     * Maneja las transacciones
    */
    private ProductRepository repository = new ProductRepository();
    //Simula los datos de repository, capa de datos

    public List<Product> findAll(){
        /*No es necesario que el método devuelva el mismo dato
         * que repository, como service realiza su  propio
         * tipo de transacción, devulve lo que necesite devolver
         */
        /*Entre los métodos, podemos agregar lógica
         * para trabajar con los datos
         * 
         * Service trabaja con los datos, con la lógica
         * de negocio. Como en este método al intercambiar
         * el precio de cada elemento
         */
        return repository.findAll().stream().map(p -> {
           Double priceImp = p.getPrice() * 0.16;
           p.setPrice(priceImp);
           return p;
        }).collect(Collectors.toList());
    }

    public Product findById(int iD){
        /*Entre los métodos, podemos agregar lógica
         * para trabajar con los datos
         */
        return repository.findByID(iD);
    }
}

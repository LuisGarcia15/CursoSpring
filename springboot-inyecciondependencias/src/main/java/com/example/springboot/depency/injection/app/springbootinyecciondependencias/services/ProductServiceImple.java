package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductService;

public class ProductServiceImple implements ProductService{
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
    private ProductRepositoryImple repository = new ProductRepositoryImple();
    //Simula los datos de repository, capa de datos

    @Override
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
           Double priceTax = p.getPrice() * 1.25;
           /*Product newProd = new Product(p.getId(), p.getName(), priceTax);
            *Se crea un nuevo producto por cada producto que haya en la 
            * lista original de repository. y se retorna este nuevo producto
            al flujo de MAP, de esta forma no modificas cada peticón el precio
            original y se mantiene inmutable. Realmnete lo que haces es retornar
            una copia de la lista pero con objetos con direccion de memoria
            diferente pero el precio con el impuesto aumentado
            */
            Product newProd = (Product)p.clone();
            /*Principio de inmutabilidad
             * Se crea un nuevo producto, el cual posee las cualidades del objeto
             * de repository actual. Esto con el fin de actualizar su precio a ese
             * clon con uno nuevo con impuestos y no afectar el dato original cada
             * vez que se llame este método y romper con los datos.
             */
            newProd.setPrice(priceTax);
           return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(int iD){
        /*Entre los métodos, podemos agregar lógica
         * para trabajar con los datos
         */
        return repository.findById(iD);
    }
}

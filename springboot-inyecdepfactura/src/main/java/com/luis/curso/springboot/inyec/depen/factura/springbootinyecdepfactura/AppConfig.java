package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Item;
import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Product;

@Configuration
@PropertySource("classpath:dataproperties")
public class AppConfig {
    /*Clase que funciona como clase de configuracion para
     * inyectar variables de configuracion definidas en archivos
     * .properties.
     * 
     * Clase que funciona como clase de naciminetos de beans para
     * inyectarlas en caso de que no puedan ser anotadas con
     * @Component o sus deerivados
     */
    @Bean
     private List<Item> itemsInvoice(){
        /*Una factura incluye una lista de items, cada item tiene
         * una cantidad especifica para el producto
         */
        Product pro1 = new Product("Lego Zelda", 1000);
        Product pro2 = new Product("Lego Mario", 2000);
       return Arrays.asList(new Item(pro1, 3),
        new Item(pro2, 5));
        
     }

}

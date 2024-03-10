package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Item;
import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Product;

@Configuration
@PropertySource(value = "classpath:application.properties")
@PropertySources({@PropertySource(value =  "classpath:data.properties"
, encoding = "UTF-8"), @PropertySource(value = "application.properties")})
public class AppConfig {
    /*Clase que funciona como clase de configuracion para
     * inyectar variables de configuracion definidas en archivos
     * .properties.
     * 
     * Clase que funciona como clase de naciminetos de beans para
     * inyectarlas en caso de que no puedan ser anotadas con
     * @Component o sus deerivados
     */
    
   @Bean("itemsToys")
   public List<Item> itemsInvoiceToys(){
        /*Una factura incluye una lista de items, cada item tiene
         * una cantidad especifica para el producto
         */
        Product pro1 = new Product("Lego Zelda", 1000);
        Product pro2 = new Product("Lego Mario", 2000);
       return Arrays.asList(new Item(pro1, 3),
        new Item(pro2, 5));
        
   }

   @Bean("itemsOffice")
   public List<Item> itemsInvoiceOffice(){
        /*Una factura incluye una lista de items, cada item tiene
         * una cantidad especifica para el producto
         */
        Product pro1 = new Product("Printer HP", 500);
        Product pro2 = new Product("Monitor Asus", 3000);
        Product pro3 = new Product("Mouse Razer", 150);
        Product pro4 = new Product("Keyboard Hyper X", 3000);
       return Arrays.asList(new Item(pro1, 6),
        new Item(pro2, 9), new Item(pro3, 6),
        new Item(pro4, 20));
  }

}

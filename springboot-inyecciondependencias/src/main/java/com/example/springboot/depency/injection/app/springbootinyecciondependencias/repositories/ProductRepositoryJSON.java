package com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJSON implements ProductRepository{
/*Clase que lee un archivo JSON y lo alamcena como objetos de tipo
 * productos en una lista de Java
 */

    //@Value("classpath:product.json")
    //private Resoruce resource;
    /*Es posible inyectar el archivo json para convertirlo en objetos
     * en Java mediante value, dando el path del archivo json.
     * Solo que para esto la clase base debe ser un componente de Spring
     * pues la anotacion @Value solo funciona en el contexto de componente
     * de Spring.
     * 
     * En este caso esta clase no esta directamente en el contenedor de Spring
     * ya que se inyecta desde una clase @Configuration ya que @Configuration
     * tiene la anotacion @Component por lo que esta dentro del contexto de Spring
     * y cualquier Bean definido en su clase estara indirectamente en el 
     * contenedor de Spring
     */

    private List<Product> list;

    /*1da Forma de obtener, apartir de un archivo JSON, donde se lee el archivo JSON
     * de un objeto ClassPathResource que implementa la interfaz Resource
     * donde Resource es una interfaz ancestra de ClasspathResource,
     * siempre y cuando sea del paquete org.springframework.core.io.Resource;
    */
    public ProductRepositoryJSON(){
        Resource resource = new ClassPathResource("product.json");
        /*La clase ClassPathResource proporciona una forma conveniente de acceder a 
        recursos en el classpath, ya sea para leer archivos de configuración, cargar 
        plantillas, o realizar otras operaciones relacionadas con recursos en el 
        classpath. En caso de un archivo JSON permite convertir un archivo en un flujo
        de bytes para que se pueda leer en un programa Java o convertir el archivo
        en un archivo que pueda ser leido por un programa Java*/
        this.readValueJSON(resource);
        
    }   

    /*2da Forma de obtener, apartir de un archivo JSON, donde se lee el archivo JSON
     * de un objeto Resource que es inyectado por @Value. Obtiene el archivo de forma
     * declarativa
    */
    public ProductRepositoryJSON(Resource resource) {
        this.readValueJSON(resource);
    }

    private void readValueJSON(Resource resource){
        ObjectMapper objectMapper = new ObjectMapper();
        /*Permite convertir un input stream o un archivo .json en un objeto de
         * java
         */
        try {
            /*ObjectMapper se utiliza para convertir objetos Java en formato JSON 
            (serialización) y viceversa (deserialización). Permite mapear objetos 
            Java a representaciones JSON y viceversa de manera fácil y 
            eficiente. */
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), 
            Product[].class));
            /*Devuelve un arreglo de objetos de producto y lo convierte en una lista
             * 
             * Excepcion en caso de que no pueda poblar el arreglo de Productos
             * o de que el archivo no existe
             * 
             * Un input stream en java es cualquier cosa que se pueda leer en forma
             * de bytes, un archivo de texto plano puede convertirse en bytes
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*.readvalue
         * Permite obtener el o los objetos de un archivo con .getFile y como segundo
         * parámetro, permite definir de que tipo seran esos objetos, al ser varios, se coloca
         * como si fuera un arreglo, ademas de colocar .class para definir de que tipo de objetos sera
         * ese arreglo
         */
    }

    @Override
    public List<Product> findAll() {
        return this.list;
    }

    @Override
    public Product findById(int id) {
        return list.stream().filter(p -> {
            return p.getId() == (id);
        }).findFirst().orElseThrow();
        /*ElseThrow devuelve el objeto encontrado
         * o la traza del error
         */
    }
    
}

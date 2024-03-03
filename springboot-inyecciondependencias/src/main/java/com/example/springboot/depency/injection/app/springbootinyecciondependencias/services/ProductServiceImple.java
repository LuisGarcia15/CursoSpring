package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.model.Product;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepository;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductService;

@Service
/*Componente más especifico. Se encarga de  estar en contacto con
 * los repositories para obtener datos y procesarlos bajo una 
 * lógica de negocios.
*/
public class ProductServiceImple implements ProductService{
    //Clase que actuará como una clase de lógica de negocio
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

    /*Se esta injectando a ProductService una instanca
     * singlentton del prodcutrepositoryimple
    */

    @Autowired
    private Environment environment;

    @Value("${config.taxes}")
    /*para inyectar propiedades de archivos .properties, es necesario
     * tener creado la clase de configuracion especializada, anotada con
     * @Configuration y @PropertySource o @PropertySources
     */
    private Double tax;
    /*
    @Autowired
    @Qualifier("productFoo")
    Para inyectar la clase deseada cuando existen dos o más clases
    que extendien una interfaz, debe llevar autowired para la inyección
    y Qualifier para indicar que clase se debe inyectarm para no
    causar una ambiguedad
    */
    private ProductRepository repository;
    //Simula los datos de repository, capa de datos
    //Injección de dependencias por clase

    public ProductServiceImple(@Qualifier("productGames") ProductRepository repository) {
        /*@Qualifier
         * Se encarga de inyectar la clase concreta, en caso que existan dos o más
         * clases que implementen una sola interface, cada componente en Spring
         * se identifica por su nombre de clase, para indicar que clase inyecta
         * se usa el nombre de la clase como parámetro y con la primera letra
         * en minúscula
         */
        this.repository = repository;
    }
    /*En un constructor NO se colcoa @Autowired ya que al utilizar
         * un constructor, se inyecta automáticamente la instancia
         * de ser necesario, sin necesidad de @Autowired. Es necesario
         * que se le pase como argumento un objeto de interface, no un
         * objeto de instancia para que la injección de dependencias
         * funcione
         * 
         * Incluso la misma documentación recomienda no utilizar
         * @Autowired en un constructor.
         */

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
           System.out.println(this.environment.getProperty("config.taxes", Double.class));
           /*getProperty de la clase enviroment puede permitir dos parametros, uno sera
           la key de la variable de un archivo .properties definido en la clase especializada
           de configuracion y otro la clase a la cual se casteara esea variable */ 
           Double priceTax = p.getPrice() * this.tax;
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
        return this.repository.findById(iD);
    }

    //@Autowired
    public void setRepository(ProductRepositoryImple repository) {
        this.repository = repository;
        /*Al colocar @Autowired en un setter que instancia un objeto
         * en una clase, se esta injectando esa intancia por medio
         * del método.
         * 
         * Esta inyección solo funciona si la clase del objeto
         * a instanciar tiene una anotación de un componente y el
         * método tiene la anotación @Autowired
         */
    }

    
}

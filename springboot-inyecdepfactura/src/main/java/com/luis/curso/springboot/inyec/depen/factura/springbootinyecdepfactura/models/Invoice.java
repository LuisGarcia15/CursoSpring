package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component //Contexto de un objeto en el contenedor spring
@RequestScope
/*Para recordar, RequestScope hace que los objetos de una clase 
 *sean propios del contexto Request no de un contexto Component.
 *Un RequestScope siempre ve anotado por @Component o derivados

 *Esto hace que cada que se llama al objeto Invoice, Spring crea un proxy
 *que sera manejado por Spring pero hereda la clase original, este proxy
 *genera atributos residuales que no puede devolverse en un JSON al no
 *contar con método get. Spring genera ese proxy para que exista una
 *instancia por cada request
 *
 * Esto se puede soluciona Agregando JsonIgnoreProperties como anotación de la
 * clase donde se genera el proxy.  Estos atributos residuales se colocan como
 paátametro en la anotación mencionada en el parrafo actual 
*/
@JsonIgnoreProperties({"targetSource", "advisors","exposeProxy","proxyTargetClass"})

/*No olvidar que un componente que se encuentra en el contenedor de Spring
 * Component, Repository, Service tienen un estado Singlentton, por lo que un
 * objet instanciado de una clase con alguna de esas anotaciones es compartida
 * por todo la aplicación
 */

 /*
  * @ApplicationScope
  *Muy similar a ser Singlentton, existe una instancia por aplicación, la diferencia
  *radica en que, @ApplicationScope permite, al tener varias aplicaciones desplegadas
  *en el servidor de Tomcar, compartir esa instancia entre las distintas aplicaciones
  *distribuidas en el server. Singlentton por su parte no puede ser distribuido entre
  *aplicaciones ejecutadas en el servidor de Tomcat
  */
public class Invoice {
//Clase de factura

    @Autowired
    private Client client;
    /*Es posible anotar desde un inicio con Autowired a la instancia cliente
     * para inyectarlo una vez se cree un objeto de tipo factura pues la clase
     * Cliente es un componente
     */
    @Value("${invoice.description}")
    private String description;

    @Autowired
    /*@Autowired
     * Anotación para indicar que va a inyectar una instancia del contenedor
     * de Spring
     * @Qualifier
     * Aanotación para indicar que Bean o Clase del contenedor de Spring se
     * va a inyectar
     */
    @Qualifier("itemsToys")
    private List<Item> items;

    /*Las anotaciones que porvengan de jakarta no son propias de Spring, sino que 
     * estan integradas en Spring por jakarta.
     * 
     * Este es el caso de @PostConstruct y @PreDestroy
    */

    /*Por convención, luego de los atributos y antes de los métodos (Sin incluir
     * los constructores), se colocan aquellos métodos del ciclo de vida de la clase 
     * @PostConstruct o @PreDestroy
     */
   
    /*@PostConstruct
     * Permite ejecutar alguna lógica luego de la creación de la instancia Singlentton
     * (luego de la llamada al constructor), es la primera etapa de vida de la instancia 
     * Singlentton
     * 
     * Tambien, los métodos @PostConstruct se ejecutan cuando la App se ejecuta y lleva
     * objetos correspondientes al contenedor por lo que ya se inyectaron y poblaron los
     * atributos del obketo, es por eso que los atributos estan inicializados
     * y los podemos utilizar en un Post Construct.
     * 
     * PostConstruct se ejecuta luego del constructor como su nombre lo dice
     */
    @PostConstruct
    public void init(){
        this.client.setName(this.client.getName().concat(" Prueba"));
        System.out.println
        ("******* \n Se crea y almacena la instancia Singlentton en el contenedor de Spring \n *******");
        /*La diferencia entre colocar estas intrucciones en el constructor o
         * con la anotación PostConstruct, es que en el momento del consctructor,
         * los atributos la instancia son nulos. por el contrario, al colcar
         * lógica en un método con PostConstruct,todos los atributos de la instancia
         * estan inicializados con valores previstos
         */
    }

    /*@PreDestroy
     * Es una anotación para colocar en un método de clase de aquellos componentes que viviran
     * en el contenedor de Spring, y se ejecutará la lógica del método cuando el ciclo de vida
     * del objeto salga del contenedor de Spring.
     * 
     * En este caso, como el objeto de invoice es único para toda la aplicación (Singlentton),
     * solo sale del contenedor una vez la aplición es detenida.
     */
    @PreDestroy
    public void destroy(){
        System.out.println("******* \n" +
                        " Sacando del contenedor (destruyendo) el componente o Bean Invoice \n" +
                        " *******");
    }

    public Invoice() {
    }

    public Invoice(Client client, String description, List<Item> items) {
        this.client = client;
        this.description = description;
        this.items = items;
    }


    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        System.out.println(this.items);
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Progamación Declarativa
    public int getTotal(){
        /* .map en stream permite modificar el flujo de los datos, 
         * en este caso de trabajar con objetos Item, trabajamos con
         * el importe de esos objetos ITEM. Convertimos de instancia
         * ITEM a primitivo int
        */
        int total;
        total = items.stream().map(item -> item.getImport())
        .reduce(0, (sum, importe) -> sum + importe);
        return total;
    }

    @Override
    public String toString() {
        return "Invoice [client=" + client + ", description=" + description + ", items=" + items + "]";
    }

    /*Programación Imperativa
    public int getTotal(){
        int total = 0;
        for (Item item : items) {
            total += item.getImport();
        }
        return total;
    }
    */
}

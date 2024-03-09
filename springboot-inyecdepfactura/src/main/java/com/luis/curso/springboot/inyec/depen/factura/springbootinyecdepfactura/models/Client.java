package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@SessionScope
@JsonIgnoreProperties({"targetSource", "advisors"})
/*SessionScope
 * * Anotación, que permite que una instancia este atada a la sesión
 * HTTP, que NO sea Singlentton y que la instancia sea atómica para cada
 * sesión HTTP, una vez que la sesión haya ocurrido, la instancia no se guarda
 * en memoria como un Singlentton, sino que se elimina para crearse
 * posterormente en otro sesión que lo necesite.
 * 
 * Cada que se cierra el buscador y se abre con el endpoint de SessionScope
 * la sesión HTTP se reinicia a diferencia de RequestScope que la instancia
 * se crea cada que ocurre un request
 * 
 * Esta anotación tambien genera un proxy, una clase administrada por Spring
 * pero que hereda la clase original. Al igual que RequestScope, genera
 * atributos residuales que no puede serializar un JSON y generar error. Una
 * solución es usar JsonIgnoreProperties y como parámetro el nombre de
 * los atributos residuales
 */
public class Client {

    @Value("${client.name}")
    //Value solo puede ser usado en Componentes
    private String name;
    @Value("${client.lastname}")
    private String lastName;

    public Client() {
    }
    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

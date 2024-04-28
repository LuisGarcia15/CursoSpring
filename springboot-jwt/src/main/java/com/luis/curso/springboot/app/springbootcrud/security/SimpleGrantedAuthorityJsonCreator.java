package com.luis.curso.springboot.app.springbootcrud.security;

import com.fasterxml.jackson.annotation.JsonCreator;
/*Biblioteca Jackson
 * Biblioteca más usada para el trabajo con JSON's, pueden ser
 * utilizadas en cualquier app java que trabaje con JSON. NO
 * so propias de Spring
*/
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    /*Authority ya que el Json tiene un atributo llamado authority*/
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}

/*@JsonCreator: Esta anotación se utiliza para marcar un constructor o un método de fábrica 
(factory method) que Jackson debe utilizar para crear instancias de la clase durante la 
deserialización desde JSON.En el código que has proporcionado, @JsonCreator se utiliza para 
marcar el constructor de SimpleGrantedAuthorityJsonCreator. Esto le indica a Jackson que 
durante la deserialización desde JSON, debe utilizar este constructor para crear instancias 
de la clase SimpleGrantedAuthorityJsonCreator, y espera que el valor de la propiedad "authority"
 en el JSON se pase como argumento al constructor.

@JsonProperty: Esta anotación se utiliza para especificar el nombre de la propiedad en el 
JSON que se debe asociar con el campo o parámetro del método durante la serialización y 
deserialización.En el constructor marcado con @JsonCreator, @JsonProperty("authority") 
se utiliza para indicar que el parámetro role del constructor corresponde a la propiedad 
"authority" en el JSON. Durante la deserialización, Jackson buscará una propiedad llamada 
"authority" en el JSON y pasará su valor al constructor como argumento role.*/
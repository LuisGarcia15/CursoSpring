package com.luis.curso.springboot.webapp.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
/*Configuration
 * Permite definir una clase específica para la definición de 
 * archivos .properties
 */

 @PropertySources({
	@PropertySource(value = "classpath:values.properties", 
    encoding = "UTF-8")
    /*El valor encodig con su valor UTF-8 permite definir caracteres
    especiales en los valores que se definan en .properties) */
})
public class ValuesConfigApp {
/*Clase específica para contener todas las definiciones de archivos
 * .properties
 */
}

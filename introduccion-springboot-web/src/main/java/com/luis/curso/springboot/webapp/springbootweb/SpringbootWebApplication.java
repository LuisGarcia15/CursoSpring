package com.luis.curso.springboot.webapp.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@PropertySources({
	@PropertySource(value = "classpath:values.properties")
})*/
/*PropertySource
 * Permite definir un archivo .properties propio para definir,
 * entre otras cosas, configuraciones propias del programador
 * sin contaminar el archivo .properties base de spring.
 * 
 * con el argumento entre parentesis ('classpath:archivoPropio.properties')
 * 
 * Permite definir variables a nivel de 'programa' como las
 * de values.properties
 * 
 * Permite inyectar esas varibles glopales del 'programa' con la anotación
 * @Value
 * 
 * @PropertySources
 * 
 * Permite definir varias definiciones de archivos .properties
 * entre llaves. El argumento entre llaves seran @PropertySource
 * y por cada archivo .properties existirá un @PropertySource
 */
public class SpringbootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebApplication.class, args);
	}

}
//Generar Jar para producción con terminal
//En la carpeta raíz del proyecto
/*./mvnw install
 * Permite empaquetar o generar un jar del proyecto, además de
 * lo instala en el repositorio local de nuestro maven. Esto 
 * último nos permite reutilizar el proyecto aunque un proyecto
 * de Spring no se reutilza, esto sería más si fuese un conjunto
 * de librerías
 * 
 * ./mvnw clean package
 * Comando unicamente para generar o construir el jar
 * Clean permite "limpiar" (eliminar) un jar generado antes
 * si es que existe en la carpeta tarjet
*/

//Ctrl + C: Detiene la ejecución de un jar en terminal

//Generar proyecto desde Maven
/*En la pestaña de Maven, en LifeCyrcle se encontraran todos
 * los scripts para la creación de archivos JAR y con solo un click
 * en package se genera el JAR en target
 */
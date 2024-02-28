package com.example.springboot.depency.injection.app.springbootinyecciondependencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
 * @ComponentScan: Permite a esta anotación buscar todos
 * aquellas clases definidas con @Component para
 * registrarlas en el contenedor y, de no definir un
 * scope contexto para esos componentes, los trata como
 * singlentton o como el contexto de spring lo decida
 * (generalmente como singlentton)
 */
public class SpringbootInyecciondependenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootInyecciondependenciasApplication.class, args);
	}

}

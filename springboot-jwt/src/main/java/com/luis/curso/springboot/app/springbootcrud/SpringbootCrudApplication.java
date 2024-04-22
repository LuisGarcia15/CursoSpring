package com.luis.curso.springboot.app.springbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}
	/* Al usar Spring Security, por defecto se securisa
	 * toda la app, por lo que no se permite crear data
	 * por defecto
	*/
}

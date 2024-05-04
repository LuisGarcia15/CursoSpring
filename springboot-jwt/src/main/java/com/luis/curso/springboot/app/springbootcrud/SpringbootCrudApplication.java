package com.luis.curso.springboot.app.springbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootCrudApplication extends SpringBootServletInitializer{
	/*SpringBootServletInitizalizer
	 * Pemrite configurar un servidor externo al envevido por defecto al que incluye
	 * springboot
	*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}
	/* Al usar Spring Security, por defecto se securisa
	 * toda la app, por lo que no se permite crear data
	 * por defecto
	*/
}

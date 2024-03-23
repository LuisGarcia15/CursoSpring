package com.luis.curso.springboot.app.aop.springbootaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
/*Anotación que activa/permite el soporte de manejo de componentes
 * marcados con @Aspect. No es necesario colocarla, se autoconfigura
 * solo cuando existe un aspecto
*/
@SpringBootApplication
public class SpringbootAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAopApplication.class, args);
	}

}

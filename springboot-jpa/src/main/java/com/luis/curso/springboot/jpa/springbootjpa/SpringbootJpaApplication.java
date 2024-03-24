package com.luis.curso.springboot.jpa.springbootjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{
	/*CommandLineRunner
	 * Permite ejecutar una aplicación de Spring en la terminal
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	/*Permite reaizar una acción cuando la antes de que la aplicación de Spring
	 * se inicie
	*/

}

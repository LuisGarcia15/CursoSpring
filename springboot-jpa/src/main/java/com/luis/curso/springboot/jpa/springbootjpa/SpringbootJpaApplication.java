package com.luis.curso.springboot.jpa.springbootjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luis.curso.springboot.jpa.springbootjpa.entities.Person;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
/*ESTA CLASE en si misma tiene la anotación @Component por lo que tambien
 * es un componente de Spring y dentro podemos inyectar elementos
*/
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;
	/*CommandLineRunner
	 * Permite ejecutar una aplicación de Spring en la terminal
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Como findAll() regresa un Iterable, debemos hacer el cast
		 * a una lista de tipo Person
		*/
		List<Person> people = (List<Person>) this.repository.findAll();
		people.stream().forEach(person -> {
			System.out.println(person);
		});
	}
	/*Permite reaizar una acción cuando la antes de que la aplicación de Spring
	 * se inicie
	*/

}

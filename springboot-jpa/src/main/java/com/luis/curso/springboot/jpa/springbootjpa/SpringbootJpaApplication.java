package com.luis.curso.springboot.jpa.springbootjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luis.curso.springboot.jpa.springbootjpa.entities.People;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository;

@SpringBootApplication
/*ESTA CLASE en si misma tiene la anotación @Component por lo que tambien
 * es un componente de Spring y dentro podemos inyectar elementos
*/
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PeopleRepository repository;
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
		System.out.println("********** Método de CrudRepository ***************");
		List<People> people = (List<People>) this.repository.findAll();
		this.printPeople(people);
		System.out.println("********** Método de CrudRepository Con Nomenclatura ***************");
		people = (List<People>) this.repository.findByProgrammingLenguage("Java");
		this.printPeople(people);
		System.out.println("*********** Método Propio Con @Query **************");
		people = (List<People>) this.repository.imprimirTodos();
		this.printPeople(people);
		System.out.println("*********** Método Propio Con @Query **************");
		people = (List<People>) this.repository.imprimirPorLenguaje("Java");
		this.printPeople(people);
		System.out.println("*********** Método Propio Con @Query **************");
		people = (List<People>) this.repository.imprimirPorLenguajeNombre("Java", "Luis");
		this.printPeople(people);
		System.out.println("*********** Método de CrudRepository Con Nomenclatura **************");
		people = (List<People>) this.repository.findByProgrammingLenguageAndName("Java", "Luis");
		this.printPeople(people);
		System.out.println("*********** Método Propio @Query que obtiene ciertas columnas **************");
		List<Object[]> peopleValues = this.repository.obtenerPeopleData();
		/*Al obtener los campos por separado, tenemos que guardarlos en una
		 * estructura de datos de arreglo para acceder a cada campo
		*/
		peopleValues.stream().forEach(person -> {
			System.out.println(person[0] + " es programador en:" + person[1]);
		});
		System.out.println("*********** Método Propio @Query que obtiene ciertas columnas con where **************");
		peopleValues = this.repository.obtenerPeopleData("Java", "Luis");
		peopleValues.stream().forEach(person -> {
			System.out.println(person[0] + " es programador en:" + person[1]);
		});
	}	
	/*Permite reaizar una acción cuando la antes de que la aplicación de Spring
	 * se inicie
	*/

	private void printPeople(List<People> people){
		people.stream().forEach(person -> {
			System.out.println(person);
		});
	}
}

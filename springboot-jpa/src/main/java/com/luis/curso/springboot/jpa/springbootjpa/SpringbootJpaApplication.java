package com.luis.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.jpa.springbootjpa.entities.People;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository2;

@SpringBootApplication
/*ESTA CLASE en si misma tiene la anotación @Component por lo que tambien
 * es un componente de Spring y dentro podemos inyectar elementos
*/
public class SpringbootJpaApplication implements CommandLineRunner{
	/*CommandLineRunner
	 * Permite ejecutar una aplicación de Spring en la terminal
	 */
	@Autowired
	private PeopleRepository repository;

	@Autowired
	private PeopleRepository2 repository2;

	private Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	/*Permite reaizar una acción cuando la antes de que la aplicación de Spring
	 * se inicie
	*/
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
		System.out.println("*********** Obteniendo un solo elemento por CrudRepository **************");
		this.findOne();
		System.out.println("*********** Obteniendo un solo elemento por una consulta personalizada **************");
		System.out.println(this.repository2.findOne(2L));
		System.out.println("*********** Obteniendo elementos por una consulta personalizada con LIKE **************");
		System.out.println(this.repository2.findOneLikeName("hn"));
		System.out.println("*********** Obteniendo elementos por una consulta LIKE pero basado en el método **************");
		System.out.println(this.repository2.findByNameContaining("le"));
		System.out.println("*********** Creando nuevo registro en la BD con un método propio **************");
		//this.create();
		System.out.println("*********** Actualizando nuevo registro en la BD con un método propio **************");
		//this.update();
		System.out.println("*********** Eliminando nuevo registro en la BD con un método propio **************");
		this.repository.findAll().forEach(p -> System.out.println(p));
		this.delete();
	}	

	private void printPeople(List<People> people){
		people.stream().forEach(person -> {
			System.out.println(person);
		});
	}
	@Transactional
	public void delete(){
		System.out.println("Ingrese el Id de la persona a eliminar:");
		Long id = this.scanner.nextLong();
		this.repository.deleteById(id);
		/*deleteById()
		 * Elimina un registro por ID, de no existir no hace nada
		*/
		this.repository.findAll().forEach(System.out::println);
	}

	@Transactional
	public void update(){
		System.out.println("Ingrese el Id de la persona a editar:");
		Long id;
		id = this.scanner.nextLong();
		Optional<People> optionalPerson = this.repository.findById(id);
		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programación: ");
			String programmingLenguage = this.scanner.next();
			person.setProgrammingLenguage(programmingLenguage);
			People personDB = this.repository.save(person);
			/*save() permite tambien actualizar un elemento en la BD.
			 * save sirve para salvar cualquier cambio en la BD
			*/
			System.out.println(personDB);
		});
	}

	@SuppressWarnings("null")
	@Transactional
	/*Cualquier operacion que modifique la base de datos, un Update,
	 * Delete, Create, etc debe ser anotado con @Transactional
	 * 
	 * Reserva la integridad de la BD pues si ocurre un error en la
	 * transaccion de la base de dato, este se revierte.
	 * 
	 * Puede pensarse como una aspecto, pues intercepta la transacción
	 * si se completa, realiza un commit, de no completarse realiza
	 * un rollback
	*/
	private void create(){
		System.out.println("Ingrese el nombre: ");
		String name = this.scanner.nextLine();
		System.out.println("Ingrese el apellido: ");
		String lastname = this.scanner.nextLine();
		System.out.println("Ingrese el lenguaje de programacion: ");
		String programmingLenguage = this.scanner.nextLine();
		People person = new People(null, name, lastname, programmingLenguage);
		/*El id se coloca como nulo pues Spring se encarga de Autoincrementarlo cada que
		 * se crea un nuevo registro. por ello se coloca null
		*/

		/*Permite salvar un nuevo registro el la DB. save () Regresa el registro que creo, 
		* nunca regresa nulo, por ello podemos guardar el nuevo registro en una nueva
		* variable.
		*/
		People personNew = this.repository.save(person);
		System.out.println(personNew);
		this.repository.findById(personNew.getId()).ifPresent(
			p -> System.out.println(p));
	}

	@Transactional(readOnly = true)
	/*El parametro de readOnly = true se usa cuando el método
	 * solo realiza una consulta a la Base de datos, no realiza
	 * una transacción a la BD
	*/

	/*Obtiene el registro por su id por el método de CrudRepository*/
	private void findOne(){
		People person = null;
		//Manejo seguro de Optional
		Optional<People> optionalPerson = this.repository.findById(1L);
		//isEmpty(): Contrario a isPresent()
		if(optionalPerson.isPresent()){
			person = optionalPerson.get();
			/*Serizaliza el objeto de la tabla a objeto en Java, por lo que
			 * podriamos obtener sus atributos
			 */
		}
		System.out.println(person);
	}
}

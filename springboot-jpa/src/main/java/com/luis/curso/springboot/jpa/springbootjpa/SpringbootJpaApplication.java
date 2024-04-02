package com.luis.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.jpa.springbootjpa.dto.PeopleDTO;
import com.luis.curso.springboot.jpa.springbootjpa.entities.People;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository2;
import com.luis.curso.springboot.jpa.springbootjpa.repositories.PeopleRepository3;

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

	@Autowired
	private PeopleRepository3 repository3;

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
		System.out.println("*********** Método Propio Con @Query imprime todos **************");
		//Selecciona todas las intancias que existan el la tabla people
		//En cada indice esta guardando un registro con la consulta select p from <Entidad> p;
		people = (List<People>) this.repository.imprimirTodos();
		System.out.println(people.get(0));
		//this.printPeople(people);
		System.out.println("*********** Método Propio Con @Query imprime por lenguaje**************");
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
		//this.repository.findAll().forEach(p -> System.out.println(p));
		//this.delete();
		System.out.println("*********** Eliminando nuevo registro en la BD con un método funcional **************");
		//this.repository.findAll().forEach(p -> System.out.println(p));
		//this.deleteCrud();
		System.out.println("*********** Consulta de campos donde se obtiene por tipo de dato mostrando solo ciertos campos **************");
		//this.personilizedQueries();
		System.out.println("*********** Consulta de campos personalizada por id mostrando todos los datos **************");
		//this.personilizedQueries2();
		System.out.println("*********** Consulta unicamente de los nombres existentes **************");
		this.personilizedQueriesDistinct();
		this.personalizedQuerysUpperAndLower();
		this.repository.findAll().forEach(person -> System.out.println(person));
		System.out.println("*********** Consulta de registros con Between entre 2 y 10 **************");
		this.personalizedQuerysBetween();
		System.out.println("*********** Consulta de cuantos registros existen en la tabla con count() **************");
		this.querysFunctionAggregation();
	}	

	@Transactional(readOnly = true)
	public void querysFunctionAggregation(){
		Long count = this.repository3.totalPeople();
		Long min = this.repository3.minId();
		Long max = this.repository3.maxId();
		System.out.println("Existen " + count + " registros de Personas en la tabla");
		System.out.println("*********** Consulta del id minimo con min() **************");
		System.out.println("El id mínimo registrado es: " + min);
		System.out.println("*********** Consulta del id máximo con max() **************");
		System.out.println("El id máximo registrado es: " + max);
	}

	@Transactional(readOnly = true)
	public void personalizedQuerysBetween(){
		List<People> people = this.repository3.findAllPeopleBetweenNumeric();
		people.forEach(person -> {
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between caracteres entre L y R **************");
		people = this.repository3.findAllPeopleBetweenCharacters();
		people.forEach(person -> {
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between entre indices pasados como parametros **************");
		Integer num1 = 4;
		Integer num2 = 12;
		people = this.repository3.findAllPeopleBetweenNumericParam(num1, num2);
		System.out.println("-------- Limite entre " + num1 + " y " + num2 + " --------");
		people.forEach(person -> {
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between entre caracteres pasados como parametros **************");
		String p1 = "A";
		String p2 = "L";
		people = this.repository3.findAllPeopleBetweenCharactersParam(p1,p2);
		System.out.println("-------- Limite entre " + p1 + " y " + p2 + " --------");
		people.forEach(person -> {
			System.out.println(person);
		});

		System.out.println("*********** Consulta de registros con Between entre indices con nombre de método **************");
		Long lo1 = 6L;
		Long lo2 = 15L;
		people = this.repository3.findByIdBetween(lo1, lo2);
		System.out.println("-------- Limite entre " + lo1 + " y " + lo2 + " --------");
		people.forEach(person -> {
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between entre caracteres con nombre de método **************");
		p1 = "A";
		p2 = "N";
		people = this.repository3.findByNameBetween(p1, p2);
		System.out.println("-------- Limite entre " + p1 + " y " + p2 + " --------");
		people.forEach(person -> {
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between y Order By en ID **************");
		people = this.repository3.findAllPeopleBetweenOrderByNumericParam(num1, num2);
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between y Order By en Name **************");
		people = this.repository3.findAllPeopleBetweenCharactersOrderByParam(p1, p2);
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between y Order By en ID con método basado en nombre **************");
		people = this.repository3.findByIdBetweenOrderByIdDesc(lo1, lo2);
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between y Order By en Nombre con método basado en nombre **************");
		people = this.repository3.findByIdBetweenOrderByNameDesc(lo1, lo2);
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Between y Order By en Nombre con método basado en nombre y apellido **************");
		people = this.repository3.findByIdBetweenOrderByNameDescLastnameAsc(lo1, lo2);
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Order By en nombre  **************");
		people = this.repository3.getAllOrdered();
		people.forEach(person ->{
			System.out.println(person);
		});
		System.out.println("*********** Consulta de registros con Order By en nombre basado en el nombre del método**************");
		people = this.repository3.findAllByOrderByNameDesc();
		people.forEach(person ->{
			System.out.println(person);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQuerysUpperAndLower(){
		System.out.println("*********** Consulta concatenada por pipe || de nombre y apellido de todos los registros **************");
		List<String> query = this.repository2.findFullNameConcatPipe();
		query.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("*********** Consulta concatenada de nombre y apellido de todos los registros en Mayúsculas **************");
		query = this.repository2.findFullNameConcatUpper();
		query.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("*********** Consulta concatenada de nombre y apellido de todos los registros en minusculas **************");
		query = this.repository2.findFullNameConcatLower();
		query.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("*********** Consulta concatenada de campos con upper y lower en la misma query **************");
		List<Object[]>query2 = this.repository2.findFullNameUpperLower();
		query2.forEach(reg -> {
			System.out.println(reg[0] + " usa el lenguaje: " + reg[1]);
		});
	}

	@Transactional(readOnly = true)
	public void personilizedQueriesDistinct(){
		List<String> names = this.repository2.findAllNames();
		names.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("*********** Consulta unicamente de los nombres existentes con distinct **************");
		names = this.repository2.findAllNamesDistinct();
		names.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("*********** Consulta unicamente de los lenguajes existentes **************");
		names = this.repository2.findAllLenguages();
		names.forEach(len -> {
			System.out.println(len);
		});
		System.out.println("*********** Consulta unicamente de los lenguajes existentes con distinct **************");
		names = this.repository2.findAllLenguagesDistinct();
		names.forEach(len -> {
			System.out.println(len);
		});
		System.out.println("*********** Contar los regitros distintos de los lenguajes **************");	
		Long count  = this.repository2.countAllLenguagesDistinct();
		System.out.println("EL numero de registros de lenguajes distintos es: " + count);
	}

	private void printPeople(List<People> people){
		people.stream().forEach(person -> {
			System.out.println(person);
		});
	}

	@Transactional(readOnly = true)
	public void personilizedQueries2(){
		System.out.println("Ingresa el ID:");
		Long id = this.scanner.nextLong();
		Object[] personReg = (Object[]) this.repository2.obtenerFullDataPerson(id);
		System.out.println("id = " + personReg[0] +
							"\nnombre: " + personReg[1] +
							"\napellido: " + personReg[2] +
							"\nlenguaje de programación: " + personReg[3]);
		System.out.println("*********** Consulta de registros y su lenguaje de programación **************");
		List<Object[]> peopleRegs = this.repository2.findAllMixPersonDataList();
		peopleRegs.forEach(reg -> {
			System.out.println("Registro completo: " + reg[0] +
								"\n Lenguaje de programación: " + reg[1]);
		}
		);	
		System.out.println("*********** Consulta de registros unicamente por algunos campos **************");	
		List<People> objectsPeople = this.repository2.findAllPersonPersonalized();
		objectsPeople.forEach(reg -> {
			System.out.println(reg);
		});
		System.out.println("*********** Consulta de registros unicamente por algunos campos con Clase DTO **************");	
		List<PeopleDTO> personDTO = this.repository2.findAllPersonPersonalizedDTO();
		personDTO.forEach(reg -> {
			System.out.println(reg);
		});
	}

	@Transactional(readOnly = true)
	/*Obtiene campos en su equvalencia de tipo en Java*/
	public void personilizedQueries(){
		System.out.println("Ingresa el ID:");
		Long id = this.scanner.nextLong();
		String name = this.repository2.getNameById(id);
		Long idObtenido = this.repository2.getIdById(id);
		String fullName = this.repository2.getFullNameById(id);
		System.out.println("El nombre es "+ name + " del clinte con id " + id);
		System.out.println("El id es "+ idObtenido + " del clinte con id " + id);
		System.out.println("El nombre completo del clinete con id: " + id + " es " + fullName);
	}

	@SuppressWarnings("null")
	@Transactional
	public void deleteCrud(){
		System.out.println("Ingrese el Id de la persona a eliminar:");
		Long id = this.scanner.nextLong();
		Optional<People> optionalPerson = this.repository.findById(id);
		optionalPerson.ifPresentOrElse(
			person -> this.repository.delete(person),
			//delete() borra la entidad pasada como parametro
			() -> System.out.println("No se puede eliminar la persona, no existe!"));
		/*deleteById()
		 * Elimina un registro por ID, de no existir no hace nada
		*/
		this.repository.findAll().forEach(System.out::println);
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

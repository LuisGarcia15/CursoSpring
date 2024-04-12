package com.luis.curso.springboot.jpa.springbootjparelationships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Address;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Client;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.ClientDetails;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Invoice;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Student;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.ClientDetailsRepository;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.ClienteRepository;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.CoursesRepository;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.InvoiceRepository;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ClientDetailsRepository detailsRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CoursesRepository coursesRepository;

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.all();
		System.out.println("********** Manejando la relación ManyToOne **********");
		//this.manyToOne();
		System.out.println("********** Manejando la relación ManyToOne insertando la relación en un ID existente**********");
		//this.manyToOneFindByIdClient();
		System.out.println("********** Manejando la relación OneToMany **********");
		//this.oneToMany();
		System.out.println("********** Manejando la relación OneToMany mediante input **********");
		//this.oneToManyInput();
		System.out.println("********** Borrando relación de OneToMany **********");
		//this.removeAddress();
		System.out.println("********** Borrando relación de OneToMany de un ID existente **********");
		//this.removeAddressesInput();
		System.out.println("********** Manejando la relación OneToMany Bidireccional **********");
		//this.oneToManyInvoiceBidirectional();
		System.out.println("********** Manejando la relación OneToMany Bidireccional por ID**********");
		//this.oneToManyInvoiceBidirectionalFindById();
		System.out.println("********** Eliminando la relación OneToMany Bidireccional por ID**********");
		//this.removeInvoiceBidirectionalFindById();
		System.out.println("********** Manejando la relación de OneToOne **********");
		//this.oneToOne();
		System.out.println("********** Manejando la relación de OneToOne Bidireccional **********");
		//this.oneToOneBidireccional();
		System.out.println("********** Manejando la relación de OneToOne Bidireccional por ID**********");
		//this.oneToOneBidireccionalById();
		System.out.println("********** Manejando la relación de ManyToMany **********");
		//this.manyToMany();
		System.out.println("********** Manejando la relación de ManyToMany por medio de ID**********");
		this.manyToManyFindByID();
	}

	@Transactional
	public void manyToManyFindByID(){
		//Unidireccional
		Optional<Student> studentOp1 = this.studentRepository.findById(1L);
		Optional<Student> studentOp2 = this.studentRepository.findById(1L);
		Course course1 = this.coursesRepository.findById(1L).get();
		Course course2 = this.coursesRepository.findById(2L).get();
		Student student1 = studentOp1.get();
		Student student2 = studentOp2.get();

		student1.setCourses(Set.of(course1,course2));
		student2.setCourses(Set.of(course1));
		
		this.studentRepository.saveAll(List.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany(){
		//Unidireccional
		Student student1 = new Student("Link","ChildFairy");
		Student student2 = new Student("Majora","ChildMask");
		Course course1 = new Course("SpringBoot", "Andres");
		Course course2 = new Course("Java", "Andres");
		student1.setCourses(Set.of(course1,course2));
		/*Set.of()
		 * Permite con convertir elementos a Set
		*/
		student2.setCourses(Set.of(course1));
		System.out.println(this.studentRepository.saveAll(List.of(student1,student2)));
		//saveAll()
		/*Guarda un iterable de objetos de Entidades en el objero.
		* Funciona con clase que herede de la interfaz funcional
		* Iterable<T>, por eso se le pasa una Lista
		*/

		/*RECORDEMOS que Student es el dueño de la relación. En su
		 * atributo de "llave foranea" (no la tiene en su tabla pues
		 * esta en la tabla intermedia). Por lo que al persisitir un objeto
		 * Student, si el objeto student tiene cursos, estos los persiste
		 * automáticamente
		*/

	}

	@Transactional
	public void oneToOneBidireccionalById(){
		Optional<Client> clientOptional = this.clientRepository.findById(2L);
		clientOptional.ifPresent(client ->{
			ClientDetails detail = new ClientDetails(true, 1000000);
			client.setClientDetails(detail);
			detail.setClient(client);
			System.out.println(this.clientRepository.save(client));
		});
	}

	@Transactional
	public void oneToOneBidireccional(){
		Client client = new Client("Leon", "Keneddy");
		ClientDetails details = new ClientDetails(true, 1000);
		client.setClientDetails(details);
		/*Se guarda en ambos lados la relación al ser bidireccional*/
		details.setClient(client);
		/*Como se tiene la propiedad cascade, al persisitir un cliente
		 * al que se le añadio un detail, ese detail se almacena
		 * en la DB por defecto
		*/
		System.out.println(this.clientRepository.save(client));
	}

	@Transactional
	public void oneToOne(){
		//Unidireccional de details a client
		Client client = new Client("Leon", "Keneddy");
		this.clientRepository.save(client);
		//Se persiste el cliente pero aun no tiene la relacion de detalle

		ClientDetails details = new ClientDetails(true, 1000);
		/*Luego de persisitr el cliente, debemos agregar su detalle de cuenta.
		Posteriormente se persiste el detalle de la cuenta en el cliente, como
		cliente no tiene una relación bidireccional, solo se persiste el detalle */ 		
		//details.setClient(client);
		System.out.println(this.detailsRepository.save(details));
	}

	public void removeInvoiceBidirectionalFindById(){
		Optional<Client> optionalClient = this.clientRepository.findOne(1L);
		/*Como aqui se obtiene el cliente, no genera un error de conexión pues tenemos
		 * a este momento una conexión correcta
		*/
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Hongo", 80.00);
			Invoice invoice2 = new Invoice("Estrella", 80.00);
			//En este momento el id de invocice1 e invoice2 valen null
			/*Por ello se coloca el equals en la clase POJO Invoice, y que compara
			 * con id, descripcion y total, ya que si solo compará con id, no
			 * identificará el objeto correco
			 */
			client.addInvoice(invoice1).addInvoice(invoice2);
			System.out.println(this.clientRepository.save(client));
		});
		Optional<Client> optionalClient2 = this.clientRepository.findOne(1L);
		optionalClient2.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = this.invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice ->{
				client.getInvoices().remove(invoice);
				/*Como es una relacion bidirecciona, si eliminamos un factura de
				 * client, debemos eliminar de esa factura al cliente que tenia
				 * asignado
				 * 
				 * Por detras, se compara la factura con alguna factura existente gracias
				 * a equals() de la clase Invoice() en el método remove(). equals() debe
				 * estar en Invoice pues compara por facturas en el remove que existan
				 * en el cliente
				*/
				invoice.setClient(null);
				/*Al persisitir el cliente, se van a eliminar las relaciones con
				 * invoices gracias a la propiedad cascade en la llave foranea
				 * de Client
				*/
				System.out.println("+++++ Resultado +++++");
				System.out.println(this.clientRepository.save(client));
			});
		});
	}

	public void oneToManyInvoiceBidirectionalFindById(){
		Optional<Client> optionalClient = this.clientRepository.findOneInvoices(1L);
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Hongo", 80.00);
			Invoice invoice2 = new Invoice("Estrella", 80.00);
			client.addInvoice(invoice1).addInvoice(invoice2);
			invoice1.setClient(client);
			invoice2.setClient(client);
			System.out.println(this.clientRepository.save(client));
		});
	}

	public void oneToManyInvoiceBidirectional(){
		Client client = new Client("Link", "Child Fairy");

		Invoice invoice1 = new Invoice("Arco", 80.00);
		Invoice invoice2 = new Invoice("Escudo", 80.00);

		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);
		/*Como es una relacion bidirecciona, tanto a cliente se le colocan
		 * las facturas como a cada factura se le coloca el cliente
		*/
		invoice1.setClient(client);
		invoice2.setClient(client);
		/*Al guardar el cliente, guarda automaticamente las facturas
		 * gracias a la propiedad y valor cascade = CascadeType.ALL.
		 * Luego de persistir el cliente Spring JPA se encarga de persistir
		 * las facturas y la relacion de ellas con el cliente
		*/
		System.out.println(this.clientRepository.save(client));
	}

	@Transactional
	public void removeAddressesInput(){
		Optional<Client> optClient = this.clientRepository.findById(1L);
		optClient.ifPresent(client -> {
			Address address1 = new Address("Village", 20);
			Address address2 = new Address("Temple of Time", 85);
		client.setAddresses(Arrays.asList(address1, address2));
		System.out.println(this.clientRepository.save(client));
		Optional<Client> optClient2 = this.clientRepository.findOneAddresses(1L);
		/*Crear un Query personalizado que traiga todas las instancias de la direcciones
		 * en vez de hacer una consulta por cada una, elimina el error Lazy y es mejor manejado
		 * que la configuración en .properties
		*/
		optClient2.ifPresent(cli -> {
			cli.getAddresses().remove(address1);
			System.out.println(this.clientRepository.save(cli));
		});
		});
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Link", "Child Fairy");
		Address address1 = new Address("Deku Tree", 85);
		Address address2 = new Address("Clock Town", 87);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		System.out.println(this.clientRepository.save(client)/*Persiste o actualiza 
		datos*/);
		/*De forma automática persiste las direcciones, sin tener que persistir
		 * las direcciones primero con un repository gracias a la propiedad 
		 * cascade de la llave foranea de addresses
		*/
		Optional<Client> optClient = this.clientRepository.findById(3L);
		/*Genera un error de tipo com.luis.curso.springboot.jpa.springbootjparelationships.
		entities.Client.addresses: could not initialize proxy - no Session ya que en Apps de
		escritorio o consola, luego de buscar por ID con el repositorio, se cierra la 
		conexión a la DB, cuando quiere persistir el objeto actualizado luego de borrar
		una dirección, no le es posible pues no existe conexión a la DB.
		Para solucionar esto se utiliza la propiedad en el archivo .properties:
		spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
		*/
		optClient.ifPresent(cli -> {
			cli.getAddresses().remove(address1);
			/*Cuando se elimina un objeto con remove y una instancia de
			 * un objeto, busca por dirección de memoria de la instancia actual,
			 * pero arriba se persistio primero la instancia, por lo que Spring
			 * Dta JPA cambia las direcciones de memeoria cuando se persiste, así
			 * que aqui, al querer eliminar esa nueva dirección de memoria no le
			 * es posible.
			 * 
			 * Se ayuda del método sobreescrito equeals() de la clase address para
			 * saber que address eliminar comparando el id de la entidad que pasamos
			 * por parámetro con el íd del registro en la DB. Por que como vimos,
			 * la referencia a memoria de la entidad puede cambiar, como aquí, por
			 * eso se necesita equal() [En la misma documentación de .remove() dice
			 * que elimina gracias a la compración de equals()]
			 * 
			 * Vimos que se eliminan todos los recursos de las direcciones que pasamos
			 * como parámetro, se elimina de la tabla intermedia el id de la dirección
			 * y la dirección se elimina de la tabla Adresses esto gracias al parámetro
			 * orphanRemoval=true.
			 * Si no existiera la propiedad orphanRemoval=true, simplemente no elimina
			 * las direcciones de Adresses y quedan huerfanas e inutilizables pues no
			 * se volverian a utilizar en la DB
			*/
			System.out.println(this.clientRepository.save(cli));
		});
	}

	@Transactional
	public void oneToManyInput(){
		System.out.println("Ingrese un id:");
		Long id = this.scanner.nextLong();
		Optional<Client> optClient = this.clientRepository.findById(id);
		optClient.ifPresent(client -> {
		Address address1 = new Address("Deku Tree", 85);
		Address address2 = new Address("Clock Town", 87);
			/*1 Crea los objetos que se persistiran
			 *2 Agrega los objetos creados a un registro existente
			*/
		client.setAddresses(Arrays.asList(address1, address2));
		/*3 Se actualiza en la BD, primero creando los objetos
		 * Address en la DB, luego actualizando su campo id_cliente
		 * para relacionar los objetos creados al cliente que se
		 * establecio
		 * 
		 * Si existiera una tabla intermedia, Spring Data JPA, se engarga
		 * de relacionar en la tabla clients, addresses y la tabla intermedia
		*/

		System.out.println(this.clientRepository.save(client));
		});
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Link", "Child Fairy");
		Address address1 = new Address("Deku Tree", 85);
		Address address2 = new Address("Clock Town", 87);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		System.out.println(this.clientRepository.save(client)/*Persiste o actualiza 
		datos*/);
		/*De forma automática persiste las direcciones, sin tener que persistir
		 * las direcciones primero con un repository gracias a la propiedad 
		 * cascade de la llave foranea de addresses
		*/
	}

	@Transactional
	/*Agrega una factura a un registro nuevo*/
	public void manyToOne(){
		Client client = new Client("Toad", "Mushroom");
		//clientrepository se encarga solo de manejar registros tipo client
		//solo este repository puede persistir en la DB obejtos tipo client
		clientRepository.save(client);
		Invoice invoice = new Invoice("Compras objetos", 2000.00);
		//invoicerepository se encarga solo de manejar registros tipo invoice
		//solo este repository puede persistir en la DB objetos tipo invoice
		invoice.setClient(client);
		//RECUERDA: al persistir un objeto en la DB con save, el método devuelde
		//por defecto el registro que persistio, por ello puede imprimirse
		System.out.println(invoiceRepository.save(invoice));
	}

	@Transactional
	/*Agrega una factura a un registro existente*/
	public void manyToOneFindByIdClient(){
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("PoweUP's", 1000.00);
			invoice.setClient(client);
			System.out.println(invoiceRepository.save(invoice));
		}
	}

	public void all(){
		Iterable<Client> i = this.clientRepository.findAll();
		i.forEach(client -> {
			System.out.println(client);
		});
	}
}

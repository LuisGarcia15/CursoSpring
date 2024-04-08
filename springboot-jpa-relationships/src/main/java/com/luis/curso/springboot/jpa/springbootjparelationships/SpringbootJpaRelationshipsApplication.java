package com.luis.curso.springboot.jpa.springbootjparelationships;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Address;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Client;
import com.luis.curso.springboot.jpa.springbootjparelationships.entities.Invoice;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.ClienteRepository;
import com.luis.curso.springboot.jpa.springbootjparelationships.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("********** Manejando la relación ManyToOne **********");
		//this.manyToOne();
		System.out.println("********** Manejando la relación ManyToOne insertando la relación en un ID existente**********");
		//this.manyToOneFindByIdClient();
		System.out.println("********** Manejando la relación OneToMany **********");
		//this.oneToMany();
		System.out.println("********** Manejando la relación OneToMany mediante input **********");
		//this.oneToManyInput();
		System.out.println("********** Borrando relación de OneToMany **********");
		this.removeAddress();
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
}

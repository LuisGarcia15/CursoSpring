package com.luis.curso.springboot.jpa.springbootjparelationships;

import java.util.Optional;

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
		this.oneToMany();
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Link", "Child Fairy");
		Address address1 = new Address("Deku Tree", 85);
		Address address2 = new Address("Clock Town", 87);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		System.out.println(this.clientRepository.save(client));
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

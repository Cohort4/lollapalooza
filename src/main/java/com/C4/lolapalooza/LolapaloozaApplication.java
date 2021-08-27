package com.C4.lolapalooza;

import com.C4.lolapalooza.models.*;
import com.C4.lolapalooza.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class LolapaloozaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LolapaloozaApplication.class, args);

	}
	@Bean
	public CommandLineRunner initData(ClientRepository repositoryClient,
									  CommentRepository commentRepository,
									  FacturaRepository facturaRepository,
									  MerchRepository merchRepository,
									  EventRepository eventRepository,
									  SiteRepository siteRepository,
									  TicketEntryRepository ticketEntryRepository,
									  MerchFacturaRepository merchFacturaRepository,
									  TicketFacturaRepository ticketFacturaRepository,
									  PreviousEventRepository previousEventRepository
	) {
		return (args) -> {

			LocalDate date = LocalDate.now();

			Client admin = repositoryClient.save(new Client("Admin","Admin","admin@lollapalooza.com", passwordEncoder.encode("admin"),true));
			Client sofi = repositoryClient.save(new Client("Sofi","Menichelli","sofi@mindhub.com", passwordEncoder.encode("sofi"),true));
			Client dami = repositoryClient.save(new Client("Dami","Zamora","dami@mindhub.com", passwordEncoder.encode("dami"),true));
			Client tobi = repositoryClient.save(new Client("Tobi","Pazos","tobi@mindhub.com", passwordEncoder.encode("tobi"),true));
			Client fede = repositoryClient.save(new Client("Fede","Battcock","fede@mindhub.com", passwordEncoder.encode("fede"),true));
			Client barto = repositoryClient.save(new Client("Il","Barto","bartolome.albarracin@hotmail.com", passwordEncoder.encode("barto"),true));
			Client client2 = repositoryClient.save(new Client("prueba","prueba","prueba@mindhub.com", passwordEncoder.encode("12345"),true));

			Factura factura = facturaRepository.save(new Factura(date.plusDays(2), sofi));
			Factura factura2 = facturaRepository.save(new Factura(date, client2));

			//PRODUCTOS
			Merch remera = merchRepository.save(new Merch(ProductType.TShirt,"T-shirt Lolla 2021", "", "./assets/remera.png",List.of("XS","S","M","L","XL","XXL"), 2000, 650.00,true ));
			Merch remera1 = merchRepository.save(new Merch(ProductType.TShirt,"T-shirt Lolla 2020", "", "./assets/remera2.png",List.of("XS","S","M","L","XL","XXL"), 2500, 500.00,true ));
			Merch remera2 = merchRepository.save(new Merch(ProductType.TShirt,"T-shirt Lolla 2019", "", "./assets/remera3.png",List.of("XS","S","M","L","XL","XXL"), 1000, 700.00,true ));
			Merch remera3 = merchRepository.save(new Merch(ProductType.TShirt,"T-shirt Queen", "", "./assets/remera4.png",List.of("XS","S","M","L","XL","XXL"), 1500, 900.00,true ));
			Merch remera4 = merchRepository.save(new Merch(ProductType.TShirt,"T-shirt Electric", "", "./assets/remera5.png",List.of("XS","S","M","L","XL","XXL"), 1500, 800.00,true ));
			Merch billetera = merchRepository.save(new Merch(ProductType.Wallet,"Wallet Lolla", "Cuero", "./assets/billetera.png", 1500, 1500.00,true ));
			Merch calco = merchRepository.save(new Merch(ProductType.Stickers,"Sticker 1", "", "./assets/stickers.png", 500, 200.00,true ));
			Merch calco2 = merchRepository.save(new Merch(ProductType.Stickers,"Sticker", "", "./assets/stickers2.png", 500, 200.00,true ));
			Merch buzo = merchRepository.save(new Merch(ProductType.Sweeter,"Sweeter Lolla2021", "", "./assets/buzo.png",List.of("XS","S","M","L","XL","XXL"), 1500, 2000.00,true ));
			Merch buzo2 = merchRepository.save(new Merch(ProductType.Sweeter,"Sweeter Lolla2020", "", "./assets/buzo2.png",List.of("XS","S","M","L","XL","XXL"), 1500, 2500.00,true ));
			Merch campera = merchRepository.save(new Merch(ProductType.Jacket,"Jacket Lolla", "", "./assets/campera.png",List.of("XS","S","M","L","XL","XXL"), 1500, 4500.00,true ));
			Merch gorro = merchRepository.save(new Merch(ProductType.Hat,"Hat", "", "./assets/gorro.png", 1000, 900.00,true ));
			Merch gorra = merchRepository.save(new Merch(ProductType.Cap,"Cap", "", "./assets/gorra.png", 1000, 1450.00,true ));
			Merch bolso = merchRepository.save(new Merch(ProductType.Bag,"Bag", "", "./assets/bolso.png", 1000, 2450.00,true ));
			Merch agenda = merchRepository.save(new Merch(ProductType.Library,"Diary 2020", "", "./assets/agenda.png", 500, 450.00,true ));
			Merch agenda2 = merchRepository.save(new Merch(ProductType.Library,"Diary 2021", "", "./assets/agenda2.png", 500, 750.00,true ));
			Merch barbijo = merchRepository.save(new Merch(ProductType.Accessories,"Mask", "", "./assets/barbijo.png", 2000, 450.00,true ));
			Merch barbijo2 = merchRepository.save(new Merch(ProductType.Accessories,"Mask", "", "./assets/barbijo2.png", 2000, 450.00,true ));
			Merch barbijo3 = merchRepository.save(new Merch(ProductType.Accessories,"Mask","", "./assets/barbijo3.png", 2000, 450.00,true ));
			Merch barbijo4 = merchRepository.save(new Merch(ProductType.Accessories,"Mask", "", "./assets/barbijo4.png", 2000, 450.00,true ));

			Site site = siteRepository.save((new Site("Buenos Aires, Hipodromo - San Isidro", 100)));

			Event event = eventRepository.save(new Event("Day1",List.of("Metallica", "Redhot chilli pepers", "Foo Fighters","Kasabian"), date, 2500, site,true,"./img/day1.jpg"));
			Event event2 = eventRepository.save(new Event("Day2",List.of("Ke Personajes", "Roman, El Original", "L-Gante","Damas Gratis"), date, 1000, site,true,"./img/day2.jpg"));
			Event event3 = eventRepository.save(new Event("Day3",List.of("Duki", "Wos", "Lit Killah","Bad Bunny"), date, 1000, site,true,"./img/day3.jpg"));
			Event event4 = eventRepository.save(new Event("Day4",List.of("Justin Bieber", "Shawn Mendes", "Selena Gomez","Justin Timberlake"), date, 2000, site,true,"./img/day4.jpg"));


			TicketFactura ticketFactura = ticketFacturaRepository.save(new TicketFactura(factura));
			TicketFactura ticketFactura2 = ticketFacturaRepository.save(new TicketFactura(factura));

			TicketEntry ticketEntry = ticketEntryRepository.save(new TicketEntry(event, ticketFactura));
			TicketEntry ticketEntry1 = ticketEntryRepository.save(new TicketEntry(event2, ticketFactura2));
			TicketEntry ticketEntry3 = ticketEntryRepository.save(new TicketEntry(event3, ticketFactura2));
			TicketEntry ticketEntry2 = ticketEntryRepository.save(new TicketEntry(event2, ticketFactura));

			MerchFactura merchFactura1 = merchFacturaRepository.save(new MerchFactura(factura, gorra, 2));
			MerchFactura merchFactura2 = merchFacturaRepository.save(new MerchFactura(factura, remera,3));



			//Eventos Previos

			PreviousEvent previousEvent = previousEventRepository.save(new PreviousEvent("TARDE DEL DIA 1","./img/img-previous/1.jpg"));
			PreviousEvent previousEvent1 = previousEventRepository.save(new PreviousEvent("'LET ME PLAY GUITAR WITH YOU'", "./img/img-previous/2.jpg"));
			PreviousEvent previousEvent2 = previousEventRepository.save(new PreviousEvent("ARCTIC MONKEYS","./img/img-previous/3.jpg"));
			PreviousEvent previousEvent3 = previousEventRepository.save(new PreviousEvent("NOCHE DEL DIA 1","./img/img-previous/4.jpg"));
			PreviousEvent previousEvent4 = previousEventRepository.save(new PreviousEvent("LA MONA JIMENEZ","./img/img-previous/5.jpg"));
			PreviousEvent previousEvent5 = previousEventRepository.save(new PreviousEvent("PRIMERA FILA","./img/img-previous/6.jpg"));
			PreviousEvent previousEvent6 = previousEventRepository.save(new PreviousEvent("DJ TIESTO","./img/img-previous/7.jpg"));
			PreviousEvent previousEvent7 = previousEventRepository.save(new PreviousEvent("POSTALES DEL LOLLAPALOOZA","./img/img-previous/8.jpg"));
			PreviousEvent previousEvent8 = previousEventRepository.save(new PreviousEvent("NOCHE DEL DIA 2","./img/img-previous/9.jpg"));
			PreviousEvent previousEvent9 = previousEventRepository.save(new PreviousEvent("TARDE DEL DIA 3","./img/img-previous/10.jpg"));
			PreviousEvent previousEvent10 = previousEventRepository.save(new PreviousEvent("FITO PAEZ","./img/img-previous/11.jpg"));

			Comment comment1 = commentRepository.save(new Comment("Muy Bueno", LocalDate.now(),previousEvent,client2));
			Comment comment = commentRepository.save(new Comment("Fotón!", LocalDate.now(), previousEvent9, sofi));
			Comment comment2 = commentRepository.save(new Comment("Lo dejaron?", LocalDate.now(),previousEvent1, client2));
			Comment comment3 = commentRepository.save(new Comment("Muy bueno", LocalDate.now(),previousEvent3, fede));
			Comment comment4 = commentRepository.save(new Comment("Una joyita", LocalDate.now(),previousEvent5, tobi));
			Comment comment5 = commentRepository.save(new Comment("Me encantó", LocalDate.now(),previousEvent7, sofi));
			Comment comment6 = commentRepository.save(new Comment("Que banda de mierda!", LocalDate.now(),previousEvent2, fede));
			Comment comment7 = commentRepository.save(new Comment("alala lala long", LocalDate.now(),previousEvent6, barto));
			Comment comment9 = commentRepository.save(new Comment("Espectacular!", LocalDate.now(),previousEvent8, dami));
			Comment comment8 = commentRepository.save(new Comment("Yo me tomé todo el vino", LocalDate.now(),previousEvent4, barto));
			Comment comment10 = commentRepository.save(new Comment("Que grande!", LocalDate.now(),previousEvent10,fede));

			System.out.println("Que la felicidad nos colme el alma, sha la la long ♫♪");
			System.out.println("BUEN TRABAJO!!! :D");
		};
	}


}

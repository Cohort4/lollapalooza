package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.ClientDTO;
import com.C4.lolapalooza.dtos.FacturaDTO;
import com.C4.lolapalooza.dtos.TicketEntryDTO;
import com.C4.lolapalooza.dtos.TicketFacturaDTO;
import com.C4.lolapalooza.models.*;
import com.C4.lolapalooza.repositories.FacturaRepository;
import com.C4.lolapalooza.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FacturaRepository facturaRepository;

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return this.clientService.getClients().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        System.out.println("Hola");
        if (email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientService.findClientByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password),true);
        clientService.saveC(client);
        return new ResponseEntity<>("User created Correctly",HttpStatus.CREATED);
    }

    @PostMapping(path = "/client/setStatusClient")
    public ResponseEntity<Object> setStatusClient(@RequestParam String email) {

        if (email.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientService.findClientByEmail(email) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        } else {
            Client clientToDelete = clientService.findClientByEmail(email);
            if (clientToDelete.getStatus().equals(true)) {
                clientToDelete.setStatus(false);
                clientService.saveC(clientToDelete);
                return new ResponseEntity<>("Client inactive", HttpStatus.ACCEPTED);

            } else {
                if (clientToDelete.getStatus().equals(false))
                    clientToDelete.setStatus(true);
                clientService.saveC(clientToDelete);
                return new ResponseEntity<>("Client active", HttpStatus.ACCEPTED);
            }
        }
    }




    @GetMapping("/clients/current")
    public ResponseEntity<?> getClient(Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>("Error, you are not authenticated", HttpStatus.FORBIDDEN);
        }
        Client client = clientService.findClientByEmail(authentication.getName());
        if (client == null) {
            return new ResponseEntity<>("Error, you are not authenticated", HttpStatus.UNAUTHORIZED);
        }
        if(client.getStatus() == false) {
            return new ResponseEntity<>("Error, your account is inactive", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients/current/facturas")
    public ResponseEntity<?> getFacturasCurrentClient(Authentication authentication) {

        if (authentication == null) {
            return new ResponseEntity<>("Error, por Bad Authentication ", HttpStatus.FORBIDDEN);
        }

        Client client = clientService.findClientByEmail(authentication.getName());

        if (client == null) {
            return new ResponseEntity<>("Error, you are not authenticated", HttpStatus.UNAUTHORIZED);
        }

        Set<FacturaDTO> facturaDTOS = client.getFacturas().stream().map(FacturaDTO::new).collect(Collectors.toSet());

        if (facturaDTOS.isEmpty()) {
            return new ResponseEntity<>("Este cliente no tiene facturas", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(facturaDTOS, HttpStatus.CREATED);
    }

    @GetMapping("/clients/current/tickets")
    public ResponseEntity<?> getTicketsCurrentClient(Authentication authentication) {

        if (authentication == null) {
            return new ResponseEntity<>("Error, Bad Authentication ", HttpStatus.FORBIDDEN);
        }

        Client client = clientService.findClientByEmail(authentication.getName());

        if (client == null) {
            return new ResponseEntity<>("Error, you are not authenticated", HttpStatus.UNAUTHORIZED);
        }

        Set<TicketFacturaDTO> ticketFacturaDTOS = client.getFacturas().stream().flatMap(x -> x.getTicketFacturas().stream().map(ticketFactura -> new TicketFacturaDTO(ticketFactura))).collect(Collectors.toSet());

        /*Set<Set<TicketFacturaDTO>> ticketFacturaDTOS =  client.getFacturas().stream().map(x -> x.getTicketFacturas().stream().map(TicketFacturaDTO::new)
                .collect(Collectors.toSet())).collect(Collectors.toSet());*/

        if(ticketFacturaDTOS.isEmpty()){
            return new ResponseEntity<>("Este cliente no tiene tickets de entrada", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ticketFacturaDTOS,HttpStatus.CREATED);


    }
}
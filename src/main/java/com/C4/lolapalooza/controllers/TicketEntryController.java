package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.EventDTO;
import com.C4.lolapalooza.dtos.TicketEntryDTO;
import com.C4.lolapalooza.dtos.TicketFacturaDTO;
import com.C4.lolapalooza.repositories.TicketEntryRepository;
import com.C4.lolapalooza.repositories.TicketFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketEntryController {

    @Autowired
    TicketFacturaRepository ticketFacturaRepository;

    @Autowired
    TicketEntryRepository ticketEntryRepository;

    @RequestMapping("/ticketEntry")
    private List<TicketEntryDTO> getTicketEntry(){
        return this.ticketEntryRepository.findAll().stream().map(TicketEntryDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/tickets")
    private List<TicketFacturaDTO> getTickets(){
        return this.ticketFacturaRepository.findAll().stream().map(x -> new TicketFacturaDTO(x)).collect(Collectors.toList());
    }

    @RequestMapping("/tickets/{ id }")
    private TicketFacturaDTO getTicketsById(@PathVariable Long id){
        return this.ticketFacturaRepository.findById(id).map(x -> new TicketFacturaDTO(x)).orElse(null);
    }

}

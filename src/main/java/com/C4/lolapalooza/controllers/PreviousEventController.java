package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.PreviousEventDTO;
import com.C4.lolapalooza.models.Client;
import com.C4.lolapalooza.models.PreviousEvent;
import com.C4.lolapalooza.repositories.PreviousEventRepository;
import com.C4.lolapalooza.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PreviousEventController {

    @Autowired
    PreviousEventRepository previousEventRepository;

    @Autowired
    ClientService clientService;


    @GetMapping("/previousevent")
    public List<PreviousEventDTO> getPreviousEvents(){
        return this.previousEventRepository.findAll().stream().map(PreviousEventDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/previousevent/{ id }")
    public PreviousEventDTO getPreviousEventsById(@PathVariable Long id){
        return this.previousEventRepository.findById(id).map(PreviousEventDTO::new).orElse(null);
        }



}

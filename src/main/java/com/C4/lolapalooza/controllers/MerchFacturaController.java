package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.MerchFacturaDTO;
import com.C4.lolapalooza.repositories.MerchFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MerchFacturaController {

    @Autowired
    MerchFacturaRepository merchFacturaRepository;

    @RequestMapping("/merch_facturas")
    private List<MerchFacturaDTO> getMerchFacturas(){
        return this.merchFacturaRepository.findAll().stream().map(x -> new MerchFacturaDTO(x)).collect(Collectors.toList());
    }

    @RequestMapping("/merch_facturas/{ id }")
    private MerchFacturaDTO getMerchFacturasById(@PathVariable Long id){
        return this.merchFacturaRepository.findById(id).map(x -> new MerchFacturaDTO(x)).orElse(null);
    }

}



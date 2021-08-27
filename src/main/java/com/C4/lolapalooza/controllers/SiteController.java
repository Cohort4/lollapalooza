package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.SiteDTO;
import com.C4.lolapalooza.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SiteController {

    @Autowired
    SiteRepository siteRepository;

    @RequestMapping("/sites")
    private List<SiteDTO> getSites(){
        return this.siteRepository.findAll().stream().map(x -> new SiteDTO(x)).collect(Collectors.toList());
    }

    @RequestMapping("/sites/{ id }")
    private SiteDTO getSitesById(@PathVariable Long id){
        return this.siteRepository.findById(id).map(x -> new SiteDTO(x)).orElse(null);
    }
}

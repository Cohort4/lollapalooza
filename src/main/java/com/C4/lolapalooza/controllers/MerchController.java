package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.MerchDTO;

import com.C4.lolapalooza.models.ProductType;
import com.C4.lolapalooza.repositories.MerchRepository;

import com.C4.lolapalooza.service.MerchService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MerchController {

    @Autowired
    MerchService merchService;

    @Autowired
    MerchRepository merchRepository;

    @RequestMapping("/merchs")
    public List<MerchDTO> getMerchs() {
        return merchService.getMerchs();
    }

    @RequestMapping("/merchs/{id}")
    public MerchDTO getMerchById(@PathVariable Long id) {
        return merchService.getMerchById(id);
    }


    @PostMapping(path = "/merchs/createMerch")
    public ResponseEntity<?> registerMerchs(@RequestParam ProductType productType,
                                            @RequestParam String productName,
                                            @RequestParam String descriptionProduct,
                                            @RequestParam String imageProduct,
                                            @RequestParam String tallesProduct,
                                            @RequestParam int stockProduct,
                                            @RequestParam Double priceProduct) throws IOException {

        return merchService.registerMerchs(productType,productName,descriptionProduct,imageProduct,
                                           tallesProduct,stockProduct,priceProduct  );
    }

    @PostMapping(path = "/merchs/deleteMerch")
    public ResponseEntity<?> deleteMerchs(@RequestParam String productName) {
    return merchService.deleteMerchs(productName);
    }

    @PostMapping(path = "/merchs/editMerch")
    public ResponseEntity<?> editMerch(@RequestParam int editStock,@RequestParam Double editPrice,
                                       @RequestParam String nameProduct) {
        return merchService.editMerch(editStock,editPrice,nameProduct);
    }


    @PostMapping(path = "/merchs/statusMerch")
    public ResponseEntity<?> statusMerchs(@RequestParam String productName) {
        return merchService.statusMerchs(productName);
    }

}



package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.FacturaDTO;
import com.C4.lolapalooza.dtos.TicketFacturaDTO;
import com.C4.lolapalooza.models.*;
import com.C4.lolapalooza.repositories.*;
import com.C4.lolapalooza.service.ClientService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FacturaController {

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    MerchFacturaRepository merchFacturaRepository;

    @Autowired
    MerchRepository merchRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TicketFacturaRepository ticketFacturaRepository;

    @Autowired
    TicketEntryRepository ticketEntryRepository;

    @GetMapping("/facturas")
    private List<FacturaDTO> getFacturas() {
        return this.facturaRepository.findAll().stream().map(x -> new FacturaDTO(x)).collect(Collectors.toList());
    }

    @GetMapping("/facturas/{id}")
    private FacturaDTO getFacturaById(@PathVariable Long id) {
        return this.facturaRepository.findById(id).map(x -> new FacturaDTO(x)).orElse(null);
    }

    //Funcionalidad PDF Exports
    @PostMapping("/clients/current/export/pdf")
    public void ExportingToPDF(HttpServletResponse response, Authentication authentication, @RequestParam Long numberFactura) throws DocumentException, IOException {

        Client client = clientService.findClientByEmail(authentication.getName());
        Factura factura = facturaRepository.getById(numberFactura);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=LollaPalooza N° " + numberFactura + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<MerchFactura> listMerchs = facturaRepository.findById(numberFactura).get().getMerchFacturas().stream().collect(Collectors.toList());
        List<TicketFacturaDTO> ticketFacturas = facturaRepository.findById(numberFactura).get().getTicketFacturas().stream().map(TicketFacturaDTO::new).collect(Collectors.toList());

        FacturaDTO facturaDTO = new FacturaDTO(factura);
        FacturaPDFExport exporter = new FacturaPDFExport(listMerchs, facturaDTO, ticketFacturas);
        exporter.usePDFExport(response);
        System.out.println("PDF created");

    }

    // HACER RUTA CURRENT CLIENT FACTURAS



    @PostMapping("/clients/current/newfactura")
    private ResponseEntity<?> newFactura(Authentication authentication, String[] nameArticle, Integer[] cantArticle
                                         , String[] nameEvent, Integer[] cantEvent
                                          ) {

        Client client = clientService.findClientByEmail(authentication.getName());

        if (client == null) {
            return new ResponseEntity<>("Client not recognized", HttpStatus.FORBIDDEN);
        }
        if (authentication == null) {
            return new ResponseEntity<>("Client not authenticated", HttpStatus.FORBIDDEN);
        }

        Factura factura = facturaRepository.save(new Factura(LocalDate.now(), client));


        if (!(nameArticle == null) && !(cantArticle == null)) {

            for (int i = 0; i < cantArticle.length; i++) {

                System.out.println("entre hasta acá3");
                Merch product = merchRepository.findByName(nameArticle[i]);

                if (product == null) {
                    return new ResponseEntity<>("Product not find", HttpStatus.FORBIDDEN);
                }
                if (product.getStock() < cantArticle[i]) {
                    return new ResponseEntity<>("Stock not available", HttpStatus.FORBIDDEN);
                }

                merchFacturaRepository.save(new MerchFactura(factura, product, cantArticle[i]));

                product.setStock(product.getStock() - cantArticle[i]);

                merchRepository.save(product);
            }
        }


        if (cantEvent.length > 0) {

            TicketFactura ticketFactura = ticketFacturaRepository.save(new TicketFactura(factura));


            for (int i = 0; i < cantEvent.length; i++) {
                Event eventToFacturar = eventRepository.findBynameEvent(nameEvent[i]);

                for (int j = 0; j < cantEvent[i]; j++) {

                    if (eventToFacturar.getRemainingQuantity() < 1) {
                        return new ResponseEntity<>("Stock not available", HttpStatus.FORBIDDEN);
                    }

                    if (!(eventToFacturar == null)) {

                        ticketEntryRepository.save(new TicketEntry(eventToFacturar, ticketFactura));
                    }

                    eventToFacturar.setRemainingQuantity(eventToFacturar.getRemainingQuantity() - 1);

                    eventRepository.save(eventToFacturar);

                }
            }// FIN FOR

        }

        return new ResponseEntity<>(factura.getId(), HttpStatus.OK);
    }
}
package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Client;
import com.C4.lolapalooza.models.Factura;
import com.C4.lolapalooza.models.MerchFactura;
import com.C4.lolapalooza.models.TicketFactura;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FacturaDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private Client client;
    private Set<MerchFacturaDTO> merchs = new HashSet<>();
    private Set<TicketFacturaDTO> tickets = new HashSet<>();

    public FacturaDTO() {
    }

    public FacturaDTO(Factura factura) {
        this.id = factura.getId();
        this.date = factura.getDate();
        this.client = factura.getClient();
        this.merchs = factura.getMerchFacturas().stream().map(MerchFacturaDTO::new).collect(Collectors.toSet());
        this.tickets = factura.getTicketFacturas().stream().map(TicketFacturaDTO::new).collect(Collectors.toSet());
        this.amount = calcularBalance(factura.getMerchFacturas()) + calcularBalanceT(tickets);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<MerchFacturaDTO> getMerchs() {
        return merchs;
    }

    public void setMerchs(Set<MerchFacturaDTO> merchs) {
        this.merchs = merchs;
    }

    public Set<TicketFacturaDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketFacturaDTO> tickets) {
        this.tickets = tickets;
    }

    public static double calcularBalance(Set<MerchFactura> merchFacturas) {
        double balance = 0;
        for (MerchFactura merchFactura : merchFacturas) {
            balance += merchFactura.getPrecioTotal();
        }
        return balance;
    }
    public static double calcularBalanceT(Set<TicketFacturaDTO> ticketFacturas) {
        double balance = 0;

        for (TicketFacturaDTO ticketFacturaDTO : ticketFacturas) {
            balance = balance + ticketFacturaDTO.getPrecioTotal();

        }
        return balance;

    }

}

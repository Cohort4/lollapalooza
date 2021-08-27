package com.C4.lolapalooza.models;

import com.C4.lolapalooza.dtos.MerchDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private LocalDate date;
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idClient")
    private Client client;

    @OneToMany(mappedBy = "factura", fetch = FetchType.EAGER)
    private Set<MerchFactura> merchFacturas = new HashSet<>();

    @OneToMany(mappedBy = "factura", fetch = FetchType.EAGER)
    private Set<TicketFactura> ticketFacturas = new HashSet<>();

    public Factura() {
    }

    public Factura(LocalDate date,Client client) {
        this.date = date;
        this.client = client;
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

    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<MerchFactura> getMerchFacturas() {
        return merchFacturas;
    }

    public void setMerchFacturas(Set<MerchFactura> merchFacturas) {
        this.merchFacturas = merchFacturas;
    }

    public Set<TicketFactura> getTicketFacturas() {
        return ticketFacturas;
    }

    public void setTicketFacturas(Set<TicketFactura> ticketFacturas) {
        this.ticketFacturas = ticketFacturas;
    }
}

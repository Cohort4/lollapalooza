package com.C4.lolapalooza.models;

import net.minidev.json.JSONUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class TicketFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idFactura")
    private Factura factura;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idTicket")
    private TicketEntry ticketEntry;*/
    @OneToMany(mappedBy = "ticketFactura", fetch = FetchType.EAGER)
    private Set<TicketEntry> ticketEntry = new HashSet<>();
    //private List<LocalDate> dates = new ArrayList<>();
    private double priceTotal;
    private String site;

    public TicketFactura() {
    }

    public TicketFactura(Factura factura) {
        this.factura = factura;
        //this.priceTotal = ticketEntry.
        //this.site = site;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Set<TicketEntry> getTicketEntry() {
        return ticketEntry;
    }

    public void setTicketEntry(Set<TicketEntry> ticketEntry) {
        this.ticketEntry = ticketEntry;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}


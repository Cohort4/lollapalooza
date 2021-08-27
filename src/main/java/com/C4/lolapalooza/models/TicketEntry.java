package com.C4.lolapalooza.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TicketEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double price;
    private LocalDate dateConcert;
    private String eventSite;
    private int quantity;

//    @OneToMany(mappedBy = "ticketEntry", fetch = FetchType.EAGER )
//    private Set<TicketFactura> ticketFacturas = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketFacturaId")
    private TicketFactura ticketFactura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idEvent")
    private Event event;

    public TicketEntry() {
    }

    public TicketEntry(Event event, TicketFactura ticketFactura) {
        this.event = event;
        this.eventSite = event.getSite().getLocation();
        this.price = event.getPrice();
        this.dateConcert = event.getDateEvent();
        this.ticketFactura = ticketFactura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateConcert() {
        return dateConcert;
    }

    public void setDateConcert(LocalDate dateConcert) {
        this.dateConcert = dateConcert;
    }

    public TicketFactura getTicketFactura() {
        return ticketFactura;
    }

    public void setTicketFactura(TicketFactura ticketFactura) {
        this.ticketFactura = ticketFactura;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEventSite() {
        return eventSite;
    }

    public void setEventSite(String eventSite) {
        this.eventSite = eventSite;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

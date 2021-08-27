package com.C4.lolapalooza.models;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.*;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private LocalDate dateEvent;
    private double price;
    private Boolean status;
    private long remainingQuantity;
    private String image;

    @ElementCollection
    private List<String> band = new ArrayList<String>();

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<TicketEntry> tickets = new HashSet<>();

    //Traer la capacidad del sitio
    //hacer una funcion que cuente el largo del set de tickets y lo reste a la capacidad

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idSede")
    private Site site;

    public Event() {
    }

    public Event(String name,List<String> band,LocalDate dateEvent, double price, Site site,Boolean status, String image) {
        this.name = name;
        this.band = band;
        this.dateEvent = dateEvent;
        this.price = price;
        this.site = site;
        this.status = status;
        this.remainingQuantity = site.getCapacity();
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBand() {
        return band;
    }

    public void setBand(List<String> band) {
        this.band = band;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<TicketEntry> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntry> tickets) {
        this.tickets = tickets;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

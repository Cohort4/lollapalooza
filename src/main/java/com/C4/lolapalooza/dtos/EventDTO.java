package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Event;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EventDTO {
    private UUID id;
    private String nameEvent;
    private List<String> bands = new ArrayList<>();
    private String site;
    private Boolean status;
    private LocalDate dateEvent;
    private Set<TicketEntryDTO> tickets = new HashSet<>();
    private double price;
    private long remainingQuantity;
    private String image;

    public EventDTO() {
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.nameEvent = event.getNameEvent();
        this.bands = event.getBand();
        this.status = event.getStatus();
        this.site = event.getSite().getLocation();
        this.dateEvent = event.getDateEvent();
        this.tickets = event.getTickets().stream().map(TicketEntryDTO::new).collect(Collectors.toSet());
        this.price = event.getPrice();
        this.remainingQuantity = event.getRemainingQuantity();
        this.image = event.getImage();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getBands() { return bands; }

    public void setBands(List<String> bands) {
        this.bands = bands;
    }

    public String getIdSite() {
        return site;
    }

    public void setIdSite(String idSite) {
        this.site = idSite;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Set<TicketEntryDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntryDTO> tickets) {
        this.tickets = tickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.TicketEntry;

import java.time.LocalDate;
import java.util.UUID;

public class TicketEntryDTO {
    private Long id;
    private double price;
    private LocalDate datesConcert;
    private UUID idEvent;
    private Long quantity;

    public TicketEntryDTO() {
    }

    public TicketEntryDTO(TicketEntry ticketEntry) {
        this.price = ticketEntry.getPrice();
        this.datesConcert = ticketEntry.getDateConcert();
        this.idEvent = ticketEntry.getEvent().getId();
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

    public LocalDate getDatesConcert() {
        return datesConcert;
    }

    public void setDatesConcert(LocalDate datesConcert) {
        this.datesConcert = datesConcert;
    }

    public UUID getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(UUID idEvent) {
        this.idEvent = idEvent;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}

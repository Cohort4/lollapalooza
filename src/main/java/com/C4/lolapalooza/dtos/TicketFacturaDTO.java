package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.TicketFactura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketFacturaDTO {
    private Long id;

    private List<LocalDate> dates = new ArrayList<>();
    private double precioTotal;
    private String site;

    public TicketFacturaDTO() {
    }

    public TicketFacturaDTO(TicketFactura ticketFactura) {
        this.id = ticketFactura.getId();
        this.dates = ticketFactura.getTicketEntry().stream().map(t -> t.getDateConcert()).collect(Collectors.toList());
        this.precioTotal = ticketFactura.getTicketEntry().stream().mapToDouble(t -> t.getPrice()).sum();
      //this.precioTotal = ticketFactura.getPriceTotal();
        this.site = ticketFactura.getTicketEntry().stream().findFirst().get().getEventSite();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

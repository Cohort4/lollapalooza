package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Site;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SiteDTO {
    private String id;

    private String location;
    private long capacity;
    private long quantitySoldOut;
    private Set<EventDTO> events = new HashSet<>();

    public SiteDTO() {
    }

    public SiteDTO(Site site) {
        this.id = site.getId();
        this.location = site.getLocation();
        this.capacity = site.getCapacity();
        //this.quantitySoldOut = site.getQuantitySoldOut();
        this.events = site.getEvents().stream().map(EventDTO::new).collect(Collectors.toSet());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getQuantitySoldOut() {
        return quantitySoldOut;
    }

    public void setQuantitySoldOut(long quantitySoldOut) {
        this.quantitySoldOut = quantitySoldOut;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDTO> events) {
        this.events = events;
    }
}

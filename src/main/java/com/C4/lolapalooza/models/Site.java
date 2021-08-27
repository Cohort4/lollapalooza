package com.C4.lolapalooza.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id;
    private String location;
    private long capacity;
    //private long quantitySoldOut;

    @OneToMany(mappedBy = "site")
    private Set<Event> events = new HashSet<>();

    public Site() {
    }

    public Site(String location, long capacity) {
        this.location = location;
        this.capacity = capacity;
        //this.quantitySoldOut = quantitySoldOut;
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

/*    public long getQuantitySoldOut() {
        return quantitySoldOut;
    }

    public void setQuantitySoldOut(long quantitySoldOut) {
        this.quantitySoldOut = quantitySoldOut;
    }*/

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}

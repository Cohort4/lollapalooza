package com.C4.lolapalooza.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String description;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idClient")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="previousEvent")
    private PreviousEvent previousEvent;

    public Comment() {
    }

    public Comment(String description, LocalDate date,PreviousEvent previousEvent, Client client) {
        this.description = description;
        this.date = date;
        this.client = client;
        this.previousEvent = previousEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PreviousEvent getPreviousEvent() {
        return previousEvent;
    }

    public void setPreviousEvent(PreviousEvent previousEvent) {
        this.previousEvent = previousEvent;
    }
}

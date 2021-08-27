package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private Boolean status;
    private Set<CommentDTO> comments = new HashSet<>();
    private Set<FacturaDTO> facturas = new HashSet<>();

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.LastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.status = client.getStatus();
        this.comments = client.getComments().stream().map(CommentDTO::new).collect(Collectors.toSet());
        this.facturas = client.getFacturas().stream().map(FacturaDTO::new).collect(Collectors.toSet());

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<CommentDTO> getComments() { return comments; }

    public void setComments(Set<CommentDTO> comments) { this.comments = comments; }

    public Set<FacturaDTO> getFacturas() {
        return facturas;
    }

    public void setFacturas(Set<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

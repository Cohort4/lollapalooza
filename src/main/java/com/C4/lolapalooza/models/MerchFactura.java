package com.C4.lolapalooza.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class MerchFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idFactura")
    private Factura factura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idMerch")
    private Merch merch;
    private int cantidad;
    private String name;
    private String description;
    private double precioUnitario;
    private double precioTotal;

    public MerchFactura() {
    }

    public MerchFactura(Factura factura, Merch merch, int cantidad) {
        this.factura = factura;
        this.merch = merch;
        this.cantidad = cantidad;
        this.name = merch.getName();
        this.description = merch.getDescription();
        this.precioUnitario = merch.getPrice();
        this.precioTotal = merch.getPrice()*this.cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Merch getMerch() {
        return merch;
    }

    public void setMerch(Merch merch) {
        this.merch = merch;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}

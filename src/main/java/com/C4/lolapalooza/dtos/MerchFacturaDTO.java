package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.MerchFactura;

public class MerchFacturaDTO {
    private Long id;
    private Long idFactura;
    private Long idMerch;
    private int cantidad;
    private String name;
    private String description;
    private double precioUnitario;
    private double precioTotal;

    public MerchFacturaDTO() {
    }

    public MerchFacturaDTO(MerchFactura merchFactura) {
        this.id = merchFactura.getId();
        this.idFactura = merchFactura.getFactura().getId();
        this.idMerch = merchFactura.getMerch().getId();
        this.cantidad = merchFactura.getCantidad();
        this.name = merchFactura.getMerch().getName();
        this.description = merchFactura.getMerch().getDescription();
        this.precioUnitario = merchFactura.getMerch().getPrice();
        this.precioTotal = merchFactura.getMerch().getPrice()*this.cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Long getIdMerch() {
        return idMerch;
    }

    public void setIdMerch(Long idMerch) {
        this.idMerch = idMerch;
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

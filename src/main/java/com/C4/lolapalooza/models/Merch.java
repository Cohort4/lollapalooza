package com.C4.lolapalooza.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Entity
public class Merch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private ProductType productType;
    private String name;
    private String description;
    private String image;
    @ElementCollection
    private List<String> talles = new ArrayList<>();
    private int stock;
    private Double price;
    private boolean status;

    @OneToMany(mappedBy = "merch", fetch  = FetchType.EAGER)
    private Set<MerchFactura> merchFacturas = new HashSet<>();

    public Merch() {
    }

    public Merch(ProductType productType,String name, String description, String image,
                                        List<String> talles, int stock, Double price,boolean status) {
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.image = image;
        this.talles = talles;
        this.stock = stock;
        this.price = price;
        this.status = status;
    }

    public Merch(ProductType productType,String name, String description, String image,
                                        int stock, Double price, boolean status) {
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getTalles() {
        return talles;
    }

    public void setTalles(List<String> talles) {
        this.talles = talles;
    }

    public Set<MerchFactura> getMerchFacturas() {
        return merchFacturas;
    }

    public void setMerchFacturas(Set<MerchFactura> merchFacturas) {
        this.merchFacturas = merchFacturas;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Merch;
import com.C4.lolapalooza.models.ProductType;

import java.util.ArrayList;
import java.util.List;

public class MerchDTO {
    private Long id;
    private ProductType productType;
    private String name;
    private String description;
    private String image;
    private List<String> talles = new ArrayList<>();
    private int stock;
    private Double price;
    private boolean status;

    public MerchDTO() {
    }

    public MerchDTO(Merch merch) {
        this.id = merch.getId();
        this.productType = merch.getProductType();
        this.name = merch.getName();
        this.description = merch.getDescription();
        this.image = merch.getImage();
        this.talles = merch.getTalles().isEmpty() ? null : merch.getTalles() ;
        this.stock = merch.getStock();
        this.price = merch.getPrice();
        this.status = merch.isStatus();
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

    public List<String> getTalles() {
        return talles;
    }

    public void setTalles(List<String> talles) {
        this.talles = talles;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

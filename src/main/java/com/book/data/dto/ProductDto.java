package com.book.data.dto;

import com.book.data.entity.Product;

import java.math.BigDecimal;

/**
 * Created by jotaiwan on 12/08/2017.
 */
public class ProductDto {
    private String code;
    private String name;
    private String brand;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;

    public ProductDto() {

    }

    public ProductDto(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.unitPrice = product.getUnitPrice();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}

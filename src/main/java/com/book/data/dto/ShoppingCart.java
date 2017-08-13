package com.book.data.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jotaiwan on 7/08/2017.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="session")
public class ShoppingCart {

    private List<ProductDto> productDtos = Collections.emptyList();
    private BigDecimal totalPrice;

    public void add(ProductDto product) {
        if (productDtos.isEmpty()) {
            productDtos = new ArrayList<>();
        }
        productDtos.add(product);
    }

    public void remove() {

    }

    public int totalItems() {
        return productDtos.size();
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

package com.book.data.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jotaiwan on 7/08/2017.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="session")
public class ShoppingCart implements Cart{

    List<ProductDto> productDtos;
    BigDecimal totalPrice;

    public void add() {

    }

    public void remove() {

    }

}

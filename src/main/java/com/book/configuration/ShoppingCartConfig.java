package com.book.configuration;

import com.book.data.dto.Cart;
import com.book.data.dto.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jotaiwan on 12/08/2017.
 */
@Configuration
public class ShoppingCartConfig {

    @Bean
    Cart cart() {
        return new ShoppingCart();
    }
}

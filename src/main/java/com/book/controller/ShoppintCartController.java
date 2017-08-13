package com.book.controller;

import com.book.data.dto.ProductDto;
import com.book.data.dto.ShoppingCart;
import com.book.data.entity.Product;
import com.book.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jotaiwan on 7/08/2017.
 */
@Controller
@Scope("session")
@RequestMapping("/shoppingCart")
public class ShoppintCartController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppintCartController.class);
    private static final String CART = "cart";

    @Autowired
    ProductService productService;

    private List<Product> products = Collections.emptyList();

    @RequestMapping(method = RequestMethod.GET)
    public String viewCart(Model model, HttpServletRequest request) {
        ShoppingCart shoppingCart = getCartFromSession(request);
        products = productService.findAllProducts();

        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("products", getNotSelectedProducts(getSelectedProductCodes(shoppingCart)));
        model.addAttribute("cartItems", shoppingCart.totalItems());
        return "/shoppingCart/viewCart";
    }

    @RequestMapping(value = "/add/{id}")
    public String addToCart(@PathVariable int id,  Model model, HttpServletRequest request) {
        ShoppingCart shoppingCart = getCartFromSession(request);
        shoppingCart.add(new ProductDto(getSelectedProduct(id)));

        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartItems", shoppingCart.totalItems());
        model.addAttribute("products", getNotSelectedProducts(getSelectedProductCodes(shoppingCart)));
        return "/shoppingCart/viewCart";
    }

    private ShoppingCart getCartFromSession(HttpServletRequest request) {
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(CART);
        if (shoppingCart == null) {
            request.getSession().setAttribute(CART, new ShoppingCart());
        }
        logger.info("Shoppingcart session: " + request.getSession().getId());
        return (ShoppingCart)request.getSession().getAttribute(CART);
    }

    private List<Product> getNotSelectedProducts(List<String> codes) {
        return products.stream().filter(p -> !codes.contains(p.getCode())).collect(Collectors.toList());
    }

    private List<String> getSelectedProductCodes(ShoppingCart cart) {
        return cart.getProductDtos().stream().map(ProductDto::getCode).collect(Collectors.toList());
    }

    private Product getSelectedProduct(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}

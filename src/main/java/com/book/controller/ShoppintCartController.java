package com.book.controller;

import com.book.data.dto.Cart;
import com.book.data.dto.ShoppingCart;
import com.book.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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
    Cart cart;

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewCart(Model model, HttpServletRequest request) {
        Cart cart = (ShoppingCart)request.getSession().getAttribute(CART);
        if (cart == null) {
            request.getSession().setAttribute("cart", cart);
        }
        logger.info("Shoppingcart session: " + request.getSession().getId());
        model.addAttribute("shoppingCart", request.getSession().getAttribute(CART));
        model.addAttribute("products", productService.findAllProducts());
        return "/shoppingCart/viewCart";
    }

}

package com.book.controller;

import com.book.data.dto.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by jotaiwan on 7/08/2017.
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppintCartController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppintCartController.class);

    @Autowired
    ShoppingCart shoppingCart;

    @RequestMapping(value="/view")
    public String viewCart(Model model) {
        logger.info("Shoppingcart session: " + shoppingCart.hashCode());
        model.addAttribute("shoppingCart", shoppingCart);
        return "/shoppingCart/viewCart";
    }


}

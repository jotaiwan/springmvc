package com.book.controller;

import com.book.data.entity.Product;
import com.book.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jotaiwan on 12/08/2017.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("total", products.size());

        return "products";
    }
}

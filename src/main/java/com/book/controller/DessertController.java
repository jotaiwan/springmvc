package com.book.controller;

import com.book.annotation.Cold;
import com.book.annotation.Fruity;
import com.book.data.dto.dessert.Dessert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jotaiwan on 15/07/2017.
 */
@Controller
@RequestMapping("/dessert")
public class DessertController {

    @Autowired
    @Cold
    @Fruity
    private Dessert dessert;

    @RequestMapping(value="/{type}")
    public String defaultDessert(@PathVariable String type, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("dessertDescription", dessert.description());
        return "dessert";
    }
}

package com.book.controller;

import com.book.concert.Performance;
import com.book.service.ConcertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jotaiwan on 2/08/2017.
 */
@Controller
@RequestMapping(value = "/concert")
public class ConcertController {
    private static final Logger logger = LoggerFactory.getLogger(ConcertController.class);

    @Autowired
    private ConcertService concertService;

    @RequestMapping(value = "/{artist}", method = RequestMethod.GET)
    public String perform(@PathVariable String artist, Model model) {
        logger.info("Start performing concert");
        concertService.perform();
        model.addAttribute("start", artist + " has started performing");
        return "concert";
    }

}

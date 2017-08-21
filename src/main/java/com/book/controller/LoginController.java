package com.book.controller;

import com.book.data.dto.LoginDto;
import com.book.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping("/all")
    public String loginManager(Model model) {
        List<LoginDto> logins = loginService.findAll();
        model.addAttribute("logins", logins);

        logger.info("Total logins is {}", logins.size());
        return "loginManager";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String adminStudent(@PathVariable int id, Model model) {

        LoginDto login = loginService.findById(id);
        model.addAttribute("login", login);
        model.addAttribute("mode", "edit");
        return "loginManager";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        LoginDto login = new LoginDto();
        model.addAttribute("login", login);
        model.addAttribute("mode", "add");
        return "loginManager";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("login") LoginDto login, BindingResult result, SessionStatus status) {
        loginService.save(login);
        return "redirect:" + "/login/all";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("login") LoginDto login, BindingResult result, SessionStatus status) {
        loginService.update(login);
        return "redirect:" + "/login/all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@ModelAttribute("login") LoginDto login, BindingResult result, SessionStatus status) {
        int res = loginService.delete(login.getId());
        return "redirect:" + "/login/all";
    }
}

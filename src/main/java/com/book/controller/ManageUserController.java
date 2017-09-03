package com.book.controller;

import com.book.data.dto.LoginDto;
import com.book.service.LoginService;
import com.book.validator.LoginValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Controller
@RequestMapping(value="/manageuser")
public class ManageUserController {
    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    LoginValidator loginValidator;

    @RequestMapping("/all")
    public String allUsers(Model model) {
        List<LoginDto> logins = loginService.findAll();
        model.addAttribute("logins", logins);
        model.addAttribute("userSize", logins.size());
        logger.info("Total logins is {}", logins.size());
        return "manageUser";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable int id, Model model) {
        LoginDto login = loginService.findById(id);
        model.addAttribute("login", login);
        model.addAttribute("mode", "edit");
        return "manageUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        LoginDto login = new LoginDto();
        model.addAttribute("login", login);
        model.addAttribute("mode", "add");
        return "manageUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("login") LoginDto login, BindingResult result,
        SessionStatus status, ModelMap model) {
        loginService.save(login);
        return "redirect:" + "/manageuser/all";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("login") LoginDto login, BindingResult result,
        SessionStatus status, Model model) {

        loginValidator.validate(login, result);
        if (result.hasErrors()) {
            LoginDto newLogin = new LoginDto(login.getId(), login.getUsername(), login.getUser());
            newLogin.setPasswordReset(login.isPasswordReset());
            model.addAttribute("mode", "edit");
            return "manageUser";
        }
        loginService.update(login);
        return "redirect:" + "/manageuser/all";
    }

    @RequestMapping(value = "/delete/${id}", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("login") LoginDto login, BindingResult result, SessionStatus status) {
        int res = loginService.delete(login.getId());
        return "redirect:" + "/manageuser/all";
    }
}

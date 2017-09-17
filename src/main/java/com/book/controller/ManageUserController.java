package com.book.controller;

import com.book.adapter.UserAccountAdapter;
import com.book.data.bucket.FileBucket;
import com.book.data.dto.LoginDto;
import com.book.data.dto.UserAccountDto;
import com.book.data.dto.UserDocumentDto;
import com.book.data.entity.UserAccountJson;
import com.book.data.entity.UserDocument;
import com.book.service.LoginService;
import com.book.service.UserAccountService;
import com.book.service.UserDocumentService;
import com.book.validator.LoginValidator;
import com.book.view.LoginDetailFormView;
import com.book.view.UserAccountFormView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Controller
@RequestMapping(value="/manageuser")
public class ManageUserController {
    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    private static final String LOGIN_FORM_VIEW = "login";
    private static final String USER_FORM_VIEW = "account";

    @Autowired
    LoginService loginService;

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    UserAccountAdapter userAccountAdapter;

    @InitBinder("login")
    public void initLoginBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @RequestMapping("/all")
    public String allUsers(Model model) {
        List<LoginDto> logins = loginService.findAll();
        logins.sort(Comparator.comparing(LoginDto::getId));
        model.addAttribute("logins", logins);
        model.addAttribute("userSize", logins.size());
        logger.info("Total logins is {}", logins.size());
        return "manageUser";
    }

    @RequestMapping(value = "/login/edit/{id}", method = RequestMethod.GET)
    public String editLogin(@PathVariable int id, Model model) {
        LoginDetailFormView login = loginService.findLoginById(id);
        model.addAttribute("login", login);
        model.addAttribute("mode", "edit");
        model.addAttribute("view", LOGIN_FORM_VIEW);
        return "manageUser";
    }

    @RequestMapping(value = "/account/edit/{userId}", method = RequestMethod.GET)
    public String editDetail(@PathVariable int userId, Model model) {
        UserAccountFormView user = userAccountService.findUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("mode", "edit");
        model.addAttribute("view", USER_FORM_VIEW);
        return "manageUser";
    }

    @RequestMapping(value = "/register/1", method = RequestMethod.GET)
    public String registerAccount(Model model) {
        UserAccountFormView user = new UserAccountFormView();
        model.addAttribute("account", user);
        model.addAttribute("mode", "registerUser");
        return "registration";
    }

    @RequestMapping(value = "/register/2", method = RequestMethod.POST)
    public String registerLogin(@ModelAttribute("account") UserAccountFormView account, Model model) {
        try {
            // validate the input first and make sure that there is no repeat
            UserAccountJson userAccountJson =
                    userAccountAdapter.convertUserAccountFormViewToJson(account);
            if (userAccountService.findUserAccountJsonByJson(userAccountJson)) {
                model.addAttribute("warning", "The account is exist!");
                model.addAttribute("account", account);
            } else {
                int userAccountJsonId = userAccountService.saveUserAccountFormAsJson(userAccountJson);
                LoginDetailFormView login = new LoginDetailFormView(userAccountJsonId);
                model.addAttribute("login", login);
            }

            model.addAttribute("mode", "registerLogin");
        } catch (Exception e) {
            logger.error("Failed to save UserAccountJson: ", e);
            model.addAttribute(account);
            model.addAttribute("mode", "registerUser");
        }
        return "registration";
    }

    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("login") LoginDetailFormView login, BindingResult result,
        SessionStatus status, Model model) {
//        loginService.save(login);
//        return "redirect:" + "/manageuser/all";
        model.addAttribute("mode", "registerDone");
        return "registration";
    }

    @RequestMapping(value = "/login/update/{id}", method = RequestMethod.POST)
    public String updateLogin(@Valid @ModelAttribute("login") LoginDetailFormView login,
                  BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("view", LOGIN_FORM_VIEW);
            return "manageUser";
        }
        loginService.update(login);
        attributes.addFlashAttribute("alertMessage", login.getUsername() + "'s password has been updatedd successfully.");
        attributes.addFlashAttribute("alertType", "success");
        return "redirect:" + "/manageuser/all";
    }

    @RequestMapping(value = "/account/update/{id}", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") UserAccountFormView user,
                 BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("view", USER_FORM_VIEW);
            return "manageUser";
        }
        loginService.update(user);
        String username = user.getFirstName() + " " + user.getLastName();
        attributes.addFlashAttribute("alertMessage", "Account " + username + "has been updatedd successfully.");
        attributes.addFlashAttribute("alertType", "success");
        return "redirect:" + "/manageuser/all";
    }

    @RequestMapping(value = "/delete/${id}", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("login") LoginDto login, BindingResult result, SessionStatus status) {
        int res = loginService.delete(login.getId());
        return "redirect:" + "/manageuser/all";
    }

    @RequestMapping(value = { "/document-{userId}" }, method = RequestMethod.GET)
    public String addDocuments(@PathVariable int userId, ModelMap model) {
        UserAccountDto user = userAccountService.findById(userId);
        model.addAttribute("user", user);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocumentDto> documents = userDocumentService.findAllByUserId(userId);
        model.addAttribute("documents", documents);

        model.addAttribute("mode", "document");
        return "manageUser";
    }

    @RequestMapping(value = { "/document-{userId}" }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result,
                 ModelMap model, @PathVariable int userId, RedirectAttributes attributes) throws IOException{

        if (result.hasErrors()) {
            System.out.println("validation errors");
            UserAccountDto user = userAccountService.findById(userId);
            model.addAttribute("user", user);

            List<UserDocumentDto> documents = userDocumentService.findAllByUserId(userId);
            model.addAttribute("documents", documents);

            return "managedocuments";
        } else {

            System.out.println("Fetching file");

            UserAccountDto user = userAccountService.findById(userId);
            model.addAttribute("user", user);

            try {
                saveDocument(fileBucket, user);
            } finally {
                attributes.addFlashAttribute("alertMessage", fileBucket.getFile().getOriginalFilename() + " has been uploaded successfully.");
                attributes.addFlashAttribute("alertType", "success");
            }
            return "redirect:/manageuser/document-" + userId;
        }
    }

    @RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int userId, @PathVariable int docId,
                                   HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/manageuser/document-" + userId;
    }

    @RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
        userDocumentService.deleteById(docId);
        return "redirect:/manageuser/document-" + userId;
    }


    private void saveDocument(FileBucket fileBucket, UserAccountDto user) throws IOException{

        UserDocumentDto document = new UserDocumentDto();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setUser(user);
        userDocumentService.saveDocument(document);
    }
}

package com.book.controller;

import com.book.data.bucket.FileBucket;
import com.book.data.dto.UserAccountDto;
import com.book.data.dto.UserDocumentDto;
import com.book.data.entity.UserAccount;
import com.book.data.entity.UserDocument;
import com.book.service.UserAccountService;
import com.book.service.UserDocumentService;
import com.book.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Controller
@RequestMapping(value="/user")
public class FileBucketController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<UserAccountDto> users = userAccountService.findAllUsers();
        model.addAttribute("users", users);
        return "userslist";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserAccountDto user = new UserAccountDto();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid UserAccountDto user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        int userId = userAccountService.saveUser(user);
        user.setId(userId);

        model.addAttribute("user", user);
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        UserAccountDto user = userAccountService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid UserAccountDto user, BindingResult result,
                             ModelMap model, @PathVariable int id) {

        if (result.hasErrors()) {
            return "registration";
        }

        userAccountService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        return "registrationsuccess";
    }


    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        userAccountService.deleteUserById(id);
        return "redirect:/list";
    }



    @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
    public String addDocuments(@PathVariable int userId, ModelMap model) {
        UserAccountDto user = userAccountService.findById(userId);
        model.addAttribute("user", user);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocumentDto> documents = userDocumentService.findAllByUserId(userId);
        model.addAttribute("documents", documents);

        return "managedocuments";
    }


    @RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int userId, @PathVariable int docId,
                                   HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/add-document-"+userId;
    }

    @RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
        userDocumentService.deleteById(docId);
        return "redirect:/add-document-"+userId;
    }

    @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result,
             ModelMap model, @PathVariable int userId) throws IOException{

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

            saveDocument(fileBucket, user);

            return "redirect:/add-document-"+userId;
        }
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

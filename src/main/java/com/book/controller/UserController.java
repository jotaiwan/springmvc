package com.book.controller;

import com.book.adapter.UserAccountAdapter;
import com.book.data.bucket.FileBucket;
import com.book.data.dto.UserDocumentForm;
import com.book.data.entity.UserAccount;
import com.book.data.entity.UserDocument;
import com.book.data.form.LoginDetailForm;
import com.book.data.form.UserAccountForm;
import com.book.data.view.UserInfo;
import com.book.service.LoginService;
import com.book.service.UserAccountService;
import com.book.service.UserDocumentService;
import com.book.util.PageMessageUtil;
import com.book.validator.AccountValidator;
import com.book.validator.LoginValidator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jotaiwan on 29/07/2017.
 * The main objects will be
 * 1. Login form (Login Detail, eg. username)
 * 2. Account form (Account Detail, eg. first name etc)
 */
@Controller
@RequestMapping(value=SiteUrls.USER.ROOT)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String LOGIN_FORM_VIEW = "login";
    private static final String USER_FORM_VIEW = "account";

    @Autowired
    LoginService loginService;

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    AccountValidator accountValidator;

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

    @InitBinder("account")
    protected void initAccountBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    /**
     * Display all users
     * */
    @RequestMapping(value = { "/", "/list", SiteUrls.USER.ALL })
    public String allUsers(Model model) {
        List<UserInfo> userInfos = userAccountService.findAllUsers();
        userInfos.sort(Comparator.comparing(UserInfo::getFirstName));

        model.addAttribute("users", userInfos);
        model.addAttribute("userSize", userInfos.size());
        model.addAttribute("mode", "all");
        logger.info("Total users is {}", userInfos.size());
        return SiteUrls.USER.MANAGER;
    }

    /**
     * Change Login Detail
     * */
    @RequestMapping(value = SiteUrls.USER.LOGIN_EDIT + "/{id}", method = RequestMethod.GET)
    public String editLogin(@PathVariable int id, Model model, RedirectAttributes attributes) {
        LoginDetailForm login = loginService.findFormByUserId(id);
        if (login == null) {
            model.addAttribute("mode", "");
            return "redirect:" + "/user/all";
        }
        model.addAttribute("login", login);
        model.addAttribute("mode", "edit");
        model.addAttribute("view", LOGIN_FORM_VIEW);
        return SiteUrls.USER.MANAGER;
    }

    /**
     * Change Account Detail
     * */
    @RequestMapping(value = SiteUrls.USER.ACCOUNT_EDIT + "/{userId}", method = RequestMethod.GET)
    public String editDetail(@PathVariable int userId, Model model) {
        UserAccountForm user = userAccountService.findFormById(userId);
        model.addAttribute("user", user);
        model.addAttribute("mode", "edit");
        model.addAttribute("view", USER_FORM_VIEW);
        return SiteUrls.USER.MANAGER;
    }

    /**
     * Register step 1: create register account
     * */
    @RequestMapping(value = SiteUrls.USER.REGISTER_ACCOUNT, method = RequestMethod.GET)
    public String registerAccount(Model model) {
        UserAccountForm user = new UserAccountForm();
        model.addAttribute("account", user);
        model.addAttribute("mode", "registerAccount");
        return SiteUrls.USER.REGISTRATION;
    }

    /**
     * Register step 2: create register login
     * */
    @RequestMapping(value = SiteUrls.USER.REGISTER_LOGIN, method = RequestMethod.POST)
    public String registerLogin(@Valid @ModelAttribute("account") UserAccountForm account, BindingResult result, Model model) {
        try {
            // validate the input first
            if (result.hasErrors()) {
                model.addAttribute("mode", "registerAccount");
            } else {
                if (userAccountService.findUserAccountJsonByJson(account)) {
                    model.addAttribute("warning", "The account is exist!");
                    model.addAttribute("account", account);
                } else {
                    int userAccountJsonId = userAccountService.saveUserAccountFormAsJson(account);
                    LoginDetailForm login = new LoginDetailForm(userAccountJsonId);
                    model.addAttribute("login", login);
                }

                model.addAttribute("mode", "registerLogin");
            }
        } catch (Exception e) {
            logger.error("Failed to save UserAccountJson: ", e);
            model.addAttribute(account);
            model.addAttribute("mode", "registerAccount");
        }
        return SiteUrls.USER.REGISTRATION;
    }

    /**
     * Register step 3: save account & login detail
     * */
    @RequestMapping(value = SiteUrls.USER.REGISTER_SAVE, method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("login") LoginDetailForm login, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("mode", "registerLogin");
        } else {
            // retrieve saved account json
            UserAccount userAccount = null;
            try {
                userAccount = userAccountService.finUserAccountJsonById(login.getAccountJsonId());
            } catch(Exception e) {
                logger.error("Failed to load user account json detail! id is {}", login.getAccountJsonId(), e);
            }

            if (userAccount != null) {
                // start save procedure
                try {
                    // start saving user account
                    int userAccountId = userAccountService.saveUser(userAccount);
                    // start saving login detail
                    loginService.save(login, userAccount);
                    model = PageMessageUtil.success(model, "registerDone", "Your request has been successful!");
                } catch (Exception e) {
                    // please contact system admin
                    logger.error("Saving user {} failed", userAccount.getFirstName() + " " + userAccount.getLastName(), e);
                    model = PageMessageUtil.error(model, "Your registration is failed, please contact system administrator!");
                }
            }
        }
        return SiteUrls.USER.REGISTRATION;
    }

    /**
     * Save login change
     * */
    @RequestMapping(value = SiteUrls.USER.LOGIN_UPDATE, method = RequestMethod.POST)
    public String updateLogin(@Valid @ModelAttribute("login") LoginDetailForm login,
                  BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("view", LOGIN_FORM_VIEW);
            return SiteUrls.USER.MANAGER;
        }
        loginService.update(login);
        attributes.addFlashAttribute("alertMessage", login.getUsername() + "'s password has been updatedd successfully.");
        attributes.addFlashAttribute("alertType", "success");
        return "redirect:" + SiteUrls.USER.ROOT + SiteUrls.USER.ALL;
    }

    /**
     * Save account change
     * */
    @RequestMapping(value = SiteUrls.USER.ACCOUNT_UPDATE, method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") UserAccountForm user,
                 BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("view", USER_FORM_VIEW);
            return SiteUrls.USER.ROOT + "/manageUser";
        }
        loginService.update(user);
        String username = user.getFirstName() + " " + user.getLastName();
        attributes.addFlashAttribute("alertMessage", "Account " + username + "has been updatedd successfully.");
        attributes.addFlashAttribute("alertType", "success");
        return "redirect:" + "/user/all";
    }

    /**
     * Delete user
     * */
    @RequestMapping(value = SiteUrls.USER.DELETE_USER, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int userId, Model model, RedirectAttributes attributes) {
        UserInfo user = userAccountService.findInfoById(userId);
        int userResult = userAccountService.deleteAccountById(user.getId());
        int loginResult = loginService.delete(user.getLoginId());

        String userFullName = user.getFirstName() + " " + user.getLastName();
        if (userResult != 0 && loginResult != 0) {
            attributes.addFlashAttribute("alertMessage", userFullName + " has been deleted successfully.");
            attributes.addFlashAttribute("alertType", "success");
        } else {
            attributes.addFlashAttribute("alertMessage", "There is an issue for remove " + userFullName + ", please contact system administrator.");
            attributes.addFlashAttribute("alertType", "error");
        }
        return "redirect:" + "/user/all";
    }

    /**
     * Display user document
     * */
    @RequestMapping(value = { SiteUrls.USER.DOCUMENT }, method = RequestMethod.GET)
    public String addDocument(@PathVariable int userId, ModelMap model) {
        UserInfo user = userAccountService.findInfoById(userId);
        model.addAttribute("user", user);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocumentForm> documents = userDocumentService.findAllByUserId(userId);
        model.addAttribute("documents", documents);

        model.addAttribute("mode", "document");
        return SiteUrls.USER.MANAGER;
    }

    /**
     * Upload the file/document
     * */
    @RequestMapping(value = { SiteUrls.USER.DOCUMENT }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result,
                 ModelMap model, @PathVariable int userId, RedirectAttributes attributes) throws IOException{

        UserInfo user = userAccountService.findInfoById(userId);
        if (result.hasErrors()) {
            model.addAttribute("user", user);

            List<UserDocumentForm> documents = userDocumentService.findAllByUserId(userId);
            model.addAttribute("documents", documents);

            return SiteUrls.USER.DOCUMENT;
        } else {
            logger.info("Fetching file for user: {}", user.getId());
            model.addAttribute("user", user);

            try {
                saveDocument(fileBucket, user);
            } finally {
                attributes.addFlashAttribute("alertMessage", fileBucket.getFile().getOriginalFilename() + " has been uploaded successfully.");
                attributes.addFlashAttribute("alertType", "success");
            }
            return "redirect:" + SiteUrls.USER.ROOT + SiteUrls.USER.DOCUMENT;
        }
    }

    /**
     * Download file/document
     * */
    @RequestMapping(value = { SiteUrls.USER.DOCUMENT_DOWNLOAD }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int userId, @PathVariable int docId,
                                   HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:" + SiteUrls.USER.ROOT + SiteUrls.USER.DOCUMENT;
    }

    /**
     * Delete file/document
     * */
    @RequestMapping(value = { SiteUrls.USER.DOCUMENT_DELETE}, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int userId, @PathVariable int docId, RedirectAttributes attributes) {
        String fileName = "";
        try {
            UserDocument userDocument = userDocumentService.findById(docId);
            fileName = userDocument.getName();
            userDocumentService.deleteById(docId);
        } finally {
            attributes.addFlashAttribute("alertMessage", fileName + " has been deleted.");
            attributes.addFlashAttribute("alertType", "success");
        }
        return "redirect:" + SiteUrls.USER.ROOT + SiteUrls.USER.DOCUMENT;
    }

    /**
     * Save file/document to database
     * */
    private void saveDocument(FileBucket fileBucket, UserInfo user) throws IOException{

        UserDocumentForm document = new UserDocumentForm();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setUser(user);
        userDocumentService.saveDocument(document);
    }
}

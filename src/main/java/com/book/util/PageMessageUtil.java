package com.book.util;

import org.springframework.ui.Model;

public class PageMessageUtil {

    private static final String SUCCESS = "success";
    private static final String WARNING = "warning";
    private static final String ERROR = "danger";

    public static Model success(Model model, String mode, String message) {
        model.addAttribute("alertType", SUCCESS);
        model.addAttribute("mode", mode);
        model.addAttribute("alertMessage", message);
        return model;
    }

    public static Model success(Model model, String message) {
        model.addAttribute("alertType", SUCCESS);
        model.addAttribute("alertMessage", message);
        return model;
    }

    public static Model warning(Model model, String message) {
        model.addAttribute("alertType", WARNING);
        model.addAttribute("alertMessage", message);
        return model;
    }

    public static Model error(Model model, String message) {
        model.addAttribute("alertType", ERROR);
        model.addAttribute("alertMessage", message);
        return model;
    }

}

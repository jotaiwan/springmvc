package com.book.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class PageMessageUtil {

    private static final String SUCCESS = "success";
    private static final String WARNING = "warning";
    private static final String ERROR = "danger";

    public static Model error(Model model, String message) {
        model.addAttribute("alertType", ERROR);
        model.addAttribute("alertMessage", message);
        return model;
    }

    public static void attributesSuccess(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("alertType", SUCCESS);
        attributes.addFlashAttribute("alertMessage", message);
    }

    public static void attributesError(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("alertType", ERROR);
        attributes.addFlashAttribute("alertMessage", message);
    }

}

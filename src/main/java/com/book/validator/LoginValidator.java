package com.book.validator;

import com.book.service.LoginService;
import com.book.view.LoginDetailFormView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by jotaiwan on 2/09/2017.
 */
@Component
public class LoginValidator implements Validator {

    @Autowired
    LoginService loginServic;

    @Override
    public boolean supports(Class<?> clazz) {
        //just validate the Login instances
        return LoginDetailFormView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginDetailFormView login = (LoginDetailFormView) o;

        if (StringUtils.isEmpty((login.getCurrentPassword()))) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                    "required.password", "Current Password is required.");
        } else if (isCurrentPasswordNotCorrect(login)){
            errors.rejectValue("currentPassword",
                    "password.current.incorrect", "Current Password Incorrect.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Password is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Confirm Password is required.");

        if(!(login.getPassword().equals(login.getConfirmPassword()))){
            errors.rejectValue("password", "password.not.matched", "Password does not match.");
        }
    }

    private boolean isCurrentPasswordNotCorrect(LoginDetailFormView login) {
        return !loginServic.isCurrentPasswordCorrect(login);
    }
}

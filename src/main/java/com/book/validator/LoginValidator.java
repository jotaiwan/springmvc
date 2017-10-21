package com.book.validator;

import com.book.data.form.LoginDetailForm;
import com.book.service.LoginService;
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
    LoginService loginService;

    @Override
    public boolean supports(Class<?> clazz) {
        //just validate the Login instances
        return LoginDetailForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginDetailForm login = (LoginDetailForm) o;

        if (login.getId() == null) {
            // validate add new login
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                    "required.username", "Username is required.");

            if (isUsernameExist(login)) {
                errors.rejectValue("username", "username.already.exist", "Username is already exist.");
            }

        } else {
            // validate login update
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                    "current.password.required", "Current password is required.");

            if (StringUtils.isNotEmpty(login.getCurrentPassword()) && isCurrentPasswordNotCorrect(login)) {
                // check current password is matched to database or not
                errors.rejectValue("currentPassword", "current.password.incorrect", "Current password is incorrect.");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Password is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Confirm Password is required.");

        if(!(login.getPassword().equals(login.getConfirmPassword()))){
            errors.rejectValue("password", "password.not.matched", "Password does not match.");
        }
    }

    private boolean isCurrentPasswordNotCorrect(LoginDetailForm login) {
        return !loginService.isCurrentPasswordCorrect(login);
    }

    private boolean isUsernameExist(LoginDetailForm login) {
        return loginService.isUsernameExist(login);
    }
}

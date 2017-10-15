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

//        if (StringUtils.isEmpty((login.getCurrentPassword()))) {
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
//                    "required.password", "Current Password is required.");
//        } else if (isCurrentPasswordNotCorrect(login)){
//            errors.rejectValue("currentPassword",
//                    "password.current.incorrect", "Current Password Incorrect.");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "required.username", "Username is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Password is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Confirm Password is required.");

        if(!(login.getPassword().equals(login.getConfirmPassword()))){
            errors.rejectValue("password", "password.not.matched", "Password does not match.");
        }

//        if (isUsernameExist(login)) {
//            errors.rejectValue("username", "username.already.exist", "Username is already exist.");
//        }
    }

//    private boolean isCurrentPasswordNotCorrect(LoginDetailForm login) {
//        return !loginService.isCurrentPasswordCorrect(login);
//    }

    private boolean isUsernameExist(LoginDetailForm login) {
        return loginService.isUsernameExist(login);
    }
}

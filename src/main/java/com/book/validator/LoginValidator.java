package com.book.validator;

import com.book.Repository.LoginRepository;
import com.book.data.dto.LoginDto;
import com.book.data.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by jotaiwan on 2/09/2017.
 */
public class LoginValidator implements Validator {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        //just validate the Customer instances
        return LoginDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginDto login = (LoginDto) o;

        if (login.isPasswordReset()) {
            if (StringUtils.isEmpty((login.getCurrentPassword()))) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                        "required.password", "Field name is required.");
            } else if (isCurrentPasswordNotCorrect(login)){
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                        "password.current.incorrect", "Current Password Incorrect.");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        if(!(login.getPassword().equals(login.getConfirmPassword()))){
            errors.rejectValue("password", "password.not.matched", "Password does not match.");
        }
    }

    private boolean isCurrentPasswordNotCorrect(LoginDto login) {
        Login db = loginRepository.findById(login.getId());
        return !StringUtils.equalsIgnoreCase(login.getCurrentPassword(), db.getPassword());
    }
}

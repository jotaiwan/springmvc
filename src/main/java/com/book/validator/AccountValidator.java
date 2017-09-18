package com.book.validator;

import com.book.data.form.LoginDetailForm;
import com.book.data.form.UserAccountForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        //just validate the Login instances
        return LoginDetailForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserAccountForm account = (UserAccountForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
            "required.firstName", "First name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
            "required.lastName", "Last name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress",
            "required.emailAddress", "Email Address is required.");

    }
}

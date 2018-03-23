package com.book.validator;

import com.book.data.form.LoginDetailForm;
import com.book.data.form.UserAccountForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        //just validate the Login instances
        return UserAccountForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserAccountForm account = (UserAccountForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
            "required.firstName", "First name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
            "required.lastName", "Last name is required.");

        if (StringUtils.isBlank(((UserAccountForm) o).getEmailAddress())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress",
                    "required.emailAddress", "Email Address is required.");
        } else if (!isValidEmailAddress(((UserAccountForm) o).getEmailAddress())){
            errors.rejectValue("emailAddress", "email.address.invalid", "Email address is invalid.");
        }
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}

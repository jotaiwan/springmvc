package com.book.adapter;

import com.book.data.entity.UserAccount;
import com.book.data.entity.UserAccountJson;
import com.book.data.form.UserAccountForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by jotaiwan on 17/09/2017.
 */
@Service
public class UserAccountAdapter {
    public UserAccountJson convertUserAccountFormViewToJson(UserAccountForm userAccountForm) {

        ObjectMapper mapper = new ObjectMapper();

        UserAccountJson userAccountJson = new UserAccountJson();
        try {
            // Convert object to JSON string
//            userAccountForm.setId(userAccountForm.getId() == 0 ? null : userAccountForm.getId());
            userAccountJson.setJson(mapper.writeValueAsString(userAccountForm));

            // Convert object to JSON string and pretty print
//            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userAccountForm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userAccountJson;
    }

    public UserAccount convertUserAccountJsonToUserAccount(UserAccountJson userAccountJson) {

        ObjectMapper mapper = new ObjectMapper();

        UserAccount userAccount = new UserAccount();
        try {
            userAccount = mapper.readValue(userAccountJson.getJson(), UserAccount.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userAccount;
    }
}

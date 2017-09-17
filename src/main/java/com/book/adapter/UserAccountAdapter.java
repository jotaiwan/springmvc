package com.book.adapter;

import com.book.data.entity.UserAccountJson;
import com.book.view.UserAccountFormView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * Created by jotaiwan on 17/09/2017.
 */
@Service
public class UserAccountAdapter {
    public UserAccountJson convertUserAccountFormViewToJson(UserAccountFormView userAccountFormView) {

        ObjectMapper mapper = new ObjectMapper();

        UserAccountJson userAccountJson = new UserAccountJson();
        try {
            // Convert object to JSON string
            userAccountJson.setJson(mapper.writeValueAsString(userAccountFormView));

            // Convert object to JSON string and pretty print
//            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userAccountFormView);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userAccountJson;
    }
}

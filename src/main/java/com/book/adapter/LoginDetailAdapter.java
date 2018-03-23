package com.book.adapter;

import com.book.data.entity.Login;
import com.book.data.entity.UserAccount;
import com.book.data.form.LoginDetailForm;
import org.springframework.stereotype.Service;

/**
 * Created by jotaiwan on 18/09/2017.
 */
@Service
public class LoginDetailAdapter {

    public Login converFormToLogin(LoginDetailForm loginForm, UserAccount userAccount) {
        Login login = new Login();
        login.setUsername(loginForm.getUsername());
        login.setPassword(loginForm.getPassword());
        login.setUser(userAccount);
        return login;
    }

}

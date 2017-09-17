package com.book.view;

import com.book.data.entity.UserAccount;

import javax.persistence.Transient;

/**
 * Created by jotaiwan on 16/09/2017.
 */
public class LoginDetailFormView {
    private int id;
    private String username;
    private String password;
    private int accountJsonId;

    @Transient
    private String currentPassword;
    @Transient
    private String confirmPassword;
    @Transient
    private boolean passwordReset;

    public LoginDetailFormView() {}

    public LoginDetailFormView(int id, String username, UserAccount user) {
        this.id = id;
        this.username = username;
        this.setPassword(getPassword());
    }

    public LoginDetailFormView(int accountJsonId) {
        this.accountJsonId = accountJsonId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(boolean passwordReset) {
        this.passwordReset = passwordReset;
    }

    public int getAccountJsonId() {
        return accountJsonId;
    }

    public void setAccountJsonId(int accountJsonId) {
        this.accountJsonId = accountJsonId;
    }
}

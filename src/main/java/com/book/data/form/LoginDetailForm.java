package com.book.data.form;

import com.book.data.entity.UserAccount;

import javax.persistence.Transient;

/**
 * Created by jotaiwan on 16/09/2017.
 */
public class LoginDetailForm {
    private Integer id;
    private String username;
    private String password;
    private int accountJsonId;

    @Transient
    private String currentPassword;
    @Transient
    private String confirmPassword;
    @Transient
    private boolean passwordReset;

    public LoginDetailForm() {}

    public LoginDetailForm(int id, String username, UserAccount user) {
        this.id = id;
        this.username = username;
        this.setPassword(getPassword());
    }

    public LoginDetailForm(int accountJsonId) {
        this.accountJsonId = accountJsonId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

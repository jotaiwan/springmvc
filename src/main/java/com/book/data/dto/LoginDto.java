package com.book.data.dto;

import com.book.data.entity.UserAccount;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

/**
 * Created by jotaiwan on 30/07/2017.
 */
public class LoginDto {
    private int id;
    private String username;
    private String password;
    private UserAccount user;

    @Transient
    private String currentPassword;
    @Transient
    private String confirmPassword;
    @Transient
    private boolean passwordReset;

    public LoginDto() {}

    public LoginDto(int id, String username, UserAccount user) {
        this.id = id;
        this.username = username;
        this.setPassword(getPassword());
        this.setUser(user);
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

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
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
        passwordReset = passwordReset;
    }
}

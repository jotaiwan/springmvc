package com.book.data.dto;

/**
 * Created by jotaiwan on 30/07/2017.
 */
public class LoginDto {
    private int id;
    private String username;
    private String emailAddress;
    private String password;


    public LoginDto() {

    }

    public LoginDto(int id, String username) {
        this.id = id;
        this.username = username;
        this.setPassword(getPassword());
        this.setEmailAddress(getEmailAddress());
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

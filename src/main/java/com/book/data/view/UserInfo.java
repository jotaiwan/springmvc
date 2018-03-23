package com.book.data.view;

import com.book.data.entity.UserAccount;

/**
 * Created by jotaiwan on 24/09/2017.
 */
public class UserInfo {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int loginId;
    private String userName;

    public UserInfo() {

    }

    public UserInfo(UserAccount user) {
        this.setId(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.loginId = user.getLogin().getId();
        this.userName = user.getLogin().getUsername();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }
}

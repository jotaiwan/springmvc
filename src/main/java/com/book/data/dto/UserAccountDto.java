package com.book.data.dto;

import com.book.data.entity.UserAccount;

/**
 * Created by jotaiwan on 20/08/2017.
 */
public class UserAccountDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    public UserAccountDto() {

    }

    public UserAccountDto(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.firstName = userAccount.getFirstName();
        this.lastName = userAccount.getLastName();
        this.emailAddress = userAccount.getEmailAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

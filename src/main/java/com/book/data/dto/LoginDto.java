package com.book.data.dto;

/**
 * Created by jotaiwan on 30/07/2017.
 */
public class LoginDto {
    private int id;
    private String username;

    public LoginDto() {}

    public LoginDto(int id, String username) {
        this.id = id;
        this.username = username;
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
}

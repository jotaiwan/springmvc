package com.book.data.entity;

import javax.persistence.*;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Entity
@Table(name="login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Login))
            return false;
        Login other = (Login) obj;
        if (getId() != other.getId())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Login [id=" + getId() + ", name=" + getUsername() + "]";
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

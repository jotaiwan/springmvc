package com.book.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Entity
@Table(name="USER_ACCOUNT")
public class UserAccount {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    @NotEmpty
    @Column(name="LAST_NAME", nullable=false)
    private String lastName;

    @NotEmpty
    @Column(name="EMAIL_ADDRESS", nullable=false)
    private String emailAddress;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    private Login login;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "USER_ID")
//    private Login login;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<UserDocument> userDocuments = new HashSet<UserDocument>();

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

    public Login getLogin() {
        return login;
    }

    public void setLoginDetail(Login login) {
        this.login = login;
    }

    public Set<UserDocument> getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(Set<UserDocument> userDocuments) {
        this.userDocuments = userDocuments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserAccount))
            return false;
        UserAccount other = (UserAccount) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + emailAddress + "]";
    }

}

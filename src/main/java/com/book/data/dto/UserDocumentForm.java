package com.book.data.dto;

import com.book.data.entity.UserDocument;
import com.book.data.view.UserInfo;

/**
 * Created by jotaiwan on 20/08/2017.
 */
public class UserDocumentForm {

    private Integer id;

    private String name;

    private String description;

    private String type;

    private byte[] content;

    private UserInfo user;

    public UserDocumentForm() {

    }

    public UserDocumentForm(UserDocument userDocument) {
        this.id = userDocument.getId();
        this.name = userDocument.getName();
        this.description = userDocument.getDescription();
        this.type = userDocument.getType();
        this.content = userDocument.getContent();
        this.user = new UserInfo(userDocument.getUser());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}

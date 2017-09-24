package com.book.data.form;

import com.book.data.dto.UserAccountDto;
import com.book.data.entity.UserDocument;

/**
 * Created by jotaiwan on 20/08/2017.
 */
public class UserDocumentForm {

    private Integer id;

    private String name;

    private String description;

    private String type;

    private byte[] content;

    private UserAccountDto user;

    public UserDocumentForm() {

    }

    public UserDocumentForm(UserDocument userDocument) {
        this.id = userDocument.getId();
        this.name = userDocument.getName();
        this.description = userDocument.getDescription();
        this.type = userDocument.getType();
        this.content = userDocument.getContent();
        this.user = new UserAccountDto(userDocument.getUser());
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

    public UserAccountDto getUser() {
        return user;
    }

    public void setUser(UserAccountDto user) {
        this.user = user;
    }
}

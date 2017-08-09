package com.book.data.dto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by jotaiwan on 7/08/2017.
 */
@Component
//@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES)
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
public class ShoppingCart {
    private String id;
    private String code;
    private String type;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}

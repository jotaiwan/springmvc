package com.book.data.dto.dessert;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by jotaiwan on 13/07/2017.
 */
@Component
@Qualifier("Soft")
public class Cake implements Dessert {
    private String flavor;
    private String size;

    public String description() {
        return "This is cake";
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}

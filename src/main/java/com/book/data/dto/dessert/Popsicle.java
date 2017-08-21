package com.book.data.dto.dessert;

import com.book.annotation.Cold;
import com.book.annotation.Fruity;
import org.springframework.stereotype.Component;

/**
 * Created by jotaiwan on 13/07/2017.
 */
@Component
@Cold
@Fruity
public class Popsicle implements Dessert {
    private String flavor;
    private String size;

    public String description() {
        return "This is Popsicle";
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

package com.danilomendes.Model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    private String text;

    private String size;

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

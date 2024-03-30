package com.danilomendes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an image.
 */
public class Image {

    /** The URL of the image */
    @SerializedName("#text")
    private String text;

    /** The size of the image */
    private String size;

    /** Get the URL of the image */
    public String getText() {
        return text;
    }

    /** Set the URL of the image */
    public void setText(String text) {
        this.text = text;
    }

    /** Get the size of the image */
    public String getSize() {
        return size;
    }

    /** Set the size of the image */
    public void setSize(String size) {
        this.size = size;
    }
}

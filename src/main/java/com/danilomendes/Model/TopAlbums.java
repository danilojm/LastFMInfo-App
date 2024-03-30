package com.danilomendes.Model;

import java.util.List;

/**
 * Represents a list of top albums.
 */
public class TopAlbums {
    /** The list of albums */
    private List<Album> album;

    /** Default constructor */
    public TopAlbums() {
    }

    /**
     * Constructor with parameter.
     * @param album The list of albums to set
     */
    public TopAlbums(List<Album> album) {
        this.album = album;
    }

    /** Get the list of albums */
    public List<Album> getAlbum() {
        return album;
    }

    /** Set the list of albums */
    public void setAlbum(List<Album> album) {
        this.album = album;
    }
}

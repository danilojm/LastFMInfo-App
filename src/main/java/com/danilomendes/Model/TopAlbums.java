package com.danilomendes.Model;

import java.util.List;

public class TopAlbums {
    private List<Album> album;

    public TopAlbums() {
    }

    public TopAlbums(List<Album> album) {
        this.album = album;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

}

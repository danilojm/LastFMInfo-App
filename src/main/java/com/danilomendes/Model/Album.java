package com.danilomendes.Model;

import java.util.List;

public class Album {
    private String name;
    private int playcount;
    private String mbid;
    private String url;
    private Artist artist;
    private List<Image> image;

    // Constructors, getters, and setters
    public Album() {
    }

    public Album(String name, int playcount, String mbid, String url, Artist artist, List<Image> image) {
        this.name = name;
        this.playcount = playcount;
        this.mbid = mbid;
        this.url = url;
        this.artist = artist;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

}

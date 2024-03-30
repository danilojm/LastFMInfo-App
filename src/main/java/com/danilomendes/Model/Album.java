package com.danilomendes.Model;

import java.util.List;

public class Album {
    private String name;
    private String mbid;
    private String url;
    private Artist artist;
    private List<Image> image;
    private TracksList tracks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TracksList getTracks() {
        return tracks;
    }

    public void setTracks(TracksList tracks) {
        this.tracks = tracks;
    }

}

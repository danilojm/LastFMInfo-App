package com.danilomendes.Model;

import java.util.List;

/**
 * Represents an Album entity.
 */
public class Album {
    private String name;
    private String mbid;
    private String url;
    private Artist artist;
    private List<Image> image;
    private TracksList tracks;

    /** Get the name of the album */
    public String getName() {
        return name;
    }

    /** Set the name of the album */
    public void setName(String name) {
        this.name = name;
    }

    /** Get the MusicBrainz identifier of the album */
    public String getMbid() {
        return mbid;
    }

    /** Set the MusicBrainz identifier of the album */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /** Get the URL of the album */
    public String getUrl() {
        return url;
    }

    /** Set the URL of the album */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Get the artist of the album */
    public Artist getArtist() {
        return artist;
    }

    /** Set the artist of the album */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /** Get the list of images associated with the album */
    public List<Image> getImage() {
        return image;
    }

    /** Set the list of images associated with the album */
    public void setImage(List<Image> image) {
        this.image = image;
    }

    /** Get the tracks of the album */
    public TracksList getTracks() {
        return tracks;
    }

    /** Set the tracks of the album */
    public void setTracks(TracksList tracks) {
        this.tracks = tracks;
    }
}

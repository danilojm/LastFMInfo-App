package com.danilomendes.Model;

/**
 * Represents a track.
 */
public class Track {
    /** The name of the track */
    private String name;
    /** The MusicBrainz identifier of the track */
    private String mbid;
    /** The URL of the track */
    private String url;
    /** The duration of the track */
    private String duration;
    /** The artist of the track */
    private Artist artist;

    /** Get the name of the track */
    public String getName() {
        return name;
    }

    /** Set the name of the track */
    public void setName(String name) {
        this.name = name;
    }

    /** Get the MusicBrainz identifier of the track */
    public String getMbid() {
        return mbid;
    }

    /** Set the MusicBrainz identifier of the track */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /** Get the URL of the track */
    public String getUrl() {
        return url;
    }

    /** Set the URL of the track */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Get the duration of the track */
    public String getDuration() {
        return duration;
    }

    /** Set the duration of the track */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /** Get the artist of the track */
    public Artist getArtist() {
        return artist;
    }

    /** Set the artist of the track */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

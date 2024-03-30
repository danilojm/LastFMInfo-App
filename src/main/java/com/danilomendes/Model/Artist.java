package com.danilomendes.Model;

import java.util.List;

/**
 * Represents an Artist entity.
 */
public class Artist {
    private String name;
    private String mbid;
    private String url;
    private List<Image> image;
    private Bio bio;

    /** Get the name of the artist */
    public String getName() {
        return name;
    }

    /** Set the name of the artist */
    public void setName(String name) {
        this.name = name;
    }

    /** Get the MusicBrainz identifier of the artist */
    public String getMbid() {
        return mbid;
    }

    /** Set the MusicBrainz identifier of the artist */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /** Get the URL of the artist */
    public String getUrl() {
        return url;
    }

    /** Set the URL of the artist */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Get the list of images associated with the artist */
    public List<Image> getImage() {
        return image;
    }

    /** Set the list of images associated with the artist */
    public void setImage(List<Image> image) {
        this.image = image;
    }

    /** Get the bio of the artist */
    public Bio getBio() {
        return bio;
    }

    /** Set the bio of the artist */
    public void setBio(Bio bio) {
        this.bio = bio;
    }
}

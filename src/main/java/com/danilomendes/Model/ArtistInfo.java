package com.danilomendes.Model;

/**
 * Represents information about an artist.
 */
public class ArtistInfo {

    /** The artist associated with the information */
    private Artist artist;

    /**
     * Get the artist associated with the information.
     * @return The artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Set the artist associated with the information.
     * @param artist The artist to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

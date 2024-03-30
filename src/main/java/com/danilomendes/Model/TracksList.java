package com.danilomendes.Model;

import java.util.List;

/**
 * Represents a list of tracks.
 */
public class TracksList {
    /** The list of tracks */
    private List<Track> track;

    /** Get the list of tracks */
    public List<Track> getTrack() {
        return track;
    }

    /** Set the list of tracks */
    public void setTrack(List<Track> track) {
        this.track = track;
    }
}

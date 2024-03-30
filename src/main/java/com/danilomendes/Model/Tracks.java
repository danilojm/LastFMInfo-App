package com.danilomendes.Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Represents tracks data.
 */
public class Tracks {
    /** The album */
    private Album album;

    /** Get the album */
    public Album getAlbum() {
        return album;
    }

    /** Set the album */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * Represents an album.
     */
    public static class Album {
        /** The artist */
        private String artist;
        /** The MusicBrainz identifier */
        private String mbid;
        /** The images */
        private List<Image> image;
        /** The tracks */
        private TracksList tracks;
        /** The URL */
        private String url;
        /** The name */
        private String name;

        /** Get the artist */
        public String getArtist() {
            return artist;
        }

        /** Set the artist */
        public void setArtist(String artist) {
            this.artist = artist;
        }

        /** Get the MusicBrainz identifier */
        public String getMbid() {
            return mbid;
        }

        /** Set the MusicBrainz identifier */
        public void setMbid(String mbid) {
            this.mbid = mbid;
        }

        /** Get the images */
        public List<Image> getImage() {
            return image;
        }

        /** Set the images */
        public void setImage(List<Image> image) {
            this.image = image;
        }

        /** Get the tracks */
        public TracksList getTracks() {
            return tracks;
        }

        /** Set the tracks */
        public void setTracks(TracksList tracks) {
            this.tracks = tracks;
        }

        /** Get the URL */
        public String getUrl() {
            return url;
        }

        /** Set the URL */
        public void setUrl(String url) {
            this.url = url;
        }

        /** Get the name */
        public String getName() {
            return name;
        }

        /** Set the name */
        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * Represents a list of tracks.
     */
    public static class TracksList {
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

    /**
     * Represents an image.
     */
    public static class Image {
        /** The size of the image */
        private String size;
        /** The URL of the image */
        @SerializedName("#text")
        private String text;

        /** Get the size of the image */
        public String getSize() {
            return size;
        }

        /** Set the size of the image */
        public void setSize(String size) {
            this.size = size;
        }

        /** Get the URL of the image */
        public String getText() {
            return text;
        }

        /** Set the URL of the image */
        public void setText(String text) {
            this.text = text;
        }
    }
}

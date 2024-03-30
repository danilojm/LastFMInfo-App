package com.danilomendes.Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Tracks {
    private Album album;

    // Getters and setters
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public static class Album {
        private String artist;
        private String mbid;
        private List<Image> image;
        private TracksList tracks;
        private String url;
        private String name;

        // Getters and setters
        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getMbid() {
            return mbid;
        }

        public void setMbid(String mbid) {
            this.mbid = mbid;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Tags {
        private List<Tag> tag;

        // Getters and setters
        public List<Tag> getTag() {
            return tag;
        }

        public void setTag(List<Tag> tag) {
            this.tag = tag;
        }
    }

    public static class Tag {
        private String url;
        private String name;

        // Getters and setters
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Image {
        
        private String size;

        @SerializedName("#text")
        private String text;

        // Getters and setters
        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class TracksList {
        private List<Track> track;

        // Getters and setters
        public List<Track> getTrack() {
            return track;
        }

        public void setTrack(List<Track> track) {
            this.track = track;
        }
    }

    public static class Wiki {
        private String published;
        private String summary;
        private String content;

        // Getters and setters
        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

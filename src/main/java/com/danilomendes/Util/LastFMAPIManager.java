package com.danilomendes.Util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.danilomendes.Model.Tracks;
import com.danilomendes.Model.ArtistInfo;
import com.danilomendes.Model.TopAlbumsData;
import com.google.gson.Gson;

/** 
 * Utility class for interacting with the Last.fm API
 */
public class LastFMAPIManager {
    /** Last.fm API Key */
    private static final String API_KEY = APIKeyReader.readAPIKey();
    /** Last.fm API URL */
    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";

    /**
     * Get artist information from Last.fm API
     * @param artist The name of the artist
     * @return ArtistInfo object containing artist information
     */
    public ArtistInfo getArtistInfo(String artist) {
        try {
            String encodedArtist = URLEncoder.encode(artist, "UTF-8");
            String urlString = API_URL + "?method=artist.getinfo&artist=" + encodedArtist + "&api_key=" + API_KEY
                    + "&format=json";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(httpResponse.body(), ArtistInfo.class);
        } catch (MalformedURLException e) {
            // Handle any exceptions that may occur during URI creation or URL conversion
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get top albums for an artist from Last.fm API
     * @param artistOrMbid The name of the artist or MusicBrainz identifier
     * @return TopAlbumsData object containing top albums information
     */
    public TopAlbumsData getTopAlbums(String artistOrMbid) {
        try {
            String encodedArtist = URLEncoder.encode(artistOrMbid, "UTF-8");
            String urlString = API_URL + "?method=artist.gettopalbums&artist=" + encodedArtist + "&api_key=" + API_KEY
                    + "&limit=15&format=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(httpResponse.body(), TopAlbumsData.class);
        } catch (MalformedURLException e) {
            // Handle any exceptions that may occur during URI creation or URL conversion
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get album information from Last.fm API
     * @param artistOrMbid The name of the artist or MusicBrainz identifier
     * @param album The name of the album
     * @return Tracks object containing album information
     */
    public Tracks getAlbumInfo(String artistOrMbid, String album) {
        try {
            String encodedArtist = URLEncoder.encode(artistOrMbid, "UTF-8");
            String encodedAlbum = URLEncoder.encode(album, "UTF-8");
            String urlString = API_URL + "?method=album.getinfo&api_key=" + API_KEY + "&artist=" + encodedArtist
                    + "&album="
                    + encodedAlbum + "&format=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(httpResponse.body(), Tracks.class);
        } catch (MalformedURLException e) {
            // Handle any exceptions that may occur during URI creation or URL conversion
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
        return null;
    }
}

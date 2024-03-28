package com.danilomendes.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.danilomendes.Model.ArtistInfo;
import com.danilomendes.Model.TopAlbumsData;
import com.google.gson.Gson;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Image;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.ResponseBuilder;
import de.umass.lastfm.Result;
import de.umass.lastfm.Session;
import de.umass.util.MapUtilities;
import de.umass.util.StringUtilities;

public class LastFMAPIManager {
    private static final String API_KEY = "eae6731b1afd7750ca9fac0707fe13b8";
    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_SECRET = "4bbb8a220a3c4447553330387d465582";
    private static final String API_USER = "danilojm";
    private static final String API_PASS = "daniloJM@84";

    // public String fetchData(String method, String parameters) {
    // try {
    // String urlString = API_URL + "?method=" + method + "&api_key=" + API_KEY +
    // "&format=json" + parameters;
    // URL url = new URL(urlString);
    // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // connection.setRequestMethod("GET");

    // int responseCode = connection.getResponseCode();
    // if (responseCode == HttpURLConnection.HTTP_OK) {
    // BufferedReader reader = new BufferedReader(new
    // InputStreamReader(connection.getInputStream()));
    // StringBuilder response = new StringBuilder();
    // String line;
    // while ((line = reader.readLine()) != null) {
    // response.append(line);
    // }
    // reader.close();
    // return response.toString();
    // } else {
    // System.err.println("Error: Unable to fetch data from Last.fm API. Response
    // code: " + responseCode);
    // }
    // } catch (Exception e) {
    // System.err.println("Error: Exception occurred while fetching data from
    // Last.fm API: " + e.getMessage());
    // }
    // return null;
    // }

    // public void getAlbuns() {
    // Chart<Artist> chart = User.getWeeklyArtistChart(API_USER, 10, API_KEY);
    // Collection<Artist> artists = chart.getEntries();

    // for (Artist artist : artists) {
    // System.out.println(artist.getName());
    // Collection<Album> albuns = Artist.getTopAlbums(artist.getName(), API_KEY);
    // for (Album album : albuns) {
    // System.out.println(album.getImageURL(ImageSize.LARGE));
    // }
    // }
    // }

    // public List<String> getAlbuns(String artist) {
    // List<String> albsImages = new ArrayList<String>();
    // Collection<Album> albuns = Artist.getTopAlbums(artist, API_KEY);
    // for (Album album : albuns) {
    // albsImages.add(album.getImageURL(ImageSize.LARGE));
    // }
    // return albsImages;
    // }

    // public Track getTrackInfo(String artist, String song) {
    // Track track = Track.getInfo(artist, song, API_KEY);
    // return track;
    // }

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


    public TopAlbumsData getTopAlbums(String artistOrMbid) {
        try {
            String encodedArtist = URLEncoder.encode(artistOrMbid, "UTF-8");
            System.out.println(encodedArtist);
            String urlString = API_URL + "?method=artist.gettopalbums&artist=" + encodedArtist + "&api_key=" + API_KEY
                    + "&format=json";

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
}

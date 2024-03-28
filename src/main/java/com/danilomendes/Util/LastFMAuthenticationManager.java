package com.danilomendes.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.danilomendes.View.MainView;

public class LastFMAuthenticationManager {
    private static final String API_KEY = "eae6731b1afd7750ca9fac0707fe13b8";
    private static final String SHARED_SECRET = "4bbb8a220a3c4447553330387d465582";
    private static final String AUTH_URL = "http://www.last.fm/api/auth/?api_key=" + API_KEY;
    private static final String CALLBACK_URL = "http://example.com"; // Change to your callback URL

    public void requestAuthorization() {
        try {
            String encodedCallbackUrl = URLEncoder.encode(CALLBACK_URL, "UTF-8");
            String authUrl = AUTH_URL + "&cb=" + encodedCallbackUrl;
            MainView mainView = new MainView();
            mainView.openWebView(authUrl);
            // Redirect the user to the Last.fm authentication page
            // Implement logic to open authUrl in a WebView or default browser
        } catch (Exception e) {
            System.err.println("Error: Exception occurred while encoding callback URL: " + e.getMessage());
        }
    }

    public String fetchWebServiceSession(String authToken) {
        try {
            String method = "auth.getSession";
            String apiSig = generateAPISignature(API_KEY, authToken);

            String urlString = "http://ws.audioscrobbler.com/2.0/?method=" + method +
                    "&api_key=" + API_KEY +
                    "&token=" + authToken +
                    "&api_sig=" + apiSig +
                    "&format=json";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.err.println("Error: Unable to fetch web service session. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println("Error: Exception occurred while fetching web service session: " + e.getMessage());
        }
        return null;
    }

    private String generateAPISignature(String apiKey, String authToken) {
        // Implement logic to generate API signature
        String signature = ""; // Replace with actual signature
        return signature;
    }
}

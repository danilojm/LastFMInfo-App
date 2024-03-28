package com.danilomendes.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainView extends BorderPane {
    private Button authButton;
    private Label statusLabel;

    public MainView() {
        initializeUI();
    }

    private void initializeUI() {
        // Create layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(authButton, statusLabel);

        // Set layout to the center of BorderPane
        setCenter(vbox);
    }

    public void openWebView(String url) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);

        // Show WebView in a new stage or dialog
        // Example: displayWebView(webView);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public Button getAuthButton() {
        return authButton;
    }
}

package com.danilomendes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main application class responsible for initializing and launching the
 * JavaFX application.
 */
public class App extends Application {

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application, onto which the
     *              application scene can be set.
     * @throws IOException if an error occurs during FXML loading.
     */
    @Override
    public void start(Stage stage) throws IOException {

        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));

            Image icon = new Image(getClass().getResourceAsStream("images/lastfm-icon.png"));
            stage.getIcons().add(icon);

            stage.setTitle("LastFM API - Artists Info App - by Danilo Mendes de Oliveira - 200549002");

            // Apply Style CSS to the project
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());

            // Obtendo o monitor primário
            Screen screen = Screen.getPrimary();
            // Definindo o estágio para o modo de tela cheia
            stage.setWidth(screen.getBounds().getWidth()-10);
            stage.setHeight(screen.getBounds().getHeight()-40);

            // Definindo o estágio para ter bordas decoradas
            stage.initStyle(StageStyle.DECORATED);

            // Set the scene to the stage and show the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    /**
     * Handles IOExceptions occurred during application initialization.
     *
     * @param e The IOException.
     */
    private void handleIOException(IOException e) {
        // Display an error message to the user
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Error loading FXML", e);
    }

    /**
     * The main method, launching the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}

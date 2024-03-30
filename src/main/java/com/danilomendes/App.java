package com.danilomendes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The main application class responsible for initializing and launching the JavaFX application.
 */
public class App extends Application {

    private static Scene scene;

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application, onto which the application scene can be set.
     * @throws IOException if an error occurs during FXML loading.
     */
    @Override
    public void start(Stage stage) throws IOException {

        String css = this.getClass().getResource("css/styles.css").toExternalForm();

        // Load the FXML and set the scene
        Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        // Set the alignment of the center region of the BorderPane to center
        BorderPane.setAlignment(root, Pos.CENTER);

        // Set the stage to fullscreen
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
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

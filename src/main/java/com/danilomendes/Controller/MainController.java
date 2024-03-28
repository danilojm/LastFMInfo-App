package com.danilomendes.Controller;

import java.util.Optional;

import com.danilomendes.Model.Album;
import com.danilomendes.Model.ArtistInfo;
import com.danilomendes.Model.Image;
import com.danilomendes.Model.TopAlbumsData;
import com.danilomendes.Util.LastFMAPIManager;

import de.umass.lastfm.PaginatedResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML
    private BorderPane root;

    @FXML
    private Label typeOfSearchLabel;

    @FXML
    private ComboBox<String> typeOfSearchCombobox;

    @FXML
    private TextField searchTextField;

    @FXML
    private TextArea sumaryTextAreaField;

    @FXML
    private ImageView artistImageViewId;

    @FXML
    private Button searchButton;

    @FXML
    private GridPane gridPane;

    LastFMAPIManager manager;

    private String imageUrl = "";
    private String toSearchItem = "";

    public void initialize() {
        initializeComboBoxes();
        manager = new LastFMAPIManager();
        searchButton.setOnAction(event -> handleSearch());

        typeOfSearchCombobox.setOnAction(event -> {
            String selectedOption = typeOfSearchCombobox.getValue();
            System.out.println("Selected: " + selectedOption);
            toSearchItem = selectedOption;
            typeOfSearchLabel.setText(toSearchItem);
        });
    }

    private void initializeComboBoxes() {
        typeOfSearchCombobox.getItems().addAll("Artist", "Album", "Music");
        typeOfSearchCombobox.setValue("Artist");
    }

    @FXML
    private void handleSearch() {
        ArtistInfo artistInfo = manager.getArtistInfo(searchTextField.getText());
        TopAlbumsData albums = manager.getTopAlbums(searchTextField.getText());

        for (com.danilomendes.Model.Image image : artistInfo.getArtist().getImage()) {
            if (image != null && image.getSize().equals("large")) {
                imageUrl = image.getText();
            }
        }

        // Assuming you have a GridPane instance called gridPane
        int col = 0;
        int row = 0;
        int count = 0;
        for (Album album : albums.getTopalbums().getAlbum()) {

            Optional<Image> foundImage = album.getImage().stream()
                    .filter(image -> "large".equals(image.getSize()))
                    .findFirst();

            if (foundImage.isPresent() && !foundImage.get().getText().equals("")) {
                javafx.scene.image.Image image = new javafx.scene.image.Image(foundImage.get().getText());
                ImageView imageView = new ImageView(image);

                // Add the ImageView to the desired cell in the GridPane
                gridPane.add(imageView, row, col);
                if (row < 5) {
                    row++;
                } else {
                    col++;
                    row = 0;
                }

                if (count == 17) {
                    break;
                } else {
                    count++;
                }

            } else {
                System.out.println("Image not found");
            }

        }

        // Create an ImageView and load the image into it
        javafx.scene.image.Image image = new javafx.scene.image.Image(imageUrl);
        artistImageViewId.setImage(image);
        sumaryTextAreaField.setText(artistInfo.getArtist().getBio().getSummary());

    }

    /** Close the application */
    @FXML
    private void closeApplication() {
        Platform.exit();
    }
}

package com.danilomendes.Controller;

import java.text.DecimalFormat;
import java.util.Optional;

import com.danilomendes.Model.Album;
import com.danilomendes.Model.Tracks;
import com.danilomendes.Model.ArtistInfo;
import com.danilomendes.Model.Image;
import com.danilomendes.Model.TopAlbumsData;
import com.danilomendes.Model.Track;
import com.danilomendes.Util.LastFMAPIManager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

/** Controller class for handling UI interactions */
public class MainController {

    /** FXML Declarations */
    @FXML
    private BorderPane root;

    @FXML
    private ComboBox<String> typeOfSearchCombobox;

    @FXML
    private Label typeOfSearchLabel;

    @FXML
    private TextField searchArtistTextField;

    @FXML
    private Label searchAlbumLabel;

    @FXML
    private TextField searchAlbumTextField;

    @FXML
    private TextArea sumaryTextAreaField;

    @FXML
    private ImageView artistImageViewId;

    @FXML
    private Button searchButton;

    @FXML
    private GridPane gridPane;

    /** Instance of LastFMAPIManager */
    private LastFMAPIManager manager;

    /** Instance of JavaFX Image */
    private javafx.scene.image.Image image;

    /** Default search item */
    private String toSearchItem = "Artist";

    /** Initialize method called when FXML is loaded */
    @FXML
    public void initialize() {
        searchAlbumLabel.setVisible(false);
        searchAlbumTextField.setVisible(false);
        initializeComboBoxes();
        manager = new LastFMAPIManager();

        // Event handler for ComboBox selection
        typeOfSearchCombobox.setOnAction(event -> {
            String selectedOption = typeOfSearchCombobox.getValue();
            toSearchItem = selectedOption;
            if (!toSearchItem.equals("Artist")) {
                searchAlbumLabel.setVisible(true);
                searchAlbumTextField.setVisible(true);
            } else {
                searchAlbumLabel.setVisible(false);
                searchAlbumTextField.setVisible(false);
            }
        });
    }

    /** Initialize ComboBoxes */
    private void initializeComboBoxes() {
        typeOfSearchCombobox.getItems().addAll("Artist", "Album");
        typeOfSearchCombobox.setValue("Artist");
    }

    /** Handle search event */
    @FXML
    private void handleSearch() {
        String artist = searchArtistTextField.getText().trim();
        String album = searchAlbumTextField.getText().trim();

        // Check if artist field is empty
        if (artist.isEmpty()) {
            sumaryTextAreaField.setText("Please enter an Artist name");
            return;
        }

        // Check search type and perform appropriate search
        if (!album.isEmpty() && toSearchItem.equals("Album")) {
            searchByAlbum(artist, album);
        } else {
            searchByArtist(artist);
        }
    }

    /** Search albums by artist */
    private void searchByAlbum(String artist, String album) {
        Thread searchThread = new Thread(() -> {
            // Retrieve album information from LastFMAPIManager
            Tracks trackInfo = manager.getAlbumInfo(artist, album);
            // Update UI with album information
            Platform.runLater(() -> gridListAlbumInfo(trackInfo));
        });
        searchThread.start();
    }

    /** Search artist */
    private void searchByArtist(String artist) {
        Thread searchThread = new Thread(() -> {
            // Retrieve artist information and top albums from LastFMAPIManager
            ArtistInfo artistInfo = manager.getArtistInfo(artist);
            TopAlbumsData albums = manager.getTopAlbums(artist);

            Platform.runLater(() -> {
                // Display artist bio if available, else show 'Artist not found' message
                if (artistInfo.getArtist() == null) {
                    sumaryTextAreaField.setText("Artist not Found!");
                    return;
                }

                // Iterate through top albums and display album covers as Hyperlinks
                int col = 0;
                int row = 0;
                for (Album album : albums.getTopalbums().getAlbum()) {
                    Optional<Image> foundImage = album.getImage().stream()
                            .filter(albumImage -> "large".equals(albumImage.getSize())
                                    && !albumImage.getText().isEmpty())
                            .findFirst();

                    if (foundImage.isPresent()) {
                        image = new javafx.scene.image.Image(foundImage.get().getText());
                        ImageView imageView = new ImageView(image);

                        // Create Hyperlink with album cover as graphic
                        Hyperlink hyperlink = new Hyperlink();
                        hyperlink.setGraphic(imageView);
                        Tooltip tooltip = new Tooltip();
                        tooltip.setText(album.getName());
                        hyperlink.setTooltip(tooltip);

                        // Handle click event on Hyperlink to display album tracks
                        hyperlink.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            String artistName = album.getArtist().getName();
                            String albumName = album.getName();
                            searchByAlbum(artistName, albumName);
                        });

                        // Add Hyperlink to GridPane
                        gridPane.add(hyperlink, row, col);
                        if (row < 5) {
                            row++;
                        } else {
                            col++;
                            row = 0;
                        }

                        artistImageViewId.setImage(image);
                        sumaryTextAreaField.setText(artistInfo.getArtist().getBio().getSummary());
                    } else {
                        sumaryTextAreaField.setText("Artist not Found!");
                        gridPane.getChildren().clear();
                    }
                }
            });
        });
        searchThread.start();
    }

    /** List album info in the TextArea */
    private void gridListAlbumInfo(Tracks albumInfo) {
        sumaryTextAreaField.clear();

        if (albumInfo != null && albumInfo.getAlbum() != null && albumInfo.getAlbum().getTracks() != null) {
            StringBuilder tracksBuilder = new StringBuilder("Album: ").append(albumInfo.getAlbum().getName())
                    .append("\n\n");

            // Append each track name and duration to the StringBuilder
            for (Track track : albumInfo.getAlbum().getTracks().getTrack()) {
                String trackName = track.getName();
                String duration = track.getDuration();

                tracksBuilder.append("Track: ").append(trackName).append(" ....................... ");
                if (duration != null && !duration.isEmpty()) {
                    try {
                        int durationInSeconds = Integer.parseInt(duration);
                        tracksBuilder.append(secondsToMinutes(durationInSeconds));
                    } catch (NumberFormatException e) {
                        tracksBuilder.append("00:00");
                    }
                } else {
                    tracksBuilder.append("00:00");
                }
                tracksBuilder.append("\n");
            }

            sumaryTextAreaField.setText(tracksBuilder.toString());

            // Set album cover image if available
            Optional<Tracks.Image> albumCover = albumInfo.getAlbum().getImage().stream()
                    .filter(albumImage -> "large".equals(albumImage.getSize()) && !albumImage.getText().isEmpty())
                    .findFirst();

            albumCover.ifPresent(lbCover -> {
                javafx.scene.image.Image image = new javafx.scene.image.Image(lbCover.getText());
                artistImageViewId.setImage(image);
            });

            // Remove existing background image (if any)
            root.getChildren().removeIf(node -> node instanceof ImageView);
            // Set album cover image if available
            Optional<Tracks.Image> albumCoverExtralarge = albumInfo.getAlbum().getImage().stream()
                    .filter(albumImage -> "extralarge".equals(albumImage.getSize()) && !albumImage.getText().isEmpty())
                    .findFirst();

            albumCoverExtralarge.ifPresent(bgCover -> {
                javafx.scene.image.Image image = new javafx.scene.image.Image(bgCover.getText());
                // Load and set the background image
                ImageView backgroundImage = new ImageView(image);

                backgroundImage.fitWidthProperty().bind(root.widthProperty().subtract(20));
                backgroundImage.fitHeightProperty().bind(root.heightProperty().subtract(8));
                backgroundImage.setTranslateX(10);
                backgroundImage.setTranslateY(10);
                backgroundImage.setOpacity(0.2);
                root.getChildren().add(backgroundImage);
                backgroundImage.toBack();
            });

        } else {
            sumaryTextAreaField
                    .setText("Album not found or database data inconsistent, sorry!\nTry another Album or Artist");
        }
    }

    /** Convert seconds to minutes */
    public static String secondsToMinutes(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        DecimalFormat df = new DecimalFormat("00");
        String formattedTime = df.format(minutes) + ":" + df.format(remainingSeconds);
        return formattedTime;
    }

    /** Load image */
    @FXML
    private void loadImage() {
        Thread searchThread = new Thread(() -> {
            image = new javafx.scene.image.Image(
                    MainController.class.getResourceAsStream("/com/danilomendes/images/load.gif"));
            artistImageViewId.setImage(image);
        });
        searchThread.start();

        Thread handleSearch = new Thread(() -> {
            handleSearch();
        });
        handleSearch.start();
    }

    /** Close the application */
    @FXML
    private void closeApplication() {
        Platform.exit();
    }
}

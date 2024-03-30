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

public class MainController {
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

    LastFMAPIManager manager;

    private javafx.scene.image.Image image;

    private String toSearchItem = "Artist";

    public void initialize() {

        searchAlbumLabel.setVisible(false);
        searchAlbumTextField.setVisible(false);
        initializeComboBoxes();
        manager = new LastFMAPIManager();
        // searchButton.setOnAction(event -> handleSearch());

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

    private void initializeComboBoxes() {
        typeOfSearchCombobox.getItems().addAll("Artist", "Album");
        typeOfSearchCombobox.setValue("Artist");
    }

    private void handleSearch() {
        if (!searchArtistTextField.getText().isEmpty() && !searchAlbumTextField.getText().isEmpty()) {

            Tracks trackInfo = manager.getAlbumInfo(searchArtistTextField.getText(), searchAlbumTextField.getText());
            gridListAlbumInfo(trackInfo);

        } else if (!searchArtistTextField.getText().isEmpty()) {

            ArtistInfo artistInfo = manager.getArtistInfo(searchArtistTextField.getText());
            TopAlbumsData albums = manager.getTopAlbums(searchArtistTextField.getText());

            Platform.runLater(() -> {
                if (artistInfo.getArtist() == null) {
                    sumaryTextAreaField.setText("Artist not Found!");
                    return;
                }

                // Assuming you have a GridPane instance called gridPane
                int col = 0;
                int row = 0;
                int count = 0;
                for (Album album : albums.getTopalbums().getAlbum()) {

                    Optional<Image> foundImage = album.getImage().stream()
                            .filter(albumImage -> "large".equals(albumImage.getSize())
                                    && !"".equals(albumImage.getText()))
                            .findFirst();

                    if (foundImage.isPresent()) {
                        image = new javafx.scene.image.Image(foundImage.get().getText());
                        ImageView imageView = new ImageView(image);

                        // Crie um Hyperlink
                        Hyperlink hyperlink = new Hyperlink();
                        hyperlink.setGraphic(imageView);
                        Tooltip tooltip = new Tooltip();
                        tooltip.setText(album.getName());
                        hyperlink.setTooltip(tooltip);

                        // Configure um evento de clique para o Hyperlink
                        hyperlink.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            // Obtenha o nome do artista e do álbum associado a esta imagem
                            String artistName = album.getArtist().getName();
                            String albumName = album.getName();

                            // Chame o método getAlbumInfo
                            Tracks trackInfo = manager.getAlbumInfo(artistName, albumName);
                            gridListAlbumInfo(trackInfo);
                        });

                        // Add the ImageView to the desired cell in the GridPane
                        gridPane.add(hyperlink, row, col);
                        if (row < 4) {
                            row++;
                        } else {
                            col++;
                            row = 0;
                        }

                        if (count == 14) {
                            break;
                        } else {
                            count++;
                        }

                        artistImageViewId.setImage(image);
                        sumaryTextAreaField.setText(artistInfo.getArtist().getBio().getSummary());
                    } else {
                        sumaryTextAreaField.setText("Artist not Found!");
                        gridPane.getChildren().clear();
                    }
                }
            });
        } else {
            sumaryTextAreaField.setText("Please enter an Artist name");
        }
    }

    // private void gridListAlbumInfo(Tracks albumInfo) {

    //     sumaryTextAreaField.clear();
    //     if (albumInfo != null && albumInfo.getAlbum() != null && albumInfo.getAlbum().getTracks() != null) {
    //         String tracks = "Album: " + albumInfo.getAlbum().getName() + "\n\n";

    //         for (int i = 0; i < albumInfo.getAlbum().getTracks().getTrack().size(); i++) {
    //             // Obtém o nome da faixa
    //             String trackName = albumInfo.getAlbum().getTracks().getTrack().get(i).getName();
    //             String durarion = albumInfo.getAlbum().getTracks().getTrack().get(i).getDuration();

    //             tracks += "Track : " + (i + 1) + " - " + trackName + " ....................... "
    //                     + (durarion != null ? secondsToMinutes(Integer.parseInt(durarion)) : "00:00") + "\n";

    //             // Adicione o Label ao GridPane
    //             sumaryTextAreaField.setText(tracks);

    //         }

    //         Optional<com.danilomendes.Model.Tracks.Image> albumCover = albumInfo.getAlbum().getImage().stream()
    //                 .filter(albumImage -> "large".equals(albumImage.getSize()) && !"".equals(albumImage.getText()))
    //                 .findFirst();

    //         if (albumCover.isPresent()) {
    //             image = new javafx.scene.image.Image(albumCover.get().getText());
    //             artistImageViewId.setImage(image);
    //         }
    //     } else {
    //         // Adicione o Label ao GridPane
    //         sumaryTextAreaField.setText("Album not found or database data inconsistent, sorry!\nTry another Album or Artist");
    //     }
    // }

    private void gridListAlbumInfo(Tracks albumInfo) {
        sumaryTextAreaField.clear();
    
        if (albumInfo != null && albumInfo.getAlbum() != null && albumInfo.getAlbum().getTracks() != null) {
            StringBuilder tracksBuilder = new StringBuilder("Album: ").append(albumInfo.getAlbum().getName()).append("\n\n");
    
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
    
            Optional<Tracks.Image> albumCover = albumInfo.getAlbum().getImage().stream()
                    .filter(albumImage -> "large".equals(albumImage.getSize()) && !albumImage.getText().isEmpty())
                    .findFirst();
    
            albumCover.ifPresent(cover -> {
                javafx.scene.image.Image image = new javafx.scene.image.Image(cover.getText());
                artistImageViewId.setImage(image);
            });
        } else {
            sumaryTextAreaField.setText("Album not found or database data inconsistent, sorry!\nTry another Album or Artist");
        }
    }
    

    public static String secondsToMinutes(int seconds) {
        // Converte segundos para minutos
        int minutes = seconds / 60;

        // Calcula os segundos restantes após a conversão para minutos
        int remainingSeconds = seconds % 60;

        // Formata os minutos e segundos em uma string com a máscara desejada
        DecimalFormat df = new DecimalFormat("00"); // Define a máscara para dois dígitos
        String formattedTime = df.format(minutes) + ":" + df.format(remainingSeconds);

        return formattedTime;
    }

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
        // Iniciar a thread
    }

    /** Close the application */
    @FXML
    private void closeApplication() {
        Platform.exit();
    }
}

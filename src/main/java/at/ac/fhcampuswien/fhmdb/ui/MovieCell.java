package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label description = new Label();
    private final Label genres = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final ImageView imageView = new ImageView();
    private final HBox releaseRatingBox = new HBox(releaseYear, rating);
    private final VBox textContainer = new VBox(title, releaseRatingBox, description, genres);
    private final HBox layout = new HBox(imageView, textContainer);
    private final Font titleFont = Font.font("Hiragino Sans W5", 20);
    private final Font descriptionFont = Font.font("Hiragino Sans W3", 12);
    private final Font genreFont = Font.font("Hiragino Sans W2", 12);

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Set cell styles
            this.getStyleClass().add("movie-cell");

            // Set movie details
            Image image = new Image(movie.getImgUrl());
            imageView.setImage(image);
            title.setText(movie.getTitle());
            releaseYear.setText("Release Year: " + movie.getReleaseYear());
            rating.setText("Rating: " + movie.getRating());
            description.setText(movie.getDescription() != null ? movie.getDescription() : "N/A");
            genres.setText(movie.getGenres() != null ? String.join(", ", movie.getGenres()) : "N/A");

            // Set fonts, color scheme & background
            title.setFont(titleFont);
            releaseYear.setFont(descriptionFont);
            rating.setFont(descriptionFont);
            description.setFont(descriptionFont);
            genres.setFont(genreFont);
            title.getStyleClass().add("text-white");
            releaseYear.getStyleClass().add("text-light-gray");
            rating.getStyleClass().add("text-light-gray");
            description.getStyleClass().add("text-light-gray");
            genres.getStyleClass().add("text-light-purple");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#262626"), null, null)));

            // Configure layout
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            description.setWrapText(true);
            releaseRatingBox.setSpacing(10);
            textContainer.setSpacing(10);
            layout.setPadding(new Insets(10));
            layout.setSpacing(10);
            layout.setAlignment(Pos.CENTER_LEFT);

            // Set graphic to display layout
            setGraphic(layout);
        }
    }
}

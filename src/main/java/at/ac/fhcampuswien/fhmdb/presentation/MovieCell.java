package at.ac.fhcampuswien.fhmdb.presentation;

import at.ac.fhcampuswien.fhmdb.AddToWatchlistEventHandler;
import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.RemoveFromWatchlistEventHandler;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.data.Movie;
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
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.sql.SQLException;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label description = new Label();
    private final Label genres = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final ImageView imageView = new ImageView();
    private final Button watchlistButton = new Button("To Watchlist");
    private final HBox releaseRatingBox = new HBox(releaseYear, rating);
    private final VBox textContainer = new VBox(title, releaseRatingBox, description, genres, watchlistButton);
    private final HBox layout = new HBox(imageView, textContainer);

    WatchlistRepository watchlistRepository;
    {
        try {
            watchlistRepository = new WatchlistRepository();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AddToWatchlistEventHandler<Movie> addToWatchlistClicked;
    private RemoveFromWatchlistEventHandler<Movie> removeFromWatchlistClicked;

    public MovieCell(AddToWatchlistEventHandler<Movie> addToWatchlistClicked, RemoveFromWatchlistEventHandler<Movie> removeFromWatchlistClicked) {
        this.addToWatchlistClicked = addToWatchlistClicked;
        this.removeFromWatchlistClicked = removeFromWatchlistClicked;
    }

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
            if (HomeController.isInHome) {
                watchlistButton.setText("To Watchlist");
            } else {
                watchlistButton.setText("Remove");
            }

            // Set background, color scheme & fonts
            layout.setBackground(new Background(new BackgroundFill(Color.web("#262626"), null, null)));
            watchlistButton.getStyleClass().add("watchlist-button");
            watchlistButton.getStyleClass().addAll("button");
            title.getStyleClass().add("text-white");
            releaseYear.getStyleClass().add("text-light-gray");
            rating.getStyleClass().add("text-light-gray");
            description.getStyleClass().add("text-light-gray");
            genres.getStyleClass().add("text-light-purple");
            title.setFont(Font.font(22));
            releaseYear.setFont(Font.font(13));
            rating.setFont(Font.font(13));
            description.setFont(Font.font(13));
            genres.setFont(Font.font(13));
            watchlistButton.setFont(Font.font(13));

            // Configure layout
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            description.setWrapText(true);
            releaseRatingBox.setSpacing(10);
            textContainer.setSpacing(10);
            layout.setPadding(new Insets(10));
            layout.setSpacing(10);
            layout.setAlignment(Pos.CENTER_LEFT);
            watchlistButton.setAlignment(Pos.CENTER);
            watchlistButton.setTextAlignment(TextAlignment.CENTER);

            // Set graphic to display layout
            setGraphic(layout);

            // Watchlist button's event handler
            watchlistButton.setOnMouseReleased(mouseEvent -> {
                if (watchlistButton.getText().equals("To Watchlist")) {
                    addToWatchlistClicked.addOnClick(movie);
                } else {
                    removeFromWatchlistClicked.removeOnClick(movie);
                }
            });
        }
    }
}

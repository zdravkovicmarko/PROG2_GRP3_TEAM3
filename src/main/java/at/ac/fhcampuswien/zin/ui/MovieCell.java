package at.ac.fhcampuswien.zin.ui;

import at.ac.fhcampuswien.zin.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label description = new Label();
    private final Label genres = new Label();
    private final VBox layout = new VBox(title, description, genres);
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
            this.getStyleClass().add("movie-cell"); // Set cell styles

            // Set movie details
            title.setText(movie.getTitle());
            description.setText(movie.getDescription() != null ? movie.getDescription() : "N/A");
            genres.setText(movie.getGenres() != null ? String.join(", ", movie.getGenres()) : "N/A");

            // Set color scheme & background
            title.getStyleClass().add("text-white");
            description.getStyleClass().add("text-light-gray");
            genres.getStyleClass().add("text-light-purple");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#262626"), null, null)));

            // Configure layout
            title.setFont(titleFont);
            description.setMaxWidth(getScene().getWidth() - 30);
            description.setWrapText(true);
            description.setFont(descriptionFont);
            genres.setFont(genreFont);

            layout.setPadding(new Insets(10));
            layout.setSpacing(10);
            layout.setAlignment(Pos.CENTER_LEFT);

            setGraphic(layout); // Set the graphic to display the layout
        }
    }
}

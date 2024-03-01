package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label description = new Label();
    private final Label genres = new Label();
    private final VBox layout = new VBox(title, description, genres);
    private final Font italicFont = Font.font("Verdana", FontPosture.ITALIC, 12);

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
            genres.setFont(italicFont);

            // Set color scheme & background
            title.getStyleClass().add("text-yellow");
            description.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-light-gray");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // Configure layout
            title.setFont(Font.font(20));
            description.setMaxWidth(getScene().getWidth() - 30);
            description.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.setSpacing(10);
            layout.setAlignment(Pos.CENTER_LEFT);

            setGraphic(layout); // Set the graphic to display the layout
        }
    }
}

package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.datalayer.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.datalayer.MovieRepository;
import at.ac.fhcampuswien.fhmdb.datalayer.WatchlistRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("ZINEMATIC");
        stage.setScene(scene);
        stage.show();

        try {
            MovieRepository movieRepository = new MovieRepository();
            movieRepository.addAllMovies(HomeController.allMovies);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
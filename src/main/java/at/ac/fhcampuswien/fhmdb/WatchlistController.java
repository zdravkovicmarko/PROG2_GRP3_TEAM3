package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.data.Movie;
import at.ac.fhcampuswien.fhmdb.data.MovieEntity;
import at.ac.fhcampuswien.fhmdb.presentation.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.stage.Stage;

public class WatchlistController implements Initializable {
    @FXML
    public JFXListView<Movie> watchListView;
    @FXML
    public JFXButton homeBtn;

    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    private final WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<WatchlistMovieEntity> watchlistData = watchlistRepository.getWatchlist();  // Get watchlist data from database
            List<Movie> watchlistMovies = MovieEntity.toMovies(watchlistData);              // Convert watchlist data to Movie objects
            observableMovies.setAll(watchlistMovies);                                       // Update observable list
            watchListView.setItems(observableMovies);                                       // Set data to list view
            watchListView.setCellFactory(movieListView -> new MovieCell(null, RemoveFromWatchlistClicked));
        } catch (DatabaseException e) {
            System.out.println("Error getting watchlist data: " + e.getMessage());
        }

        homeBtn.setOnAction(actionEvent -> switchToHome(actionEvent)); // Sort button's event handler
    }

    public void switchToHome(ActionEvent event) {
        try {
        HomeController.isInHome = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        Parent watchlistRoot = loader.load();
        Scene watchlistScene = new Scene(watchlistRoot, 1111, 600);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(watchlistScene);
        stage.show();
        } catch (IOException e) {
            System.out.println("Error switching to home: " + e.getMessage());
        }
    }

    protected final RemoveFromWatchlistEventHandler<Movie> RemoveFromWatchlistClicked = movie -> {
        try {
            watchlistRepository.removeFromWatchlist(movie.getId());

            // Update UI reflecting removal
            observableMovies.remove(movie);
            watchListView.setItems(observableMovies);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    };
}
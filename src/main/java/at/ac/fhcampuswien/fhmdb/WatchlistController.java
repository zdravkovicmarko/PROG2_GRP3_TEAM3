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
import java.sql.SQLException;
import java.util.*;
import javafx.stage.Stage;

public class WatchlistController implements Initializable {
    @FXML
    public JFXListView<Movie> watchListView;
    @FXML
    public JFXButton homeBtn;

    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    private final WatchlistRepository watchlistRepository;
    {
        try {
            watchlistRepository = new WatchlistRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<WatchlistMovieEntity> watchlistData = watchlistRepository.getWatchlist();  // Get watchlist data from database
            List<Movie> watchlistMovies = MovieEntity.toMovies(watchlistData);              // Convert watchlist data to Movie objects
            observableMovies.setAll(watchlistMovies);                                       // Update observable list
            watchListView.setItems(observableMovies);                                       // Set data to list view
            watchListView.setCellFactory(movieListView -> new MovieCell(null, RemoveFromWatchlistClicked));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Sort button's event handler
        homeBtn.setOnAction(actionEvent -> {
            try {
                switchToHome(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void switchToHome(ActionEvent event) throws IOException {
        HomeController.isInHome = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        Parent watchlistRoot = loader.load();
        Scene watchlistScene = new Scene(watchlistRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(watchlistScene);
        stage.show();
    }

    protected final RemoveFromWatchlistEventHandler<Movie> RemoveFromWatchlistClicked = movie -> {
        try {
            WatchlistRepository watchlistRepository = new WatchlistRepository();
            watchlistRepository.removeFromWatchlist(movie.getId());

            // Update UI reflecting removal
            observableMovies.remove(movie);
            watchListView.setItems(observableMovies);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
}
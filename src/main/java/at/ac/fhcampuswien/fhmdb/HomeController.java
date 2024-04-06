package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;

public class HomeController implements Initializable {
    @FXML
    public JFXListView movieListView;
    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXComboBox releaseYearComboBox;
    @FXML
    public JFXComboBox ratingComboBox;
    @FXML
    public JFXButton searchBtn;
    @FXML
    public TextField searchField;
    @FXML
    public JFXButton sortBtn;
    public static MovieAPI movieAPI = new MovieAPI();
    public static List<Movie> allMovies;

    static {
        try {
            allMovies = movieAPI.fetchMovies(null,null,0,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // Automatically updates corresponding UI elements when underlying data changes

    // Initialize UI stuff
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        observableMovies.addAll(allMovies); // Add dummy data to observable list
        movieListView.setItems(observableMovies); // Set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // Use custom cell factory to display data

        // Populate combo boxes
        String[] genres = {"ALL", "ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME",
                "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL",
                "MYSTERY", "ROMANCE", "SCIENCE_FICTION", "SPORT", "THRILLER", "WAR", "WESTERN"};

        List<String> releaseYears = new ArrayList<>();
        for (int year = 2025; year >= 1895; year--) {
            releaseYears.add(String.valueOf(year));
        }

        List<String> ratings = new ArrayList<>();
        for (double rating = 10.0; rating >= 0.0; rating -= 0.1) {
            ratings.add(String.format("%.1f", rating));
        }

        genreComboBox.getItems().addAll(genres);
        releaseYearComboBox.getItems().addAll(releaseYears);
        ratingComboBox.getItems().addAll(ratings);

        // Combo boxes' event handlers
        genreComboBox.setOnAction(event -> eventSearchButton());

        // Search button's event handlers
        searchBtn.setOnAction(event -> eventSearchButton());
        searchField.setOnKeyReleased(event -> { if (event.getCode() == KeyCode.ENTER) { eventSearchButton(); } });

        // Sort button's event handler
        sortBtn.setOnAction(actionEvent -> eventSortButton());
    }

    // Filters list of all movies based on search & genres
    public List<Movie> filter(List<Movie> movieList, String searchQuery, String genre) {
        String search = searchQuery.trim().toLowerCase();
        List<Movie> matchingMovies = movieList.stream()
            .filter(movie ->
                (genre == null || genre.equals("ALL") || movie.getGenres().contains(genre)) &&
                (search.isEmpty() || movie.getTitle().toLowerCase().contains(search) || movie.getDescription().toLowerCase().contains(search)))
            .collect(Collectors.toList());

        return matchingMovies;
    }

    // Events of search button (UI element)
    public void eventSearchButton(){
        List<Movie> matchingMovies = filter(allMovies, searchField.getText(), (String) genreComboBox.getSelectionModel().getSelectedItem());

        if (matchingMovies.isEmpty()) {
            Label label = new Label("No results found");
            movieListView.setPlaceholder(label);
            observableMovies.clear(); // Clear any existing movie data
        } else {
            observableMovies = FXCollections.observableArrayList(matchingMovies);
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        }
    }

    // Sorts alphabetically
    public void sortAlphabetically(boolean wantsAscSort, ObservableList<Movie> observableMovies) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        if (!wantsAscSort) {comparator = comparator.reversed();}
        observableMovies.sort(comparator);
    }

    // Events of sort button (UI element)
    public void eventSortButton(){
        if (sortBtn.getText().equals("Sort (asc)")) {
            sortAlphabetically(true, observableMovies);
            sortBtn.setText("Sort (desc)");
        } else {
            sortAlphabetically(false, observableMovies);
            sortBtn.setText("Sort (asc)");
        }
    }

    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream() // Convert the list of movies into a Stream of Movie objects
                .flatMap(movie -> movie.getMainCast().stream()) // Flatten the Stream of Movie objects into a Stream of actors in the main cast of each movie
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Collect the Stream of actors into a Map where keys are actors and values are their counts
                .entrySet().stream() // Convert the Map into a Stream of Map.Entry objects
                .max(Map.Entry.comparingByValue()) // Find the maximum entry in the stream based on the values (counts of actors)
                .map(Map.Entry::getKey) // Extract the key (actor) from the maximum entry
                .orElse(null); // Return the most popular actor's name, or null if the stream is empty
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
}
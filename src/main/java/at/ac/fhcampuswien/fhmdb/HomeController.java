package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
    @FXML
    private JFXButton watchlistBtn;
    public static MovieAPI movieAPI = new MovieAPI();
    public static List<Movie> allMovies;

    static {

        try {
            allMovies = movieAPI.fetchMovies(null,null,0,0);
        } catch (MovieAPIException e) {
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
        releaseYears.add("ALL");
        for (int year = 2025; year >= 1895; year--) {
            releaseYears.add(String.valueOf(year));
        }

        List<String> ratings = new ArrayList<>();
        ratings.add("ALL");
        for (double rating = 10.0; rating >= 0.0; rating -= 0.5) {
            ratings.add(String.format(Locale.ENGLISH, "%.1f", rating));
        }

        genreComboBox.getItems().addAll(genres);
        releaseYearComboBox.getItems().addAll(releaseYears);
        ratingComboBox.getItems().addAll(ratings);

        // Combo boxes' event handlers
        genreComboBox.setOnAction(event -> eventFilter());
        releaseYearComboBox.setOnAction(event -> eventFilter());
        ratingComboBox.setOnAction(event -> eventFilter());

        // Search button's event handlers
        searchBtn.setOnAction(event -> eventFilter());
        searchField.setOnKeyReleased(event -> { if (event.getCode() == KeyCode.ENTER) { eventFilter(); } });

        // Sort button's event handler
        sortBtn.setOnAction(actionEvent -> eventSortButton());
    }

    // Events for filtering
    public void eventFilter() {
        // Get parameters from UI elements
        String searchQuery = searchField.getText();
        String genre = (String) genreComboBox.getSelectionModel().getSelectedItem();
        String releaseYear = (String) releaseYearComboBox.getSelectionModel().getSelectedItem();
        String rating = (String) ratingComboBox.getSelectionModel().getSelectedItem();
        Label exceptionLabel = new Label();

        int releaseYearValue = 0;
        if (releaseYear != null && !releaseYear.isEmpty() && !releaseYear.equals("ALL")) {
            releaseYearValue = Integer.parseInt(releaseYear);
        }
        double ratingValue = 0.0;
        if (rating != null && !rating.isEmpty() && !rating.equals("ALL")) {
            ratingValue = Double.parseDouble(rating);
        }
        List<Movie> matchingMovies = new ArrayList<>();

        // Fetch movies based on search parameters
        // Is genre "ALL"?
        try {
            if ("ALL".equals(genre)) {
                matchingMovies = movieAPI.fetchMovies(searchQuery, null, releaseYearValue, ratingValue);
            } else {
                matchingMovies = movieAPI.fetchMovies(searchQuery, genre, releaseYearValue, ratingValue);
            }
        } catch (MovieAPIException e) {
            exceptionHandler(exceptionLabel);
        }

        // Update UI with matching movies
        if (matchingMovies.isEmpty()) {
            if (movieListView.getPlaceholder() != exceptionLabel) {
                Label label = new Label("No results found");
                movieListView.setPlaceholder(label);
            }
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

    public void switchToWatchlistScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("watchlist-view.fxml"));
        Parent watchlistRoot = loader.load();
        Scene watchlistScene = new Scene(watchlistRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(watchlistScene);
        stage.show();
    }

    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream() // Convert movie list into movie objects stream
                .flatMap(movie -> movie.getMainCast().stream()) // Flatten movie stream objects into actors stream of movie's main cast
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Collect actors stream into map of keys (actors) & values (counts)
                .entrySet().stream() // Convert map into stream of map.entry objects
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey) // Extract key (actor) from max entry
                .orElse(null);
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
        if (startYear > endYear) {
            return movies.stream()
                    .filter(movie -> movie.getReleaseYear() >= endYear && movie.getReleaseYear() <= startYear)
                    .collect(Collectors.toList());
        } else {
            return movies.stream()
                    .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                    .collect(Collectors.toList());
        }
    }
    public void exceptionHandler(Label exceptionLabel){
        exceptionLabel.setText("Make sure you have a stable internet connection");
        movieListView.setPlaceholder(exceptionLabel);
        System.out.println("HIHI");
        observableMovies.clear();

    }
}
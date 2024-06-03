package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.*;
import at.ac.fhcampuswien.fhmdb.presentation.Alert;
import at.ac.fhcampuswien.fhmdb.presentation.MovieCell;
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
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomeController implements Initializable, Observer {
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
    @FXML
    private StackPane alertPane;
    private Alert alert;

    public static MovieAPI movieAPI = new MovieAPI();
    public static List<Movie> allMovies;
    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    public static boolean isInHome = true;
    MovieSorter movieSorter;
    private MovieRepository movieRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieSorter = new MovieSorter(observableMovies);
        alert = new Alert(alertPane);

        // Fetch movies & handle movie UI elements at start
        try {
            allMovies = movieAPI.fetchMovies(null, null, 0, 0);
            this.movieRepository = MovieRepository.getInstance();
            movieRepository.removeAll();
            movieRepository.addAllMovies(allMovies);
        } catch (MovieAPIException e) {
            allMovies = exceptionHandler(null, null, 0, 0);
        } catch (DatabaseException e) {
            System.out.println("Error initialising database: " + e.getMessage());
        }

        if (allMovies != null) {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell(AddToWatchlistClicked, null));
        }

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

        // Event handlers
        genreComboBox.setOnAction(event -> eventFilter());
        releaseYearComboBox.setOnAction(event -> eventFilter());
        ratingComboBox.setOnAction(event -> eventFilter());
        searchBtn.setOnAction(event -> eventFilter());
        searchField.setOnKeyReleased(event -> { if (event.getCode() == KeyCode.ENTER) { eventFilter(); } });
        sortBtn.setOnAction(actionEvent -> eventSortButton());
        watchlistBtn.setOnAction(actionEvent -> switchToWatchlist(actionEvent));

        // Register HomeController as an observer to WatchlistRepository
        WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();
        watchlistRepository.addObserver(this);
    }

    @Override
    public void update(String message) {
        alert.showRedAlert(message);
    }

    public void eventFilter() {
        // Get parameters from UI elements
        String searchQuery = searchField.getText();
        String genre = (String) genreComboBox.getSelectionModel().getSelectedItem();
        String releaseYear = (String) releaseYearComboBox.getSelectionModel().getSelectedItem();
        String rating = (String) ratingComboBox.getSelectionModel().getSelectedItem();

        int releaseYearValue = 0;
        if (releaseYear != null && !releaseYear.isEmpty() && !releaseYear.equals("ALL")) {
            releaseYearValue = Integer.parseInt(releaseYear);
        }
        double ratingValue = 0.0;
        if (rating != null && !rating.isEmpty() && !rating.equals("ALL")) {
            ratingValue = Double.parseDouble(rating);
        }

        // Fetch movies based on search parameters
        List<Movie> matchingMovies;
        try {
            if ("ALL".equals(genre)) {
                matchingMovies = movieAPI.fetchMovies(searchQuery, null, releaseYearValue, ratingValue);
            } else {
                matchingMovies = movieAPI.fetchMovies(searchQuery, genre, releaseYearValue, ratingValue);
            }
        } catch (MovieAPIException e) { // When internet connection fails
            matchingMovies = exceptionHandler(searchQuery, genre, releaseYearValue, ratingValue);
        }

        // Update UI with matching movies
        if (matchingMovies.isEmpty()) {
            Label label = new Label("No results found");
            movieListView.setPlaceholder(label);
            observableMovies.clear();
        } else {
            observableMovies.clear();
            observableMovies.addAll(matchingMovies);
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell(AddToWatchlistClicked, null));
        }
    }

    public void sortAlphabetically(boolean wantsAscSort, ObservableList<Movie> observableMovies) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        if (!wantsAscSort) {comparator = comparator.reversed();}
        observableMovies.sort(comparator);
    }

    public void eventSortButton() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            movieSorter.sortAscending();
            sortBtn.setText("Sort (desc)");
        } else {
            movieSorter.sortDescending();
            sortBtn.setText("Sort (asc)");
        }
    }

    public void switchToWatchlist(ActionEvent event) {
        try {
            isInHome = false;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("watchlist-view.fxml"));
            loader.setControllerFactory(new MyFactory());  // Set the custom factory here
            Parent watchlistRoot = loader.load();
            Scene watchlistScene = new Scene(watchlistRoot, 1111, 600);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(watchlistScene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error switching to watchlist: " + e.getMessage());
        }
    }

    protected final AddToWatchlistEventHandler<Movie> AddToWatchlistClicked = movie -> {
        try {
            WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();
            List<String> genreList = movie.getGenres();
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDescription(),
                    MovieEntity.genresToString(genreList),
                    movie.getReleaseYear(),
                    movie.getImgUrl(),
                    movie.getLengthInMinutes(),
                    movie.getRating()
            );
            watchlistRepository.addToWatchlist(watchlistMovie);
        } catch (DatabaseException e) {
            String errorMessage = "An error occurred while adding to watchlist. Please try again later.";
            System.out.println(errorMessage);
            e.getMessage();
        }
    };

    public List<Movie> exceptionHandler(String searchQuery, String genre, int releaseYearValue, double ratingValue) {
        try {
            alert.showRedAlert("Make sure you have a stable internet connection!");
            observableMovies.clear();

            List<Movie> listFromDatabase = MovieEntity.toMovies(movieRepository.getAllMovies());

            if (!listFromDatabase.isEmpty()) {
                return exceptionFilter(listFromDatabase, searchQuery, genre, releaseYearValue, ratingValue);
            }
            return null;
        } catch (DatabaseException e) {
            System.out.println("Error getting all movies from database: " + e.getMessage());
            return null;
        }
    }

    public List<Movie> exceptionFilter(List<Movie> movieList, String searchQuery, String genre, int releaseYear, double rating) {
        String search = (searchQuery == null) ? "" : searchQuery.trim().toLowerCase();

        return movieList.stream()
                .filter(movie ->
                        (genre == null || genre.equals("ALL") || movie.getGenres().contains(genre)) &&
                                (search.isEmpty() || movie.getTitle().toLowerCase().contains(search) || movie.getDescription().toLowerCase().contains(search)) &&
                                (releaseYear == 0 || movie.getReleaseYear() == releaseYear) &&
                                (rating == 0 || movie.getRating() >= rating))
                .collect(Collectors.toList());
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
}

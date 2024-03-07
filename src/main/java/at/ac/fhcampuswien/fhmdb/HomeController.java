package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
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

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;

public class HomeController implements Initializable {
    @FXML
    public JFXListView movieListView;
    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXButton searchBtn;
    @FXML
    public TextField searchField;
    @FXML
    public JFXButton sortBtn;
    public static String movieListFilepath = "src/main/resources/at/ac/fhcampuswien/fhmdb/movies.txt";
    public static List<Movie> allMovies = Movie.initializeMovies(movieListFilepath);
    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // Automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize UI stuff
        observableMovies.addAll(allMovies); // Add dummy data to observable list
        movieListView.setItems(observableMovies); // Set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // Use custom cell factory to display data

        // Add genre filters
        String[] genres = {"ALL", "ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME",
                "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL",
                "MYSTERY", "ROMANCE", "SCIENCE FICTION", "SPORT", "THRILLER", "WAR", "WESTERN"};

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(genres);
        genreComboBox.setOnAction(event -> searchMatch(allMovies, searchField.getText(), (String) genreComboBox.getSelectionModel().getSelectedItem())); // Call a new method for filtering movies

        // Search button's event handlers
        searchBtn.setOnAction(event -> { searchMatch(allMovies, searchField.getText(), (String) genreComboBox.getSelectionModel().getSelectedItem()); });
        searchField.setOnKeyReleased(event -> { if (event.getCode() == KeyCode.ENTER) { searchMatch(allMovies, searchField.getText(), (String) genreComboBox.getSelectionModel().getSelectedItem()); } });

        // Sort button's event handler
        sortBtn.setOnAction(actionEvent -> { eventSortBtn(); });
    }

    public void searchMatch(List<Movie> movieList, String searchQuery, String genre) { // Filters list of all movies based on search substring
        String search = searchQuery.trim().toLowerCase();

        List<Movie> matchingMovies = movieList.stream().filter(movie ->
                (genre == null || genre.equals("ALL") || movie.getGenres().contains(genre)) &&
                (search.isEmpty() || movie.getTitle().toLowerCase().contains(search) || movie.getDescription().toLowerCase().contains(search)))
            .collect(Collectors.toList());

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

    /*public void eventSearchBtn(){ // Events for search button
        List<Movie> matchingMovies = searchMatch();

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
    */




    public void eventSortBtn(){ // Events for sort button
        if (sortBtn.getText().equals("Sort (asc)")) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle)); // Ascending sort by title
            sortBtn.setText("Sort (desc)");
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed()); // Descending sort by title
            sortBtn.setText("Sort (asc)");
        }
    }
}
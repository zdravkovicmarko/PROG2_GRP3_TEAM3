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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public static String movieListFilepath = "src/main/resources/at/ac/fhcampuswien/fhmdb/movies.txt";
    public static List<Movie> allMovies = Movie.initializeMovies(movieListFilepath);

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public List<Movie> findUsingStream() {
        String search = searchField.getText().trim().toLowerCase();
        List<Movie> matchingMovies = allMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(search) ||
                        movie.getDescription().toLowerCase().contains(search))
                .collect(Collectors.toList());

        return matchingMovies;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll("ALL");
        genreComboBox.getItems().addAll("ACTION");
        genreComboBox.getItems().addAll("ADVENTURE");
        genreComboBox.getItems().addAll("ANIMATION");
        genreComboBox.getItems().addAll("BIOGRAPHY");
        genreComboBox.getItems().addAll("COMEDY");
        genreComboBox.getItems().addAll("CRIME");
        genreComboBox.getItems().addAll("DRAMA");
        genreComboBox.getItems().addAll("DOCUMENTARY");
        genreComboBox.getItems().addAll("FAMILY");
        genreComboBox.getItems().addAll("FANTASY");
        genreComboBox.getItems().addAll("HISTORY");
        genreComboBox.getItems().addAll("HORROR");
        genreComboBox.getItems().addAll("MUSICAL");
        genreComboBox.getItems().addAll("MYSTERY");
        genreComboBox.getItems().addAll("ROMANCE");
        genreComboBox.getItems().addAll("SCIENCE FICTION");
        genreComboBox.getItems().addAll("SPORT");
        genreComboBox.getItems().addAll("THRILLER");
        genreComboBox.getItems().addAll("WAR");
        genreComboBox.getItems().addAll("WESTERN");



        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(event -> {
            List<Movie> matchingMovies = findUsingStream();
            ObservableList<Movie> observableMatchingMovies = FXCollections.observableArrayList(matchingMovies);
            movieListView.setItems(observableMatchingMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });

        searchField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                List<Movie> matchingMovies = findUsingStream();
                ObservableList<Movie> observableMatchingMovies = FXCollections.observableArrayList(matchingMovies);
                movieListView.setItems(observableMatchingMovies);
                movieListView.setCellFactory(movieListView -> new MovieCell());
            }
        });

    }
}
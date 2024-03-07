package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.HomeController.allMovies;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

HomeController homeController = new HomeController();
    @Test
    void playing_around_with_first_test(){ //This test needs to be optimised
        assertTrue(movieExists());
    }
    private boolean movieExists() {
        for (Movie movie : allMovies) {
            if (movie.getTitle().equals("Avatar")) {
                return true;
            }
        }
        return false;
    }
    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_AA_TestFilm2_is_the_first_entry(){
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");


        movies.add(new Movie("ZZ_TestFilm1", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm2", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));

        movies.sort(Comparator.comparing(Movie::getTitle));
        String firstMovie = movies.get(0).getTitle();
        assertEquals(firstMovie, "AA_TestFilm2");
    }
    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_GG_Testfilm3_is_the_second_entry(){
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");

        movies.add(new Movie("ZZ_TestFilm1", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm2", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));

        movies.sort(Comparator.comparing(Movie::getTitle));
        String secondMovie = movies.get(1).getTitle();
        assertEquals(secondMovie, "GG_TestFilm3");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_ZZ_Testfilm1_is_the_last_entry(){
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");

        movies.add(new Movie("ZZ_TestFilm1", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm2", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));

        movies.sort(Comparator.comparing(Movie::getTitle));
        String lastMovie = movies.get(2).getTitle();
        assertEquals(lastMovie, "ZZ_TestFilm1");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_enter_searchString() { //This test is not working yet
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");
        List<String> genre4 = Arrays.asList("FAMILY", "SCIENCE FICTION");
        String search = "BB";



        movies.add(new Movie("ZZ_TestFilm26", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm1", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));
        movies.add(new Movie("BB_TestFilm2", "This Movie is a test", genre4));


        movies.sort(Comparator.comparing(Movie::getTitle));
        homeController.searchField.setText(search);
        homeController.eventSearchBtn();
        String lastMovie = movies.get(2).getTitle();
        assertEquals(lastMovie, "ZZ_TestFilm1");

    }

    @Test
    void sort_movies_alphabetically_asc_and_check_whether_expected_list_matches_actual_list(){
        // Given
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");
        List<String> genre4 = Arrays.asList("FAMILY", "SCIENCE FICTION");

        movies.add(new Movie("ZZ_TestFilm26", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm1", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));
        movies.add(new Movie("BB_TestFilm2", "This Movie is a test", genre4));

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(movies);
        boolean wantsAscSort = true;

        // When
        homeController.sortAlphabetically(wantsAscSort, observableMovies);

        // Then
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort(Comparator.comparing(Movie::getTitle));
        assertEquals(sortedMovies, observableMovies);
    }

    @Test
    void sort_movies_alphabetically_desc_and_check_whether_expected_list_matches_actual_list(){
        // Given
        List<Movie> movies = new ArrayList<>();
        List<String> genre1 = Arrays.asList("ACTION", "COMEDY");
        List<String> genre2 = Arrays.asList("ACTION", "SPORT");
        List<String> genre3 = Arrays.asList("WAR", "SPORT");
        List<String> genre4 = Arrays.asList("FAMILY", "SCIENCE FICTION");

        movies.add(new Movie("ZZ_TestFilm26", "This Movie is a test", genre1));
        movies.add(new Movie("AA_TestFilm1", "This Movie is a test", genre2));
        movies.add(new Movie("GG_TestFilm3", "This Movie is a test", genre3));
        movies.add(new Movie("BB_TestFilm2", "This Movie is a test", genre4));

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(movies);
        boolean wantsAscSort = false;

        // When
        homeController.sortAlphabetically(wantsAscSort, observableMovies);

        // Then
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        assertEquals(sortedMovies, observableMovies);
    }
}
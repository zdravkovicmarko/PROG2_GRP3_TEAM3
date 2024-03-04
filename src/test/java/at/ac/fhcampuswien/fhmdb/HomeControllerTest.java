package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import static at.ac.fhcampuswien.fhmdb.HomeController.allMovies;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
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
}
package at.ac.fhcampuswien.zin;

import at.ac.fhcampuswien.zin.models.Movie;
import org.junit.jupiter.api.Test;

import static at.ac.fhcampuswien.zin.HomeController.allMovies;
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
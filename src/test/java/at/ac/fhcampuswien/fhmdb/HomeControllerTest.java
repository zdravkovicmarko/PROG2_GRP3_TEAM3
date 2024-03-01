package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static at.ac.fhcampuswien.fhmdb.HomeController.allMovies;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void playing_around_with_first_test(){
        Movie Avatar;
        assertTrue(movieExists("Avatar"));
    }
    private boolean movieExists(String title) {
        for (Movie movie : allMovies) {
            if (movie.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
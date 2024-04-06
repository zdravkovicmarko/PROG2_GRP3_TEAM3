package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

HomeController homeController = new HomeController();

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_Inception_is_the_first_entry(){
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        movies.sort(Comparator.comparing(Movie::getTitle));
        String firstMovie = movies.get(0).getTitle();
        assertEquals(firstMovie, "Inception");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_The_Boy_and_the_Heron_is_the_second_entry(){
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        movies.sort(Comparator.comparing(Movie::getTitle));

        String secondMovie = movies.get(1).getTitle();
        assertEquals(secondMovie, "The Boy and the Heron");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_The_Shawshank_is_the_last_entry(){
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        movies.sort(Comparator.comparing(Movie::getTitle));
        String lastMovie = movies.get(4).getTitle();
        assertEquals(lastMovie, "The Shawshank Redemption");
    }

    @Test
    void query_ignores_uppercase(){
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "SHAWSHANK";
        String genre = null;

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_ignores_lowercase(){
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "redemption";
        String genre = null;

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_matches_movie_title_but_not_description_and_has_no_genre() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "Dark";
        String genre = null;

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie4);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_matches_movie_title_but_not_description_and_has_genre() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "Redemption";
        String genre = "Drama";

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_matches_movie_description_but_not_title_and_has_no_genre(){
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "thief";
        String genre = null;

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_matches_movie_description_but_not_title_and_has_genre() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "CEO";
        String genre = "Action";

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void query_doesnt_match_movies_displaying_no_results() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String search = "MARTINI";
        String genre = null;

        // When & Then
        List<Movie> filteredMovies = homeController.filter(movies, search, genre);
        List<Movie> expectedMovies = new ArrayList<>();
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void sort_movies_alphabetically_asc_and_check_whether_expected_list_matches_actual_list(){
        // Given
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(movies);

        // When
        homeController.sortAlphabetically(true, observableMovies);

        // Then
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort(Comparator.comparing(Movie::getTitle));
        assertEquals(sortedMovies, observableMovies);
    }


    @Test
    void sort_movies_alphabetically_desc_and_check_whether_expected_list_matches_actual_list(){
        // Given
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(movies);

        // When
        homeController.sortAlphabetically(false, observableMovies);

        // Then
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        assertEquals(sortedMovies, observableMovies);
    }


    @Test
    void einTest() {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2007, 2011);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);
        expectedMovies.add(movie4);
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void andererTest() {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        long numberOfMovies = homeController.countMoviesFrom(movies, "Christopher Nolan");

        long expectedNumber = 2;

        assertEquals(expectedNumber, numberOfMovies);
    }

    @Test
    void Test3() {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);


        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        String actor = homeController.getMostPopularActor(movies);

        String expectedActor = "Leonardo DiCaprio";

        assertEquals(expectedActor, actor);
    }

    @Test
    void Test4() {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        int longestTitle = homeController.getLongestMovieTitle(movies);

        int expectedNumberOfCharacters = 24;

        assertEquals(expectedNumberOfCharacters, longestTitle);
    }


}
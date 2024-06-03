package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

HomeController homeController = new HomeController();

List<Movie> movies = new ArrayList<>();

@BeforeEach
public void movieList() {
    Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023, "In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.", "https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"), 7.6);
    Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
    Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
    Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
    Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

    movies.add(movie1);
    movies.add(movie2);
    movies.add(movie3);
    movies.add(movie4);
    movies.add(movie5);
}


    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_Inception_is_the_first_entry(){
        // Given initialized in movieList

        // When
        movies.sort(Comparator.comparing(Movie::getTitle));
        String firstMovie = movies.get(0).getTitle();

        // Then
        assertEquals(firstMovie, "Inception");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_The_Boy_and_the_Heron_is_the_second_entry(){
        // Given initialized in movieList

        // When
        movies.sort(Comparator.comparing(Movie::getTitle));
        String secondMovie = movies.get(1).getTitle();

        // Then
        assertEquals(secondMovie, "The Boy and the Heron");
    }

    @Test
    void sorting_movies_alphabetically_asc_and_check_whether_The_Shawshank_is_the_last_entry(){
        // Given initialized in movieList

        // When
        movies.sort(Comparator.comparing(Movie::getTitle));
        String lastMovie = movies.get(4).getTitle();

        // Then
        assertEquals(lastMovie, "The Shawshank Redemption");
    }

    @Test
    void sort_movies_alphabetically_asc_and_check_whether_expected_list_matches_actual_list(){
        // Given initialized in movieList
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
        // Given MovieList initialized in movieList
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
    void getMostPopularActor_returns_0_if_actor_list_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList(""),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList(""), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList(""), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList(""), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList(""), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        // When
        String mostPopularActor = homeController.getMostPopularActor(movies);
        String expectedActor = "";

        // Then
        assertEquals(expectedActor, mostPopularActor);
    }

    @Test
    void getMostPopularActor_returns_null_if_movie_list_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();

        // When
        String mostPopularActor = homeController.getMostPopularActor(movies);
        String expectedActor = null;

        // Then
        assertEquals(expectedActor, mostPopularActor);
    }

    @Test
    void getMostPopularActor_returns_correct_actor_if_appearing_multiple_times() {
        // Given initialized in movieList

        // When
        String actor = homeController.getMostPopularActor(movies);
        String expectedActor = "Leonardo DiCaprio";

        // Then
        assertEquals(expectedActor, actor);
    }

    @Test
    void getMostPopularActor_returns_last_actor_if_multiple_popular_actors_present() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList("Frank Darabont"), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList("Christopher Nolan"), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList("Christopher Nolan"), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList("Alejandro González Iñárritu"), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        // When
        String actor = homeController.getMostPopularActor(movies);
        String expectedActor = "Will Poulter";

        // Then
        assertEquals(expectedActor, actor);
    }

    @Test
    void getLongestMovieTitle_returns_null_if_movie_list_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();

        // When
        int longestTitle = homeController.getLongestMovieTitle(movies);
        int expectedNumberOfCharacters = 0;

        // Then
        assertEquals(expectedNumberOfCharacters, longestTitle);
    }

    @Test
    void getLongestMovieTitle_returns_longest_title_length() {
        // Given initialized in movieList

        // When
        int longestTitle = homeController.getLongestMovieTitle(movies);
        int expectedNumberOfCharacters = 24;

        // Then
        assertEquals(expectedNumberOfCharacters, longestTitle);
    }

    @Test
    void countMoviesFrom_returns_0_if_no_movies_from_the_director_are_in_the_list() {
        // Given initialized in movieList

        // When
        long numberOfMovies = homeController.countMoviesFrom(movies, "Steven Spielberg");
        long expectedNumber = 0;

        // Then
        assertEquals(expectedNumber, numberOfMovies);
    }

    @Test
    void countMoviesFrom_returns_0_if_movie_list_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();

        // When
        long numberOfMovies = homeController.countMoviesFrom(movies, "Christopher Nolan");
        long expectedNumber = 0;

        // Then
        assertEquals(expectedNumber, numberOfMovies);
    }

    @Test
    void countMoviesFrom_returns_0_if_list_of_directors_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("1", "The Boy and the Heron", Collections.singletonList("Animation, Adventure, Drama"), 2023,"In the wake of his mother's death and his father's remarriage, a headstrong boy named Mahito ventures into a dreamlike world shared by both the living and the dead.","https://m.media-amazon.com/images/M/MV5BZjZkNThjNTMtOGU0Ni00ZDliLThmNGUtZmMxNWQ3YzAxZTQ1XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_SX300.jpg", 124, Collections.singletonList(""), Collections.singletonList("Hayao Miyazaki"), Collections.singletonList("Soma Santoki, Masaki Suda, Kô Shibasaki"),7.6);
        Movie movie2 = new Movie("2", "The Shawshank Redemption", Collections.singletonList("Drama"), 1994, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "example.com/shawshank_redemption", 142, Collections.singletonList(""), Collections.singletonList("Stephen King (short story 'Rita Hayworth and Shawshank Redemption'), Frank Darabont (screenplay)"), Collections.singletonList("Tim Robbins, Morgan Freeman"), 9.3);
        Movie movie3 = new Movie("3", "Inception", Arrays.asList("Action", "Adventure", "Sci-Fi"), 2010, "A thief who enters the dreams of others to steal their secrets must plant an idea into a CEO's mind.", "example.com/inception", 148, Collections.singletonList(""), Collections.singletonList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), 8.8);
        Movie movie4 = new Movie("4", "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "example.com/dark_knight", 152, Collections.singletonList(""), Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), 9.0);
        Movie movie5 = new Movie("5", "The Revenant", Arrays.asList("Adventure", "Drama", "Thriller"), 2015, "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.", "example.com/the_revenant", 156, Collections.singletonList(""), Collections.singletonList("Mark L. Smith (screenplay), Alejandro González Iñárritu (screenplay)"), Arrays.asList("Leonardo DiCaprio", "Tom Hardy", "Will Poulter"), 8.0);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        // When
        long numberOfMovies = homeController.countMoviesFrom(movies, "Christopher Nolan");
        long expectedNumber = 0;

        // Then
        assertEquals(expectedNumber, numberOfMovies);
    }

    @Test
    void countMoviesFrom_returns_actual_number_of_movies_if_director_appears_multiple_times() {
        // Given initialized in movieList

        // When
        long numberOfMovies = homeController.countMoviesFrom(movies, "Christopher Nolan");
        long expectedNumber = 2;

        // Then
        assertEquals(expectedNumber, numberOfMovies);
    }

    @Test
    void getMoviesBetweenYears_returns_empty_list_if_movie_list_is_empty() {
        // Given
        List<Movie> movies = new ArrayList<>();

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2007, 2011);
        List<Movie> expectedMovies = new ArrayList<>();

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void getMoviesBetweenYears_returns_empty_list_if_no_movies_are_between_specified_years() {
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2003, 2007);
        List<Movie> expectedMovies = new ArrayList<>();

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void  getMoviesBetweenYears_returns_one_movie_if_movie_is_between_specified_years() {
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2003, 2009);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void  getMoviesBetweenYears_returns_multiple_movies_if_movies_are_between_specified_years() {
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 1900, 2009);
        List<Movie> expectedMovies = new ArrayList<>();

        expectedMovies.add(movies.get(1));
        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void  getMoviesBetweenYears_swaps_years_if_start_year_greater_than_end_year() {
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2009, 1900);
        List<Movie> expectedMovies = new ArrayList<>();

        expectedMovies.add(movies.get(1));
        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void getMoviesBetweenYears_returns_movie_if_year_matches_end_year_exactly(){
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2000, 2008);
        List<Movie> expectedMovies = new ArrayList<>();

        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void getMoviesBetweenYears_returns_movie_if_year_matches_start_year_exactly(){
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2008, 2009);
        List<Movie> expectedMovies = new ArrayList<>();

        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    void getMoviesBetweenYears_returns_movie_if_year_matches_start_year_and_end_year_exactly(){
        // Given initialized in movieList

        // When
        List<Movie> filteredMovies = homeController.getMoviesBetweenYears(movies, 2008, 2008);
        List<Movie> expectedMovies = new ArrayList<>();

        expectedMovies.add(movies.get(3));

        // Then
        assertEquals(expectedMovies, filteredMovies);
    }

    @Test
    public void testSingletonInstances() {
        MyFactory factory = new MyFactory();

        HomeController homeController1 = (HomeController) factory.call(HomeController.class);
        HomeController homeController2 = (HomeController) factory.call(HomeController.class);
        assertSame(homeController1, homeController2, "HomeController instances are not the same");

        WatchlistController watchlistController1 = (WatchlistController) factory.call(WatchlistController.class);
        WatchlistController watchlistController2 = (WatchlistController) factory.call(WatchlistController.class);
        assertSame(watchlistController1, watchlistController2, "WatchlistController instances are not the same");
    }
}

    /*
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

*/
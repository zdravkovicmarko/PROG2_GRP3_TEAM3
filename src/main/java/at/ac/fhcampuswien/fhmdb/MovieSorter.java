package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.Movie;

import java.util.List;

public class MovieSorter {
    private SortStates sortState;
    private List<Movie> movies;

    public MovieSorter(List<Movie> movies) {
        this.movies = movies;
        this.sortState = new SortStates(); // Initial state
    }

    public void sort() {
        sortState.sort(movies);
    }

    public void setSortState(SortStates.State state) {
        sortState.setState(state);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    // Methods to switch states
    public void sortAscending() {
        setSortState(SortStates.State.ASCENDING);
        sort();
    }

    public void sortDescending() {
        setSortState(SortStates.State.DESCENDING);
        sort();
    }

    public void resetSort() {
        setSortState(SortStates.State.NOT_SORTED);
        sort();
    }
}


package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortStates {
    public enum State {
        NOT_SORTED,
        ASCENDING,
        DESCENDING
    }

    private State currentState;

    public SortStates() {
        this.currentState = State.NOT_SORTED; // Initial state
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getState() {
        return currentState;
    }

    public void sort(List<Movie> movies) {
        switch (currentState) {
            case NOT_SORTED:
                // No sorting is done
                break;
            case ASCENDING:
                Collections.sort(movies, Comparator.comparing(Movie::getTitle));
                break;
            case DESCENDING:
                Collections.sort(movies, Comparator.comparing(Movie::getTitle).reversed());
                break;
        }
    }
}


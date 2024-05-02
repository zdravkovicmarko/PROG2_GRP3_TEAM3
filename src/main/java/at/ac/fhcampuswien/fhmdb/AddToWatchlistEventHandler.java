package at.ac.fhcampuswien.fhmdb;

@FunctionalInterface
public interface AddToWatchlistEventHandler<T> {
    void addOnClick(T t);
}


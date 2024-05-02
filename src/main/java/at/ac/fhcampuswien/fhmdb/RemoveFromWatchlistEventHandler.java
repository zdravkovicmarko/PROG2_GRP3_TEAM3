package at.ac.fhcampuswien.fhmdb;

@FunctionalInterface
public interface RemoveFromWatchlistEventHandler<T> {
    void removeOnClick(T t);
}

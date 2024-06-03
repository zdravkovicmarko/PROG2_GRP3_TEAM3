package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import at.ac.fhcampuswien.fhmdb.Observable;
import at.ac.fhcampuswien.fhmdb.Observer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {
    private static WatchlistRepository instance;
    Dao<WatchlistMovieEntity, Long> dao;
    private final List<Observer> observers = new ArrayList<>();

    private WatchlistRepository() {
        this.dao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    public static synchronized WatchlistRepository getInstance() {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            List<WatchlistMovieEntity> existingMovies = dao.queryForEq("apiId", movie.getApiId());

            if (existingMovies.isEmpty()) { // Movie with same apiId in watchlist?
                dao.create(movie);
                notifyObservers("Movie added to the watchlist successfully.");
                return 1;
            } else {
                notifyObservers("Movie is already in the watchlist.");
                return 0;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException {
        try {
            DeleteBuilder<WatchlistMovieEntity, Long> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("apiId", apiId);
            int rowsDeleted = deleteBuilder.delete();
            return rowsDeleted;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}

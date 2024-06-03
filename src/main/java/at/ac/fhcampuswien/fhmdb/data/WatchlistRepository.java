package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository  {

    private static WatchlistRepository instance;
    Dao<WatchlistMovieEntity, Long> dao;

    private WatchlistRepository() {
        this.dao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    public static synchronized WatchlistRepository getInstance() {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
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
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return 0;
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

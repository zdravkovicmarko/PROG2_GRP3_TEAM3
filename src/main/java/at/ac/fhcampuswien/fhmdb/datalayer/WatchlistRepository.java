package at.ac.fhcampuswien.fhmdb.datalayer;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository  {

    Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository() throws SQLException {
        this.dao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return dao.queryForAll();
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws SQLException {
        List<WatchlistMovieEntity> existingMovies = dao.queryForEq("apiId", movie.getApiId());

        if (existingMovies.isEmpty()) { // Movie with same apiId in watchlist?
            dao.create(movie);          // No, add movie
            return 0;
        }
        return 0;                       // Yes, don't add duplicate
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        dao.delete((WatchlistMovieEntity) dao.queryBuilder().where().eq("apiId", apiId).prepare());
        return 0;
    }
}

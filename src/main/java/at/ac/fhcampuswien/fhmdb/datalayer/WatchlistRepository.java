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
        dao.create(movie);
        return 0;
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        return dao.delete((WatchlistMovieEntity) dao.queryBuilder().where().eq("apiId", apiId).prepare());
    }


}

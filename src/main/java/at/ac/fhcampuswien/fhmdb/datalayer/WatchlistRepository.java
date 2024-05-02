package at.ac.fhcampuswien.fhmdb.datalayer;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository  {

    Dao<WatchlistMovieEntity, Long> dao;

    // TODO
    public List<WatchlistMovieEntity> getWatchlist() {
        return null;
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws SQLException {
        dao.create(movie);
        return 0;
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        return dao.delete((WatchlistMovieEntity) dao.queryBuilder().where().eq("apiId", apiId).prepare());
    }


}

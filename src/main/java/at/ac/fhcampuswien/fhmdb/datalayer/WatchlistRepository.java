package at.ac.fhcampuswien.fhmdb.datalayer;

import com.j256.ormlite.dao.Dao;

import java.util.List;

public class WatchlistRepository {

    Dao<WatchlistMovieEntity, Long> dao;

    public List<WatchlistMovieEntity> getWatchlist() {
        return null;
    }

    public int addToWatchlist(WatchlistMovieEntity movie) {
        return 0;
    }

    public int removeFromWatchlist(String apiId) {
        return 0;
    }
}

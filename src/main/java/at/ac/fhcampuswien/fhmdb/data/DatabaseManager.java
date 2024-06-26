package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.*;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:h2:file:./db/moviedb;DATABASE_TO_UPPER=false";
    public static final String username = "user";
    public static final String password = "pass";
    static ConnectionSource connectionSource;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            createConnectionSource();
            createDaos();
            createTables();
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }

    // Creates Data Access Objects (DAOs) for movie & watchlist movie entities.
    public void createDaos() throws DatabaseException {
        try {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
        watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static DatabaseManager getDatabaseManager() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private static void createConnectionSource() throws DatabaseException {
        try {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    private static void createTables() throws DatabaseException {
        try {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public Dao<MovieEntity, Long> getMovieDao() {
        return this.movieDao;
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao() {
        return this.watchlistDao;
    }
}
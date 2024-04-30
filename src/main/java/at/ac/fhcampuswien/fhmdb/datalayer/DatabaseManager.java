package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:h2:file:./db/moviedb";
    public static final String username = "user";
    public static final String password = "pass";
    private static ConnectionSource connectionSource;
    Dao<MovieEntity, Long> movieDao;
    Dao<WatchlistMovieEntity, Long> watchlistDao;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            createConnectionSource();
            movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            clearTables();
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseManager getDatabaseManager () throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private static void createConnectionSource () throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
    }
    private static void clearTables() throws SQLException {
        TableUtils.clearTable(connectionSource, MovieEntity.class);
    }

    public void testDB() throws SQLException {
        int databaseID = 1;
        if (HomeController.allMovies != null) {
            for (Movie movie : HomeController.allMovies) {
                List<String> genreList = movie.getGenres();
                StringJoiner stringJoiner = new StringJoiner(", ");
                for (String str : genreList) {
                    stringJoiner.add(str);
                }
                String genreAsString = stringJoiner.toString();

                MovieEntity movieEntity = new MovieEntity(
                        databaseID,
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        genreAsString,
                        movie.getReleaseYear(),
                        movie.getImgUrl(),
                        movie.getLengthInMinutes(),
                        movie.getRating()
                );
                movieDao.create(movieEntity);
                databaseID++;
            }
        }
    }
    // TO DO
    public Dao<MovieEntity, Long> getMovieDao() {
        return movieDao;
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao() {
        return watchlistDao;
    }
}
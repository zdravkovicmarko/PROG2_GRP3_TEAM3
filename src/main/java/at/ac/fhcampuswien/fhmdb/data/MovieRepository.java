package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.data.DatabaseManager.connectionSource;

public class MovieRepository {

    Dao<MovieEntity, Long> dao;
    private static MovieRepository instance;

    // Private constructor
    private MovieRepository() {
        this.dao = DatabaseManager.getDatabaseManager().getMovieDao();
    }

    // Public method to provide access to the singleton instance
    public static synchronized MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
        return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public int removeAll() throws DatabaseException {
        try {
            TableUtils.clearTable(connectionSource, MovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return 0;
    }

    public MovieEntity getMovie(long id) throws DatabaseException {
        try {
        return dao.queryForId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public int addAllMovies(List<Movie> movies) throws DatabaseException {
        if (movies != null) {
            List<MovieEntity> movieEntities = MovieEntity.fromMovies(movies);
            try {
                for (MovieEntity movieEntity : movieEntities) {
                    dao.create(movieEntity);
                }
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }
        return 0;
    }
}

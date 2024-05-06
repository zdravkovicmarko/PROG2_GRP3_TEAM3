package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

import static at.ac.fhcampuswien.fhmdb.data.DatabaseManager.connectionSource;

public class MovieRepository {

    Dao<MovieEntity, Long> dao;

    public MovieRepository() {
        this.dao = DatabaseManager.getDatabaseManager().getMovieDao();
    }

    public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
        return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static int removeAll() throws DatabaseException {
        try {
            TableUtils.clearTable(connectionSource, MovieEntity.class);
            return 0;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public MovieEntity getMovie(long id) throws DatabaseException {
        try {
        return dao.queryForId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public int addAllMovies(List<Movie> movies) throws DatabaseException {
        try {
        if (movies != null) {
            for (Movie movie : movies) {
                List<String> genreList = movie.getGenres();
                StringJoiner stringJoiner = new StringJoiner(", ");
                for (String str : genreList) {
                    stringJoiner.add(str);
                }
                String genreAsString = stringJoiner.toString();

                MovieEntity movieEntity = new MovieEntity(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        genreAsString,
                        movie.getReleaseYear(),
                        movie.getImgUrl(),
                        movie.getLengthInMinutes(),
                        movie.getRating()
                );
                dao.create(movieEntity);
            }
        }
        return 0;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}

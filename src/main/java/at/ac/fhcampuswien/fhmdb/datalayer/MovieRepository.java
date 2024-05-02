package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

import static at.ac.fhcampuswien.fhmdb.datalayer.DatabaseManager.connectionSource;

public class MovieRepository {

    Dao<MovieEntity, Long> dao;

    public MovieRepository() throws SQLException {
        this.dao = DatabaseManager.getDatabaseManager().getMovieDao();
    }

    public List<MovieEntity> getAllMovies() throws SQLException {
        return dao.queryForAll();
    }

    public static int removeAll() throws SQLException {
        TableUtils.clearTable(connectionSource, MovieEntity.class);
        return 0;
    }

    // TODO
    public MovieEntity getMovie() {
        return null;
    }


    public int addAllMovies(List<Movie> movies) throws SQLException {
        if (HomeController.allMovies != null) {
            for (Movie movie : HomeController.allMovies) {
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
    }
}

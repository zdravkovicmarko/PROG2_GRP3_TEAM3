package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class MovieRepository {

    Dao<MovieEntity, Long> dao;

    public List<MovieEntity> getAllMovies(){
        return null;
    }

    public int removeAll() {
        return 0;
    }

    public MovieEntity getMovie() {
        return null;
    }

    public int addAllMovies(List<Movie> movies) {
        return 0;
    }
}

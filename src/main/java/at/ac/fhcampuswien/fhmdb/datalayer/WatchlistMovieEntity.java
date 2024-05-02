package at.ac.fhcampuswien.fhmdb.datalayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "WATCHLIST")
public class WatchlistMovieEntity extends MovieEntity {

    private long id;
    private String apiId;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        super(apiId, title, description, genres, releaseYear, imgUrl, lengthInMinutes, rating);
    }
}

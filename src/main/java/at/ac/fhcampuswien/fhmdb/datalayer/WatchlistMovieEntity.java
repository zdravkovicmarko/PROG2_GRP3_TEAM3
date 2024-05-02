package at.ac.fhcampuswien.fhmdb.datalayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "WATCHLIST")
public class WatchlistMovieEntity extends MovieEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField()
    private String apiId;

    public WatchlistMovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        super(apiId, title, description, genres, releaseYear, imgUrl, lengthInMinutes, rating);
    }
}

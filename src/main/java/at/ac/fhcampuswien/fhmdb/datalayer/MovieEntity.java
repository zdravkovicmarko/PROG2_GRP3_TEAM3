package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable (tableName = "MOVIE")
public class MovieEntity {
    @DatabaseField ()
    private long id;

    @DatabaseField()
    private String apiId;

    @DatabaseField(columnName = "title")
    private String title;

    @DatabaseField(columnName = "description")
    private String description;

    @DatabaseField(columnName = "genres")
    private String genres;

    @DatabaseField(columnName = "releaseYear")
    private int releaseYear;

    @DatabaseField(columnName = "imgUrl")
    private String imgUrl;

    @DatabaseField(columnName = "lengthInMinutes")
    private int lengthInMinutes;

    @DatabaseField(columnName = "rating")
    private double rating;


    
    public MovieEntity() {
    }

    public MovieEntity(int id, String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.id = id;
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenres() {
        return genres;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    // TO DO
    public String genresToString (List<String> genres) {
        return null;
    }

    public List<MovieEntity> fromMovies (List<Movie> movies) {
        return null;
    }

    public List<Movie> toMovies (List<MovieEntity> movieEntities) {
        return null;
    }

}

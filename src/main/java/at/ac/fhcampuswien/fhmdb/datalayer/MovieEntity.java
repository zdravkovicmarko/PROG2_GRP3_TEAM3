package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@DatabaseTable (tableName = "MOVIE")
public class MovieEntity {
    @DatabaseField (generatedId = true)
    private long id;

    @DatabaseField(columnName = "APIID")
    private String apiId;

    @DatabaseField(columnName = "TITLE")
    private String title;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "GENRES")
    private String genres;

    @DatabaseField(columnName = "RELEASEYEAR")
    private int releaseYear;

    @DatabaseField(columnName = "IMGURL")
    private String imgUrl;

    @DatabaseField(columnName = "LENGTHINMINUTES")
    private int lengthInMinutes;

    @DatabaseField(columnName = "RATING")
    private double rating;


    
    public MovieEntity() {
    }

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public long getId() {
        return id;
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

    public static String genresToString (List<String> genres) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (String str : genres) {
                stringJoiner.add(str);
            }
        return stringJoiner.toString();

    }

    public List<MovieEntity> fromMovies (List<Movie> movies) {
        int databaseID = 1;
        if (movies != null) {
            List<MovieEntity> movieEntities = new ArrayList<>();
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

                movieEntities.add(movieEntity);
                databaseID++;
            }
            return movieEntities;
        }
        return null;
    }

    // TODO
    public List<Movie> toMovies (List<MovieEntity> movieEntities) {
        return null;
    }

}

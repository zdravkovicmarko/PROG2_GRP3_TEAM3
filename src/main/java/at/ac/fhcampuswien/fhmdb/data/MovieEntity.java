package at.ac.fhcampuswien.fhmdb.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@DatabaseTable (tableName = "MOVIE")
public class MovieEntity {
    @DatabaseField (generatedId = true)
    private long id;

    @DatabaseField()
    private String apiId;

    @DatabaseField()
    private String title;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private String genres;

    @DatabaseField()
    private int releaseYear;

    @DatabaseField()
    private String imgUrl;

    @DatabaseField()
    private int lengthInMinutes;

    @DatabaseField()
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

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        if (movies != null) {
            List<MovieEntity> movieEntities = new ArrayList<>();
            for (Movie movie : movies) {
                String genreAsString = genresToString(movie.getGenres());

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
            }
            return movieEntities;
        }
        return null;
    }


    public static List<Movie> toMovies(List<? extends MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();

        for (MovieEntity movieEntity : movieEntities) {
            String genreAsString = movieEntity.getGenres();
            List<String> genreList = Arrays.asList(genreAsString.split(", "));
            List<String> directorsList = new ArrayList<>();
            List<String> writersList = new ArrayList<>();
            List<String> mainCastList = new ArrayList<>();

            movies.add(new Movie(
                    movieEntity.getApiId(),
                    movieEntity.getTitle(),
                    genreList,
                    movieEntity.getReleaseYear(),
                    movieEntity.getDescription(),
                    movieEntity.getImgUrl(),
                    movieEntity.getLengthInMinutes(),
                    directorsList,
                    writersList,
                    mainCastList,
                    movieEntity.getRating()
            ));
        }
        return movies;
    }

}

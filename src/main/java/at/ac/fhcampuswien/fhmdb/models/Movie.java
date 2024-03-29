package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;

public class Movie {
    private String id;
    private String title;
    private List<String> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private double rating;

    public Movie(String id, String title, List<String> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes,
                 List<String> directors, List<String> writers, List<String> mainCast, double rating) {

        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

}

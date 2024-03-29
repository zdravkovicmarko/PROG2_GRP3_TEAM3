package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.JsonArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private JsonArray genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private JsonArray directors;
    private JsonArray writers;
    private JsonArray mainCast;
    private double rating;

    public Movie(String id, String title, JsonArray genres, int releaseYear, String description, String imgUrl, int lengthInMinutes,
                 JsonArray directors, JsonArray writers, JsonArray mainCast, double rating) {

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
    public static List<Movie> initializeMovies(String filepath){
        List<Movie> movies = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String title = parts[0];
                    String description = parts[1];
                    List<String> genres = Arrays.asList(Arrays.copyOfRange(parts, 2, parts.length));
                    movies.add(new Movie(title, description, genres));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }
}

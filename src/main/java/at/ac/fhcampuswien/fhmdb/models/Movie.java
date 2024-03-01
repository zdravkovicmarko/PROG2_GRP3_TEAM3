package at.ac.fhcampuswien.fhmdb.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<String> genres;

    public Movie(String title, String description, List<String> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
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

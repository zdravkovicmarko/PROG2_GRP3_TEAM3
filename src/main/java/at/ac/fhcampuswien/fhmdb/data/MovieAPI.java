package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.MovieAPIException;
import javafx.scene.control.Label;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String USER_AGENT = "http.agent";
    private final OkHttpClient client;

    public MovieAPI() {
        this.client = new OkHttpClient();
    }

    // Method to fetch movies from API
    public List<Movie> fetchMovies(String query, String genre, int releaseYear, double ratingFrom) throws MovieAPIException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder();

        // Add query parameters to URL builder
        if (query != null && !query.isEmpty()) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (genre != null && !genre.isEmpty()) {
            urlBuilder.addQueryParameter("genre", genre);
        }
        if (releaseYear > 0) {
            urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
        }
        if (ratingFrom > 0) {
            urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
        }

        System.out.println("\n\n" + urlBuilder + "\n");

        // Build request
        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .header("User-Agent", USER_AGENT)
                .build();

        // Execute request & process response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseData = response.body().string();
            return parseMovies(responseData);
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
            throw new MovieAPIException(e); // Propagate the IOException as MovieAPIException
        }
    }

    // Method to parse JSON data & create Movie objects
    private List<Movie> parseMovies(String jsonData) {
        List<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Extract movie attributes from JSON object
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title");

            JSONArray jsonGenres = jsonObject.getJSONArray("genres");
            List<String> genres = new ArrayList<>();
            for (int j = 0; j < jsonGenres.length(); j++) {
                genres.add(jsonGenres.getString(j));
            }

            int releaseYear = jsonObject.getInt("releaseYear");
            String description = jsonObject.getString("description");
            String imgUrl = jsonObject.getString("imgUrl");
            int lengthInMinutes = jsonObject.getInt("lengthInMinutes");

            JSONArray jsonDirectors = jsonObject.getJSONArray("directors");
            List<String> directors = new ArrayList<>();
            for (int j = 0; j < jsonDirectors.length(); j++) {
                directors.add(jsonDirectors.getString(j));
            }

            JSONArray jsonWriters = jsonObject.getJSONArray("writers");
            List<String> writers = new ArrayList<>();
            for (int j = 0; j < jsonWriters.length(); j++) {
                writers.add(jsonWriters.getString(j));
            }

            JSONArray jsonMainCast = jsonObject.getJSONArray("mainCast");
            List<String> mainCast = new ArrayList<>();
            for (int j = 0; j < jsonMainCast.length(); j++) {
                mainCast.add(jsonMainCast.getString(j));
            }

            double rating = jsonObject.getDouble("rating");

            // Create Movie object & add to list
            movies.add(new Movie(id, title, genres, releaseYear, description, imgUrl, lengthInMinutes, directors, writers, mainCast, rating));
        }

        // Print out all movie objects for debugging
        StringBuilder stringBuilder = new StringBuilder();
        for (Movie movie : movies) {
            stringBuilder.append("id: ").append(movie.getId()).append("\n");
            stringBuilder.append("Title: ").append(movie.getTitle()).append("\n");
            stringBuilder.append("Description: ").append(movie.getDescription()).append("\n");
            stringBuilder.append("Genres: ").append(movie.getGenres()).append("\n");
            stringBuilder.append("Release Year: ").append(movie.getReleaseYear()).append("\n");
            stringBuilder.append("Img Url: ").append(movie.getImgUrl()).append("\n");
            stringBuilder.append("Length in Minutes: ").append(movie.getLengthInMinutes()).append("\n");
            stringBuilder.append("Directors: ").append(movie.getDirectors()).append("\n");
            stringBuilder.append("Writers: ").append(movie.getWriters()).append("\n");
            stringBuilder.append("Main Cast: ").append(movie.getMainCast()).append("\n");
            stringBuilder.append("Rating: ").append(movie.getRating()).append("\n");
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);

        return movies;
    }
}

package at.ac.fhcampuswien.fhmdb.models;

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

    public List<Movie> fetchMovies(String query, String genre, int releaseYear, int ratingFrom) throws IOException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder();

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

        Request request = new Request.Builder()
                .url(BASE_URL)
                .header("User-Agent", USER_AGENT)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseData = response.body().string();
            return parseMovies(responseData);
        }
    }

    private List<Movie> parseMovies(String jsonData) {
        List<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

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

            movies.add(new Movie(id, title, genres, releaseYear, description, imgUrl, lengthInMinutes, directors, writers, mainCast, rating));
        }
        return movies;
    }
}

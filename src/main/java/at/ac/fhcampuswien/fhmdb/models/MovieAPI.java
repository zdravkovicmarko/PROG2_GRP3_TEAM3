package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private String userAgent;

    public MovieAPI(String userAgent) {
        this.userAgent = userAgent;
    }

    public List<Movie> getAllMovies(){
        return getMoviesFromUrl(BASE_URL);
    }

    public List<Movie> getMoviesByFilters(String query, String genre, int releaseYear, double ratingFrom){
        String url = buildUrlWithParams(query, genre, releaseYear, ratingFrom);
        return getMoviesFromUrl(url);
    }

    private List<Movie> getMoviesFromUrl(String url){
        List<Movie> movies = new ArrayList<>();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", userAgent);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                while ((reader.readLine()) != null) {
                    response.append(reader.readLine());
                }
                reader.close();

                JsonArray jsonArray = new Gson().fromJson(response.toString(), JsonArray.class);
                for (int i= 0; i < jsonArray.size(); i++){
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                    String id = jsonObject.get("id").getAsString();
                    String title = jsonObject.get("title").getAsString();
                    JsonArray genres = jsonObject.get("genres").getAsJsonArray();
                    int releaseYear = jsonObject.get("releaseYear").getAsInt();
                    String description = jsonObject.get("description").getAsString();
                    String imgUrl = jsonObject.get("imgUrl").getAsString();
                    int lengthInMinutes = jsonObject.get("lengthInMinutes").getAsInt();
                    JsonArray directors = jsonObject.get("directors").getAsJsonArray();
                    JsonArray writers = jsonObject.get("writers").getAsJsonArray();
                    JsonArray mainCast = jsonObject.get("mainCast").getAsJsonArray();
                    double rating = jsonObject.get("rating").getAsInt();

                    movies.add(new Movie(id, title, genres, releaseYear, description, imgUrl, lengthInMinutes, directors, writers, mainCast, rating));
                }
            }
            else {
                System.err.println("Failed to fetch movies. Response Code: " + responseCode);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }


    private String buildUrlWithParams(String query, String genre, int releaseYear, double ratingFrom) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        boolean isFirstParam = true;

        if (query != null && !query.isEmpty()) {
            appendQueryParam(urlBuilder, "query", query, isFirstParam);
            isFirstParam = false;
        }
        if (genre != null && !genre.isEmpty()) {
            appendQueryParam(urlBuilder, "genre", genre, isFirstParam);
            isFirstParam = false;
        }
        if (releaseYear > 0) {
            appendQueryParam(urlBuilder, "releaseYear", String.valueOf(releaseYear), isFirstParam);
            isFirstParam = false;
        }
        if (ratingFrom > 0) {
            appendQueryParam(urlBuilder, "ratingFrom", String.valueOf(ratingFrom), isFirstParam);
        }
        return urlBuilder.toString();
    }

    private void appendQueryParam(StringBuilder urlBuilder, String paramName, String paramValue, boolean isFirstParam) { // deciding whether to append "?" or "&" and adding parameters to url
        if (isFirstParam) {
            urlBuilder.append("?");
        } else {
            urlBuilder.append("&");
        }
        urlBuilder.append(paramName).append("=").append(paramValue);
    }





}

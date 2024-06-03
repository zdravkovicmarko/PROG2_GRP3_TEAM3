package at.ac.fhcampuswien.fhmdb.data;

import okhttp3.HttpUrl;
import java.util.Objects;

public class MovieUrlBuilder {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private final HttpUrl.Builder urlBuilder;

    public MovieUrlBuilder() {
        this.urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder();
    }

    public MovieUrlBuilder setQuery(String query) {
        if (query != null && !query.isEmpty()) { urlBuilder.addQueryParameter("query", query); }
        return this;
    }

    public MovieUrlBuilder setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) { urlBuilder.addQueryParameter("genre", genre); }
        return this;
    }

    public MovieUrlBuilder setReleaseYear(int releaseYear) {
        if (releaseYear > 0) { urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear)); }
        return this;
    }

    public MovieUrlBuilder setRatingFrom(double ratingFrom) {
        if (ratingFrom > 0) { urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom)); }
        return this;
    }

    public String build() {
        return urlBuilder.build().toString();
    }
}
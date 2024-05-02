package at.ac.fhcampuswien.fhmdb;

import java.sql.SQLException;

public class MovieAPIException extends Exception {
    public MovieAPIException() {}
    public MovieAPIException(String message) {
        super(message);
    }
    public MovieAPIException(Throwable cause) {
        super(cause);
    }
    public MovieAPIException(String message, Throwable cause) {
        super(message, cause);
    }

}


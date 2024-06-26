package at.ac.fhcampuswien.fhmdb;

import java.sql.SQLException;

public class DatabaseException extends SQLException { // Besser extends Exception im Ausnahmefall
    public DatabaseException() {}

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;

    requires com.jfoenix;
    requires okhttp3;
    requires org.json;
    requires java.sql;

    opens at.ac.fhcampuswien.fhmdb.datalayer to ormlite.jdbc;
    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.models;
    opens at.ac.fhcampuswien.fhmdb.models to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.datalayer;
}
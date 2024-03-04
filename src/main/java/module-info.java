module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    opens at.ac.fhcampuswien.zin to javafx.fxml;
    exports at.ac.fhcampuswien.zin;
}
package at.ac.fhcampuswien.fhmdb.presentation;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Alert {
    private final StackPane alertPane;
    private final Label alertLabel;
    private final FadeTransition fadeInTransition;
    private final FadeTransition fadeOutTransition;
    private final PauseTransition pauseTransition;

    public Alert(StackPane alertPane) {
        this.alertPane = alertPane;
        this.alertLabel = new Label();
        this.fadeInTransition = new FadeTransition(Duration.millis(1000), alertPane);
        this.fadeOutTransition = new FadeTransition(Duration.millis(1000), alertPane);
        this.pauseTransition = new PauseTransition(Duration.seconds(5));
        initRedAlert();
    }

    private void initRedAlert() {
        alertLabel.getStyleClass().add("text-white");
        alertPane.getChildren().add(alertLabel);
        alertPane.setVisible(false);

        // Set up transitions
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        // Set up chronological hierarchy
        fadeInTransition.setOnFinished(event -> pauseTransition.play());
        pauseTransition.setOnFinished(event -> fadeOutTransition.play());
        fadeOutTransition.setOnFinished(event -> hideRedAlert());
    }

    public void showRedAlert(String message) {
        alertLabel.setText(message);
        alertPane.setVisible(true);
        fadeInTransition.stop();
        fadeOutTransition.stop();
        pauseTransition.stop();
        fadeInTransition.play();
    }

    private void hideRedAlert() {
        fadeOutTransition.stop();
        alertPane.setVisible(false);
    }
}
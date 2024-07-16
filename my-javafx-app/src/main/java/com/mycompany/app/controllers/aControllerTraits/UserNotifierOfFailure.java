package com.mycompany.app.controllers.aControllerTraits;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;


public class UserNotifierOfFailure {

    public static Boolean notified(ProgressIndicator loadingIndicator) {
        try {
            loadingIndicator.setProgress(0); // Start from 0 progress
            loadingIndicator.setVisible(true);
            loadingIndicator.setStyle("-fx-progress-color: red;");
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(loadingIndicator.visibleProperty(),true)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(loadingIndicator.progressProperty(), 1))
            );
            timeline.play();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

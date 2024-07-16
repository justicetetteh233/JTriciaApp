package com.mycompany.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpPage0.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/authViews/SignUpPage0.fxml"));
            Parent root = loader.load();
            
            // Create the scene
            Scene scene = new Scene(root);
            
            // Set the scene and show the stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sign Up Page");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.edugames;

import com.edugames.view.StartView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window and create a StackPane
        primaryStage.setTitle("Sänka skepp");

        // Create a new startView and provide it with the primaryStage and let it present itself
        StartView startView = new StartView(primaryStage);
        startView.present();
    }
}

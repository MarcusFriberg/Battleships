package com.edugames.view;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StartView {
    // Variables
    private Stage primaryStage;

    // Constructor
    public StartView(Stage primaryStageStage) {
        this.primaryStage = primaryStageStage;
    }

    public void present() {
        // Create two buttons to let user select game mode
        Button buttonStartServer = new Button("", new ImageView("serverstart.png"));
        buttonStartServer.setStyle("-fx-background-color:transparent");
        buttonStartServer.setScaleX(0.5);
        buttonStartServer.setScaleY(0.5);
        buttonStartServer.setOnMouseEntered(event -> {
            buttonStartServer.setScaleX(0.55);
            buttonStartServer.setScaleY(0.55);
        });
        buttonStartServer.setOnMouseExited(event -> {
            buttonStartServer.setScaleX(0.5);
            buttonStartServer.setScaleY(0.5);
        });
        buttonStartServer.setOnAction(event -> {
            System.out.println("Server mode was pressed");
        });
        Button buttonStartClient = new Button("", new ImageView("clientstart.png"));
        buttonStartClient.setStyle("-fx-background-color:transparent");
        buttonStartClient.setScaleX(0.5);
        buttonStartClient.setScaleY(0.5);
        buttonStartClient.setOnMouseEntered(event -> {
            buttonStartClient.setScaleX(0.55);
            buttonStartClient.setScaleY(0.55);
        });
        buttonStartClient.setOnMouseExited(event -> {
            buttonStartClient.setScaleX(0.5);
            buttonStartClient.setScaleY(0.5);
        });
        buttonStartClient.setOnAction(event -> {
            System.out.println("Client mode was pressed");
        });
        // Create a VBox to hold the buttons and place it under the game-logo
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(240, 10, 10, 10));
        buttonsBox.setSpacing(-50);
        buttonsBox.getChildren().addAll(buttonStartServer, buttonStartClient);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(buttonsBox);
        // Create a new image called startScreenImage from Start-screen.png
        Image startScreenImage = new Image("Start-screen.png");
        // Create a new BackgroundImage from the startScreenImage and make it none repeat, position to CENTER and make it default size
        BackgroundImage backgroundImage = new BackgroundImage(startScreenImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        // Create a Background from the backgroundImage
        Background background = new Background(backgroundImage);
        // Set the background of stackPane to this background
        stackPane.setBackground(background);
        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(stackPane, 960, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }
}

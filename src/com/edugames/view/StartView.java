package com.edugames.view;

// Imports
import com.edugames.controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
* Class StartView
* A class to create a scene for the startView of the game
* where the player can select server or client mode.
* @param: Constructor receives the primaryStage as a parameter.
* @author: Marcus Friberg
* @author: marcus.friberg@edu.edugrad.se
* @version: 1.0
 */
public class StartView {
    // Variables
    private Stage primaryStage;
    private GameController gameController;

    // Constructor
    public StartView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /*
    * Method present
    * A method to present the startView.
    * Creates the layout of the first view that appears when game is started.
    * Presents the created scene to the primaryStage that was provided with
    * the call to the constructor.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.0
    */
    public void present() {
        // Create two buttons to let user select game mode
        Button buttonStartServer = new Button("", new ImageView("serverstart.png"));
        // Style the button
        buttonStartServer.setStyle("-fx-background-color:transparent");
        buttonStartServer.setScaleX(0.5);
        buttonStartServer.setScaleY(0.5);
        // Make the button scale up/down as mouse enters/leaves the object
        buttonStartServer.setOnMouseEntered(event -> {
            buttonStartServer.setScaleX(0.55);
            buttonStartServer.setScaleY(0.55);
        });
        buttonStartServer.setOnMouseExited(event -> {
            buttonStartServer.setScaleX(0.5);
            buttonStartServer.setScaleY(0.5);
        });
        // Set the action when button is pressed
        buttonStartServer.setOnAction(event -> {
            System.out.println("Server mode was pressed");
            gameController = new GameController(primaryStage, true);
        });
        Button buttonStartClient = new Button("", new ImageView("clientstart.png"));
        // Style the button
        buttonStartClient.setStyle("-fx-background-color:transparent");
        buttonStartClient.setScaleX(0.5);
        buttonStartClient.setScaleY(0.5);
        // Make the button scale up/down as mouse enters/leaves the object
        buttonStartClient.setOnMouseEntered(event -> {
            buttonStartClient.setScaleX(0.55);
            buttonStartClient.setScaleY(0.55);
        });
        buttonStartClient.setOnMouseExited(event -> {
            buttonStartClient.setScaleX(0.5);
            buttonStartClient.setScaleY(0.5);
        });
        // Set the action when button is pressed
        buttonStartClient.setOnAction(event -> {
            System.out.println("Client mode was pressed");
            gameController = new GameController(primaryStage, false);
        });
        // Create a VBox to hold the buttons and place it under the game-logo
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(240, 10, 10, 10));
        buttonsBox.setSpacing(-50);
        buttonsBox.getChildren().addAll(buttonStartServer, buttonStartClient);
        // Create a StackPane to hold the VBox
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(buttonsBox);
        // Create a new image called startViewBackgroundImage from Start-screen.png
        Image startViewBackgroundImage = new Image("Start-screen.png");
        // Create a new BackgroundImage from the startViewBackgroundImage and make it none repeat, position to CENTER and make it default size
        BackgroundImage backgroundImage = new BackgroundImage(startViewBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
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

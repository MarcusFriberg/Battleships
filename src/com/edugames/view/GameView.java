package com.edugames.view;

// Imports
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
 * Class GameView
 * A class to create a scene for the gameView of the game
 * where the player can see the progress of the game.
 * @param: Constructor receives the primaryStage(Stage) and isServer(Boolean) as parameters.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @version: 1.0
 */
public class GameView {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;

    // Constructor
    public GameView(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
    }

    //Initiates game graphics from each panel

    /*
    * Method present
    * A method to present the gameView.
    * Creates the layout of the gameView that is shown when game has started.
    * Presents the created scene to the primaryStage that was provided with
    * the call to the constructor.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.0
    */
    public void present() {
        // --TODO--Create the content here, InfoPanel, PlayerPanel, EnemyPanel
        // Create a StackPane to hold the Content
        StackPane stackPane = new StackPane();
        // --TODO--Add the content to the StackPane here
        // Create a new image called gameViewBackgroundImage from GameView-Background.png
        Image gameViewBackgroundImage = new Image("GameView-Background.png");
        // Create a new BackgroundImage from the gameViewBackgroundImage and make it none repeat, position to CENTER and make it default size
        BackgroundImage backgroundImage = new BackgroundImage(gameViewBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
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

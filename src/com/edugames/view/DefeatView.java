package com.edugames.view;

// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/*
     * Class DefeatView
     * A class to create a scene for the DefeatView of the game
     * @param: Constructor receives the primaryStage as a parameter.
     * @author: Matilda Wintence
     * @author: matilda.wintence@edu.edugrade.se
     * @co-author: Marcus Friberg
 *   * @co-author: marcus.friberg@edu.edugrade.com
     * @version: 1.0
     */
    public class DefeatView {
        // Variables
        private final Stage primaryStage;
        private final GameView gameView;

        // Constructor
        public DefeatView(Stage primaryStage, GameView gameView) {
            this.primaryStage = primaryStage;
            this.gameView = gameView;
        }

        /*
         * Method present
         * A method to present the DefeatView.
         * Creates the layout of the last view that appears when game is lost.
         * Presents the created scene to the primaryStage that was provided with
         * the call to the constructor.
         * @author: Matilda Wintence
         * @author: matilda.wintence@edu.edugrade.se
         * @co-author: Marcus Friberg
         * @co-author: marcus.friberg@edu.edugrade.com
         * @version: 1.0
         */
        public void present() {
            // Create new button with image "buttonback.png"
            Button reverseToGameView = new Button("", new ImageView("buttonback.png"));
            // Set the background to transparent
            reverseToGameView.setStyle("-fx-background-color:transparent");
            // Make button scale up when mouse hoovering over
            reverseToGameView.setOnMouseEntered(event -> {
                reverseToGameView.setScaleX(1.1);
                reverseToGameView.setScaleY(1.1);
            });
            // Make button scale down when mouse stops hoovering over
            reverseToGameView.setOnMouseExited(event -> {
                reverseToGameView.setScaleX(1);
                reverseToGameView.setScaleY(1);
            });
            // When button is pressed gameView is yet again presented
            reverseToGameView.setOnAction(event -> gameView.present());
            // Set padding for the button
            reverseToGameView.setPadding(new Insets(0, 0, 180, 0));
            // Create a StackPane
            StackPane stackPane = new StackPane();
            // Create a new image called DefeatView from DEFEAT4.png
            Image defeatViewBackgroundImage = new Image("DEFEAT4.png");
            // Create a new BackgroundImage from the defeatViewBackgroundImage and make it none repeat, position to CENTER and make it default size
            BackgroundImage backgroundImage = new BackgroundImage(defeatViewBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            // Create a Background from the backgroundImage
            Background background = new Background(backgroundImage);
            // Set the background of stackPane to this background
            stackPane.setBackground(background);
            // Add button to stackPane
            stackPane.getChildren().add(reverseToGameView);
            // Set alignment of the stackPane
            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            // Make a new Scene containing the stackPane and set the size as the backgrounds size
            Scene defeatScene = new Scene(stackPane, 960, 768);
            // Set this scene as the scene of primaryStage
            primaryStage.setScene(defeatScene);
            // Show the stage
            primaryStage.show();
        }
    }

package com.edugames.view;
// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
 * Class VictoryView
 * A class to create a scene for the VictoryView of the game
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @co-author: Marcus Friberg
 * @co-author: marcus.friberg@edu.edugrade.com
 * @version: 1.0
 */
    public class VictoryView {
        // Variables
        private Stage primaryStage;
        private GameView gameView;

        // Constructor
        public VictoryView(Stage primaryStage, GameView gameView) {
            this.primaryStage = primaryStage;
            this.gameView = gameView;
        }

        /*
         * Method present
         * A method to present the VictoryView.
         * Creates the layout of the last view that appears when game is won.
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
            reverseToGameView.setOnAction(event -> {
                System.out.println("Return button was pressed");
                gameView.present();
            });
            // Set padding for the button
            reverseToGameView.setPadding(new Insets(0, 0, 110, 0));
            // Create a StackPane
            StackPane stackPane = new StackPane();
            // Create a new image called victoryViewImage from victoryscreen2.png
            Image victoryViewBackgroundImage = new Image("victoryscreen2.png");
            // Create a new BackgroundImage from the victoryViewBackgroundImage and make it none repeat, position to CENTER and make it default size
            BackgroundImage backgroundImage = new BackgroundImage(victoryViewBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            // Create a Background from the backgroundImage
            Background background = new Background(backgroundImage);
            // Set the background of stackPane to this background
            stackPane.setBackground(background);
            // Add button to stackPane
            stackPane.getChildren().add(reverseToGameView);
            // Set alignment of the stackPane
            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            // Make a new Scene containing the stackPane and set the size as the backgrounds size
            Scene victoryScene = new Scene(stackPane, 960, 768);
            // Set this scene as the scene of primaryStage
            primaryStage.setScene(victoryScene);
            // Show the stage
            primaryStage.show();
        }
    }

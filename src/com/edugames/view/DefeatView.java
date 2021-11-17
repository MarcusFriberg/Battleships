package com.edugames.view;

import com.edugames.controller.GameController;
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
     * @version: 1.0
     */
    public class DefeatView {
        // Variables
        private Stage primaryStage;
        private GameController gameController;
        private GameView gameView;

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
            Button reverseToGameView = new Button("", new ImageView("buttonback.png"));
            reverseToGameView.setStyle("-fx-background-color:transparent");
            reverseToGameView.setOnMouseEntered(event -> {
                reverseToGameView.setScaleX(1.2);
                reverseToGameView.setScaleY(1.2);
            });
            reverseToGameView.setOnMouseExited(event -> {
                reverseToGameView.setScaleX(1);
                reverseToGameView.setScaleY(1);
            });
            reverseToGameView.setOnAction(event -> {
                reverseToGameView.setScaleX(1.2);
                reverseToGameView.setScaleY(1.2);
                System.out.println("Return button was pressed");
                gameView.present();
            });
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
            stackPane.getChildren().add(reverseToGameView);
            // Make a new Scene containing the stackPane and set the size as the backgrounds size
            Scene scene = new Scene(stackPane, 960, 768);
            // Set this scene as the scene of primaryStage
            primaryStage.setScene(scene);
            // Show the stage
            primaryStage.show();
        }
    }

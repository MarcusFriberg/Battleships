package com.edugames.view;
// Imports
import com.edugames.controller.GameController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
 * Class VictoryView
 * A class to create a scene for the VictoryView of the game
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0
 */
    public class VictoryView {
        // Variables
        private Stage primaryStage;
        private GameController gameController;

        // Constructor
        public VictoryView(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        /*
         * Method present
         * A method to present the VictoryView.
         * Creates the layout of the last view that appears when game is won.
         * Presents the created scene to the primaryStage that was provided with
         * the call to the constructor.
         * @author: Matilda Wintence
         * @author: matilda.wintence@edu.edugrade.se
         * @version: 1.0
         */
        public void present() {
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
            // Make a new Scene containing the stackPane and set the size as the backgrounds size
            Scene scene = new Scene(stackPane, 960, 768);
            // Set this scene as the scene of primaryStage
            primaryStage.setScene(scene);
            // Show the stage
            primaryStage.show();
        }
    }

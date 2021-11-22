package com.edugames.view;

// Imports
import com.edugames.controller.GameController;
import com.edugames.model.Coordinate;
import com.edugames.model.GamePanel;
import com.edugames.model.InfoPanel;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private final Stage primaryStage;
    private final GamePanel playerPanel;
    private final GamePanel enemyPanel;
    private final InfoPanel infoPanel;
    private final GameController gameController;
    private GridPane playerPanelContent;
    private GridPane enemyPanelContent;

    // Constructor
    public GameView(Stage primaryStage, Boolean isServer, GameController gameController) {
        this.primaryStage = primaryStage;
        this.gameController = gameController;
        infoPanel = new InfoPanel(this);
        playerPanel = new GamePanel();
        enemyPanel = new GamePanel();
        infoPanel.initInfoPanel(isServer);
    }

    /*
    * Method initPlayerPanel
    * A method to be called by the GameController once to give GameController access to the
    * coordinates 2D-array of the playerPanel.
    * @returns: coordinates[][] - A 2D array containing all Coordinate objects of the playerPanel.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.0
    */
    public Coordinate[][] initPlayerPanel() {
        return playerPanel.initGamePanel();
    }

    /*
     * Method initEnemyPanel
     * A method to be called by the GameController once to give GameController access to the
     * coordinates 2D-array of the enemyPanel.
     * @returns: coordinates[][] - A 2D array containing all Coordinate objects of the enemyPanel.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Coordinate[][] initEnemyPanel() {
        return enemyPanel.initGamePanel();
    }

    // Getters


    public GameController getGameController() {
        return gameController;
    }

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
        // Create a VBox named contentWrapper to hold all the Content
        VBox contentWrapper = new VBox();
        // Create a HBox to hold future content in the top area set the size to push the other panels down
        HBox topPanelContent = new HBox();
        topPanelContent.setMinSize(960,210);
        // Create a HBox to hold the content of the infoPanel and set its size
        // --TODO-- Add a call to the infopanel method that returns a HBox when class is done
        GridPane infoPanelContent = infoPanel.drawInfoPanel();
        infoPanelContent.setMinSize(960, 113);
        infoPanelContent.setAlignment(Pos.CENTER);
        // Create a new HBox to hold both the panels
        HBox gamePanelsContent = new HBox();
        // Create a new GridPane to hold the enemyPanel
        enemyPanelContent = new GridPane();
        // Fetch the content of the enemyPanel by calling drawGamePanel()
        try {
            enemyPanelContent = enemyPanel.drawGamePanel();
            //enemyPanelContent = enemyPanel.drawEnemyPanel();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Set the padding of the enemyPanel to draw the panel in the correct position
        enemyPanelContent.setPadding(new Insets(0,0,0,50));
        // Create a new GridPane to hold the PlayerPanel
        playerPanelContent = new GridPane();
        // Fetch the content of the playerPanel by calling drawGamePanel()
        try {
            playerPanelContent = playerPanel.drawGamePanel();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Set the padding of the playerPanel to draw the panel in the correct position
        playerPanelContent.setPadding(new Insets(0,0,0,62));
        // Add the EnemyPanel and PlayerPanel to the playerPanelContent (HBOX)
        gamePanelsContent.getChildren().addAll(enemyPanelContent, playerPanelContent);
        // Add the topPanelContent (HBox), infoPanelContent (HBox) and gamePanelsContent (HBox) to the contentWrapper (VBox)
        contentWrapper.getChildren().addAll(topPanelContent, infoPanelContent, gamePanelsContent);
        // Create a new image called gameViewBackgroundImage from GameView-Background.png
        Image gameViewBackgroundImage = new Image("GameView-Background.png");
        // Create a new BackgroundImage from the gameViewBackgroundImage and make it none repeat, position to CENTER and make it default size
        BackgroundImage backgroundImage = new BackgroundImage(gameViewBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        // Create a Background from the backgroundImage
        Background background = new Background(backgroundImage);
        // Set the background of contentWrapper to this background
        contentWrapper.setBackground(background);
        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(contentWrapper, 960, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }

    public GamePanel getPlayerPanel() {
        return playerPanel;
    }

    public GamePanel getEnemyPanel() {
        return enemyPanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
}

package com.edugames.controller;
// Imports
import com.edugames.model.Coordinate;
import com.edugames.model.Ship;
import com.edugames.view.GameView;
import javafx.stage.Stage;

public class GameController {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;
    private GameView gameView;
    private Coordinate[][] playerPanelCoordinates;
    private Coordinate[][] enemyPanelCoordinates;

    // Constructor
    public GameController(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
        initGameSession();
        initGameView();

    }


    //Create methods to communicate with GameSession to shoot or wait
    //Create methods to update models on action
    //Create methods to update graphics on action



    // Initial Setup of a new game that happens when player selects to start in either server or client mode

    public void initGameSession() {
        // Code to init a new GameSession
    }

    /*
     * Method createShip
     * Method to create a Ship object when the AIPlayers placeShip-method is called.
     * Will pass the playerPanelCoordinates to the constructor of Ship to give access
     * to all Coordinate-objects in the playerPanel. Will also pass the shipSize, shipAlignment,
     * shipStartXPos and shipStartYPos that was provided in the call from AIPlayers placeShip-method.
     * @returns: The Ship-object that was created.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public Ship createShip(int shipSize, char shipAlignment, int shipStartXPos, int shipStartYPos) {
        Ship ship = new Ship(shipSize, shipAlignment, shipStartXPos, shipStartYPos, playerPanelCoordinates);
        return ship;
    }

    /*
    * Method initGameView
    * Method to initiate a new GameView and store it in gameView for access
    * Will initiate the playerPanel and enemyPanel and store the returned Coordinate 2D arrays for
    * each panel in playerPanelCoordinates and enemyPanelCoordinates for quick access to all
    * Coordinate-objects of each GamePanel.
    * Then calls the present()-method of the gameView to draw the content.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.1
    */
    public void initGameView() {
        gameView = new GameView(primaryStage, isServer);
        playerPanelCoordinates = gameView.initPlayerPanel();
        enemyPanelCoordinates = gameView.initEnemyPanel();
        gameView.present();
    }
}

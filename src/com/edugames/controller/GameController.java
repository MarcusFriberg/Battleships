package com.edugames.controller;
// Imports
import com.edugames.model.*;
import com.edugames.view.GameView;
import javafx.stage.Stage;
import java.util.*;

public class GameController {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;
    private GameSession gameSession;
    private GameView gameView;
    private Coordinate[][] playerPanelCoordinates;
    private Coordinate[][] enemyPanelCoordinates;
    private AIPlayer player;

    // Constructor
    public GameController(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
        initGameSession();
        initGameView();
    }



    //Create methods to update models on action
    //Create methods to update graphics on action



    // Initial Setup of a new game that happens when player selects to start in either server or client mode

    public void initGameSession() {
        // Code to init a new GameSession
        gameSession = new GameSession(isServer, this);
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
        // Making a few testShip's --TODO-- Remove these test-ships before release
        createShip(3,'h', 0,0);
        createShip(5,'v',5,5);
        createShip(3,'v',0,4);
        gameView.present();
    }

    /*
     * Method handleIncomingShot
     * Method to check if an incoming shot hits one of the friendly players ships.
     * Returns a string with "h" for hit, "m" for miss, "s" for sunken or
     * "game over" if the friendly player is game over.
     * @param: String xy - a location of a coordinate.
     * @returns: String result - h, m, s or game over
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public String handleIncomingShot(String xy) {
        String result = "";
        // Get the Coordinate-object that was hit by enemy
        Coordinate coordinate = getCoordinateObjectFromString(xy);
        // Set the isHit property of this Coordinate-object to true and update its image
        coordinate.setIsHit(true);
        coordinate.changeImage();
        // If there is a ship on this coordinate
        if(coordinate.getHasShip()) {
            Ship ship = coordinate.getShipOnThisCoordinate();
            // Check if the ship is sunken
            if(ship.checkIfShipIsSunken()) {
                // Check if the player is game over --TODO -- Remove block comment below when player is complete
                /*if(player.checkGameOver()) {
                    // Player is game over
                    result = "game over";
                } else {
                    // Player is not game over but ship was sunken
                    result = "s";
                }*/
            // If ship was not sunken
            } else {
                // Ship was hit
                result = "h";
            }
        // There was no ship on the coordinate
        } else {
            // Enemy player missed their shot
            result = "m";
        }
        // Return the result as a string
        return result;
    }

    /*
     * Method getCoordinateObjectFromString
     * Method to convert the incoming string xy-value to integers for x and y position
     * and return the coordinate-object on that position in the playerPanelCoordinates 2D-array.
     * @param: String xy - a location of a coordinate.
     * @returns: Coordinate coordinate - a coordinate object found on that position.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public Coordinate getCoordinateObjectFromString(String xy) {
        // Make an array of chars from String xy
        char [] positionArray = xy.toCharArray();
        // Make a List of characters with the possible values of positionArray[1] (they y-value)
        List <Character> yValues = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        // Set y to the index where the y-value was found
        int y = yValues.indexOf(positionArray[1]);
        // Convert the x value from char -> String - int
        int x = Integer.parseInt(Character.toString(positionArray[0]));
        // Get the Coordinate-object from the x and y position in playerPanelCoordinates[][] and put it in coordinate
        Coordinate coordinate = playerPanelCoordinates[x][y];
        // Return the Coordinate-object that we were looking for
        return coordinate;
    }

    public void handleLastOutgoingShotResult(String result, Coordinate coordinate) {
        switch (result) {
            case "i" :
                break;
            case "h" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(true);
                coordinate.changeImage();
                gameView.present();
                break;
            case "m" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(false);
                coordinate.changeImage();
                gameView.present();
                break;
            case "s" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(true);
                coordinate.changeImage();
                gameSession.increaseEnemyShipsDestroyed();
                player.enemyShipWasDestroyed();
                gameView.present();
                break;
            case "game over" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(true);
                coordinate.changeImage();
                gameView.present();
                handleGameResult(true);
        }
    }

    public Coordinate requestNewShot() {
        Target target = new Target(3, 3);
        //target = player.fire();
        Coordinate coordinate = enemyPanelCoordinates[target.getXCoordinate()][target.getYCoordinate()];
        return coordinate;
    }

    public void handleGameResult(Boolean victory) {
        // code to handle game result, true for victory, false for loss
    }
}

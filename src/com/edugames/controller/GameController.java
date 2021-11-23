package com.edugames.controller;
// Imports
import com.edugames.model.*;
import com.edugames.view.DefeatView;
import com.edugames.view.GameView;
import com.edugames.view.VictoryView;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.*;

/*
 * Class GameController
 * A class that talks to both model's and view's. Also works as a translator between different models, example
 * the target-object used by the AIPlayer class is fetched by the GameController when GameSession asks for a new
 * coordinate-object to shoot on. The GameController uses that target-object to find out which coordinate-object to
 * send back to the GameSession.
 * @param: Stage primaryStage - the primaryStage of JavaFX
 * @param: Boolean isServer - true if server, false if client
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @version: 1.0
 */
public class GameController {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;
    private GameSession gameSession;
    private GameView gameView;
    private int gameDelay = 2000;
    private Coordinate[][] playerPanelCoordinates;
    private Coordinate[][] enemyPanelCoordinates;
    private AIPlayer player;
    private VictoryView victoryView;
    private DefeatView defeatView;

    // Constructor
    public GameController(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
        initViews();
        initPlayer();
        gameView.present();
    }

    /*
     * Method initPlayer
     * Method creates a new object from the class AIPlayer and store the reference to the object in player
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void initPlayer() {
        player = new AIPlayer(playerPanelCoordinates, this);
    }

    /*
     * Method startConnection
     * Method creates a new object from the class GameSession and store the reference to the object in gameSession
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void startConnection() {
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
    * Coordinate-objects of each GamePanel. Will also initiate objects from classes VictoryView and DefeatView.
    * Then calls the present()-method of the gameView to draw the content.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.1
    */
    public void initViews() {
        gameView = new GameView(primaryStage, isServer, this);
        playerPanelCoordinates = gameView.initPlayerPanel();
        enemyPanelCoordinates = gameView.initEnemyPanel();
        victoryView = new VictoryView(primaryStage, gameView);
        defeatView = new DefeatView(primaryStage, gameView);
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
        updatePlayerPanelImage(xy);
        // If there is a ship on this coordinate
        if(coordinate.getHasShip()) {
            Ship ship = coordinate.getShipOnThisCoordinate();
            // Check if the ship is sunken
            if(ship.checkIfShipIsSunken()) {
                // Check if the player is game over
                if(player.checkGameOver()) {
                    // Player is game over
                    result = "game over";
                    gameView.getInfoPanel().updateShipCounts();
                } else {
                    // Player is not game over but ship was sunken
                    result = "s";
                    gameView.getInfoPanel().updateShipCounts();
                }
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

    /*
     * Method updatePlayerPanelImage
     * Method to update the image in playerPanel that is represented by the incoming string xy-value.
     * @param: String xy - a location of a coordinate.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void updatePlayerPanelImage(String xy) {
        // Make an array of chars from String xy
        char [] positionArray = xy.toCharArray();
        // Make a List of characters with the possible values of positionArray[1] (they y-value)
        List <Character> yValues = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        // Set y to the index where the y-value was found
        int y = yValues.indexOf(positionArray[1]);
        // Convert the x value from char -> String - int
        int x = Integer.parseInt(Character.toString(positionArray[0]));
        // Tell the playerPanel to update the imageView
        gameView.getPlayerPanel().updateImageView(x, y);
    }

    /*
     * Method handleLastOutgoingShotResult
     * Method to handle the result of our outgoing shots. Will update status of the coordinate-object
     * of the enemyPlayerPanel that represent the last of our targets. Will also notify methods in AIPlayer
     * that will help the logic to prepare next shot depending on our last shot was a hit, miss or sank a ship.
     * @param: String result - the result of our last shot as a string, we switch the result.
     * @param: Coordinate coordinate, the last coordinate we shot on.
     * @returns: true when the method is completed.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public boolean handleLastOutgoingShotResult(String result, Coordinate coordinate) {
        switch (result) {
            case "i" :
                break;
            case "h" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(true);
                coordinate.changeImage();
                if(!player.setLastTargetWasAHit(true)) {
                    System.out.println("could not run method setLastTargetWasAHit");
                }
                updateEnemyPanelImage(coordinate);
                break;
            case "m" :
                coordinate.setIsHit(true);
                coordinate.setHasShip(false);
                coordinate.changeImage();
                if(!player.setLastTargetWasAHit(false)) {
                    System.out.println("could not run method setLastTargetWasAHit");
                }
                updateEnemyPanelImage(coordinate);
                break;
            default:
                coordinate.setIsHit(true);
                coordinate.setHasShip(true);
                coordinate.changeImage();
                updateEnemyPanelImage(coordinate);
                if(!player.enemyShipWasDestroyed()) {
                    System.out.println("could not run method enemyShipWasDestroyed");
                }
                break;
        }
        return true;
    }

    /*
     * Method updateEnemyPanelImage
     * Method to update the image in enemyPanel that is represented by the incoming string xy-value.
     * @param: String xy - a location of a coordinate.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void updateEnemyPanelImage(Coordinate coordinate) {
        // Make a List of characters with the possible values of positionArray[1] (they y-value)
        List <Character> yValues = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        // Set y to the index where the y-value was found
        int y = yValues.indexOf(coordinate.getY());
        // Convert the x value from char -> String - int
        int x = Integer.parseInt(Character.toString(coordinate.getX()));
        // Tell the playerPanel to update the imageView
        gameView.getEnemyPanel().updateImageView(x, y);
    }

    /*
     * Method requestNewShot
     * Method to ask the AIPlayer-class for a new target. The targets x and y coordinates is used to
     * find the wanted coordinate object in the enemyPanel.
     * @returns: Coordinate coordinate - a new coordinate object that we are shooting on.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public Coordinate requestNewShot() {
        Target target;
        target = player.fireAtTarget();
        Coordinate coordinate = enemyPanelCoordinates[target.getXCoordinate()][target.getYCoordinate()];
        return coordinate;
    }

    /*
     * Method handleGameResult
     * Method present VictoryView or DefeatView objets when we have won or lost.
     * The present-methods of the views must be run on JavaFX Applications thread but the call comes from
     * the server or client thread, hence the use of Platform.runLater().
     * @param: Boolean victory - true if victory, false if defeat
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void handleGameResult(Boolean victory) {
        if(victory) {
            Platform.runLater(() -> {
                victoryView.present();
            });
        } else {
            Platform.runLater(() -> {
                defeatView.present();
            });
        }
    }

    /*
     * Method gameDelayWasChanged
     * Method called by the infoPanel when the user changes the gameDelay (game speed)
     * @param: int newGameDelay - An int between 0 and 10.000 milliseconds
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public void gameDelayWasChanged(int newGameDelay) {
        gameDelay = newGameDelay;
    }

    /*
     * Method getNumberOfShipsOfSize
     * Method called by the infoPanel to get information about how many ships of a given size we have left.
     * Method will ask the player-object and pass the answer back to infoPanel.
     * @param: int size - An int between 2 and 5 depending on what ship infoPanel needs information about
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public int getNumberOfShipsOfSize(int size) {
        int number = player.getNumberOfShipsOfShipSize(size);
        return number;
    }

    /*
     * Method getGameDelay
     * A getter method that the gameSession object can call to update the gameDelay passed on to the server
     * or client thread.
     * @returns int gameDelay - number of milliseconds between 0 and 10.000
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.1
     */
    public int getGameDelay() {
        return gameDelay;
    }
}
package com.edugames.model;
// Imports
import com.edugames.controller.GameController;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Class GameSession
 * A class that communicates via sockets with another computer, decodes/encodes the in/out parameters and communicate
 * with GameController.
 * @param: Constructor receives the isServer(Boolean) and gameController(GameController) as parameter.
 * @author: Matilda Wintence
 * @author: matilda.wintence.edu.edugrade.com
 * @co-author: Martin Andersson
 * @co-author: martin.andersson.edu.edugrade.com
 * @version: 1.0
 */
public class GameSession {
    // Variables
    private Boolean isServer;
    private Boolean gameIsRunning = true;
    private GameController gameController;
    private Coordinate lastOutgoingShot;
    private String outgoingText = "";
    private int gameDelay = 2000;
    private int enemyShipsDestroyed;

    // Constructor
    public GameSession(Boolean isServer, GameController gameController) {
        this.isServer = isServer;
        this.gameController = gameController;
        this.enemyShipsDestroyed = 0;
        if(isServer) {
            hostServer();
        } else {
            hostClient();
        }
    }

    /*
     * Method hostServer
     * A method connect with another computer as server, on the same network.
     * Creates a loop in which in och out parameters can pass trough.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void hostServer() {
        ServerThread networkConnection = new ServerThread(this);
    }

    /*
     * Method hostClient
     * A method connect with another computer as client, on the same network.
     * Creates a loop in which in och out parameters can pass trough.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void hostClient() {
        ClientThread networkConnection = new ClientThread(this);
    }

    /*
     * Method socketHelper
     * A method that acts as a bridge between the Client/Server and decodeIncomingData/encodeOutgoingData.
     * Sends the data between different methods.
     * @author: Martin Andersson
     * @author: martin.andersson@edu.edugrade.se
     * @version: 1.0
     */
    public String socketHelper(String incomingText) {
        if(incomingText.equals("game over")) {
            // Code to mark lastOutgoingShot as a hit
            // Code to present Victory Screen/Message
        } else {
            String incomingShotCoordinates = decodeIncomingData(incomingText);
            String incomingShotResult = gameController.handleIncomingShot(incomingShotCoordinates);
            lastOutgoingShot = gameController.requestNewShot();
            outgoingText = encodeOutgoingData(incomingShotResult, lastOutgoingShot);
        }
        return outgoingText;
    }

    /*

     * Method decodeIncomingData
     * A method that has a String as an inparameter.
     * Decodes the incoming data and sends it to the bridge.
     * @author: Matilda Wintence
     * @author: matilda.wintence@edu.edugrade.se
     * @version: 1.0
     */
    public String decodeIncomingData(String incomingText) {
        String[] incomingDataSplit = incomingText.split(" ");
        gameController.handleLastOutgoingShotResult(incomingDataSplit[0], lastOutgoingShot);
        return incomingDataSplit[2];
    }

    /*
     * Method encodeOutgoingData
     * A method that has a String and a Coordinate object as an inparameter.
     * Encodes the outgoing data and sends it to the bridge.
     * @author: Matilda Wintence
     * @author: matilda.wintence@edu.edugrade.se
     * @version: 1.0
     */
    public String encodeOutgoingData(String lastIncomingShotResult, Coordinate outgoingShot) {
        return lastIncomingShotResult + " shot " + outgoingShot.getX() + outgoingShot.getY();
    }

    public void increaseEnemyShipsDestroyed() {
        enemyShipsDestroyed ++;
    }

    public int getGameDelay() {
        return gameDelay;
    }

    public void setGameDelay(int gameDelay) {
        this.gameDelay = gameDelay;
    }

    public Boolean getGameIsRunning() {
        return gameIsRunning;
    }

    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setLastOutgoingShot(Coordinate coordinate) {
        this.lastOutgoingShot = coordinate;
    }

    public Coordinate getLastOutgoingShot() {
        return lastOutgoingShot;
    }
}




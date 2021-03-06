package com.edugames.model;
// Imports
import com.edugames.controller.GameController;

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
    private boolean gameIsRunning = true;
    private final GameController gameController;
    private Coordinate lastOutgoingShot;
    private String outgoingText = "";

    // Constructor
    public GameSession(Boolean isServer, GameController gameController) {
        // Variables
        this.gameController = gameController;
        if(isServer) {
            hostServer();
        } else {
            hostClient();
        }
    }

    /*
     * Method hostServer
     * Creates a new ServerThread-object and stores the reference to the object in networkConnection.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void hostServer() {
        new ServerThread(this);
    }

    /*
     * Method hostClient
     * Creates a new ClientThread-object and stores the reference to the object in networkConnection.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void hostClient() {
        new ClientThread(this);
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
            gameController.handleLastOutgoingShotResult("s", lastOutgoingShot);
            this.setGameIsRunning(false);
            gameController.handleGameResult(true);
        } else {
            String incomingShotCoordinates = decodeIncomingData(incomingText);
            String incomingShotResult = gameController.handleIncomingShot(incomingShotCoordinates);
            if(incomingShotResult.equals("game over")) {
                outgoingText = "game over";
                gameController.handleGameResult(false);
            } else {
                lastOutgoingShot = gameController.requestNewShot();
                outgoingText = encodeOutgoingData(incomingShotResult, lastOutgoingShot);
            }
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
        while(!gameController.handleLastOutgoingShotResult(incomingDataSplit[0], lastOutgoingShot)) {
            // Don't move forward until method call returns true.
        }
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

    // Setters and Getters
    public int getGameDelay() {
        return gameController.getGameDelay();
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




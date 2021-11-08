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
    // Variables
    private Boolean isServer;
    private Coordinate lastEnemyCoordinateShot;
    private GameController gameController;
    // Constructor
    public GameSession(Boolean isServer, GameController gameController) {
        this.isServer = isServer;
        this.gameController = gameController;
        if(isServer) {
            //server
        } else {
            //client
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
    // TODO: Two methods for client and server - ropa på metoder - tex en som bakar ihop strängen som ska skickas
    /*
     * Method hostClient
     * A method connect with another computer as client, on the same network.
     * Creates a loop in which in och out parameters can pass trough.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */


    /*
     * Method decodeIncomingData
     * A method that has a String as an inparameter.
     * Decodes the incoming data and sends it to the bridge.
     * @author: Matilda Wintence
     * @author: matilda.wintenceg@edu.edugrade.se
     * @version: 1.0
     */
    public void decodeIncomingData(String incomingData) {
        String[] incomingDataSplit = incomingData.split(" ");
        //send incomingDataSplit[0] and incomingDataSplit[2]
    }


        /*
     * Method encodeOutgoingData
     * A method that has a String and a Coordinate object as an inparameter.
     * Encodes the outgoing data and sends it to the bridge.
     * @author: Matilda Wintence
     * @author: matilda.wintenceg@edu.edugrade.se
     * @version: 1.0
     */

    public void encodeOutGoingData(String outgoingData, Coordinate lastEnemyCoordinateShot) {
        String lastEnemyCoordinateString = lastEnemyCoordinateShot.toString();
        String shot = " shot ";
        String encodedOutgoingData = outgoingData.concat(lastEnemyCoordinateString);
        String encodedOutgoingData1 = outgoingData+shot+lastEnemyCoordinateString;
        //return encodedOutgoingData1;
        //TODO: Need to get shot and spaces into the String, is concat the best option?
    }


    /*
     * Method bridgeIncomingAndOutgoingData
     * A method that acts as a bridge between the hostClient/hostServer and decodeIncomingData/encodeOutgoingData.
     * Sends the data between different methods.
     * @author: Matilda Wintence
     * @author: matilda.wintenceg@edu.edugrade.se
     * @version: 1.0
     */

    public void bridgeIncomingAndOutgoingData() {

    }

        // sista koordinaten vi sköt på hos motståndaren - lagra i en variabel som heter lastEnemyCoordinateShot

        //handleIncomingFire (en ev framtida metod i gameController)

        //bridge lägg till en sleep/wait (1000)
    }




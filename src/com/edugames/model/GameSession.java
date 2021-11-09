package com.edugames.model;
// Imports
import com.edugames.controller.GameController;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
    String outgoingText = "";
    String incomingText = "";
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
     * Method hostClient
     * A method connect with another computer as client, on the same network.
     * Creates a loop in which in och out parameters can pass trough.
     * @author: Martin Andersson
     * @author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void hostClient() {
        try {
            Socket socket = new Socket("localhost", 8888);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));


            while(!outgoingText.equals("game over") || !incomingText.equals("game over")) {
                Scanner scanner = new Scanner(System.in);
                //        outgoingText = scanner.nextLine();

                writer.println(outgoingText);

                incomingText = reader.readLine();
                System.out.println(incomingText);
            }
            socket.close();
        }catch (Exception e ) {
            System.out.println(e);
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
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            System.out.println("Waiting for shot");
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            String incommingMessage ="";
            String outMessage = "";

            while (true) {

                incommingMessage = reader.readLine();
                System.out.println("Klienten säger: " + incommingMessage);
                Scanner scanner = new Scanner(System.in);
                outMessage = Scanner.nextLine();
                writer.println(outMessage);
                System.out.println("Väntar på svar");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /*
     * Method socketHelper
     * A method that acts as a bridge between the hostClient/hostServer and decodeIncomingData/encodeOutgoingData.
     * Sends the data between different methods.
     * @author: Martin Andersson
     * @author: martin.andersson@edu.edugrade.se
     * @version: 1.0
     */
    public String socketHelper(String incomingText) {
        decodeIncomingData(incomingText);
        return outgoingText;
    }

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

        // sista koordinaten vi sköt på hos motståndaren - lagra i en variabel som heter lastEnemyCoordinateShot

        //handleIncomingFire (en ev framtida metod i gameController)

        //bridge lägg till en sleep/wait (1000)
    }




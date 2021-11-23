package com.edugames.model;
// Imports
import java.io.*;
import java.net.Socket;

/*
 * Class ClientThread
 * A class that communicates via sockets with another computer as a client, communicates with GameSession.
 * @param: Constructor receives the gameSession (GameSession) as parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrade.com
 * @co-author: Matilda Wintence
 * @co-author: matilda.wintence.edu.edugrade.com
 * @co-author: Martin Andersson
 * @co-author: martin.andersson.edu.edugrade.com
 * @version: 1.0
 */
public class ClientThread extends Thread {
    // Variables
    private final GameSession gameSession;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Boolean firstShot;

    /*
     * Constructor
     * Constructor store's the reference to the gameSession object and calls the start-method inherited from
     * the Thread class, the call to start-method will call the run-method.
     * @param: Constructor receives a reference to the gameSession (GameSession) as parameter.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.com
     * @co-author: Matilda Wintence
     * @co-author: matilda.wintence.edu.edugrade.com
     * @co-author: Martin Andersson
     * @co-author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public ClientThread(GameSession gameSession) {
        this.gameSession = gameSession;
        this.firstShot = true;
        this.start();
    }

    /*
     * Method run()
     * Method inherited from Thread, that starts the execution of the thread. Thread will run
     * until getGameIsRunning equals false (when any player is game over).
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.com
     * @co-author: Matilda Wintence
     * @co-author: matilda.wintence.edu.edugrade.com
     * @co-author: Martin Andersson
     * @co-author: martin.andersson.edu.edugrade.com
     * @version: 1.0
     */
    public void run() {
        // Setting up the connection
        try {
            socket = new Socket("localhost", 8888);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Run method in ClientThread could not initialize socket, reader or writer with the message: " + e);
        }
        setPriority(Thread.MAX_PRIORITY);
        // While there is still info to send or receive
        while(gameSession.getGameIsRunning())
        {
            // If its our first shot
            if (firstShot) {
                // Get a new coordinate to shoot at and store it in lastOutGoingShot in gameSession
                gameSession.setLastOutgoingShot(gameSession.getGameController().requestNewShot());
                // Make a textstring to send from "i shot" and the coordinates of that object
                String firstText = "i shot " + gameSession.getLastOutgoingShot().getX() + gameSession.getLastOutgoingShot().getY();
                // Send the textstring
                writer.println(firstText);
                // Change firstShot so that it skips this part next time
                firstShot = false;
            } else {
                try {
                    // If there is something to read
                    if(reader.ready()) {
                        // outputText is the result of the call to the socketHelper with that something to read passed in as a parameter
                        String outputText = gameSession.socketHelper(reader.readLine());
                        // Try to sleep for the amount of milliseconds returned from the call to gameSession.getGameDelay
                        try {
                            sleep(gameSession.getGameDelay());
                        } catch (InterruptedException e) {
                            System.out.println("Error when trying to execute gameDelay with error message: " + e);
                        }
                        // If we are game over, change gameIsRunning to false so that we don't make another run in this loop
                        if(outputText.equals("game over")) {
                            gameSession.setGameIsRunning(false);
                        }
                        // send outputText
                        writer.println(outputText);
                    }
                } catch (IOException e){
                    System.out.println("ClientThread failed to receive or send data with error message: " + e );
                }
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close socket with error message: " + e);
        }
    }
}
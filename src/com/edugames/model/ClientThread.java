package com.edugames.model;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
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
    private GameSession gameSession;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private BufferedReader reader;
    private PrintWriter writer;
    private Boolean firstShot;
    private String outputText;

    //Constructor
    public ClientThread(GameSession gameSession) {
        this.gameSession = gameSession;
        this.firstShot = true;
        this.start();
    }


    public void run() {
        try {
            socket = new Socket("localhost", 8888);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Run method in ClientThread could not initialize socket, reader or writer with the message: " + e);
        }
        setPriority(Thread.MAX_PRIORITY);
        while(gameSession.getGameIsRunning())
        {
            if (firstShot) {
                gameSession.setLastOutgoingShot(gameSession.getGameController().requestNewShot());
                String firstText = "i shot " + gameSession.getLastOutgoingShot().getX() + gameSession.getLastOutgoingShot().getY();
                System.out.println("Client skickar: " + firstText); // --TODO-- Remove when working
                writer.println(firstText);
                firstShot = false;
            } else {
                try {
                    if(reader.ready()) {
                        outputText = gameSession.socketHelper(reader.readLine());
                        try {
                            sleep(gameSession.getGameDelay());
                        } catch (InterruptedException e) {
                            System.out.println("Error when trying to execute gameDelay with error message: " + e);
                        }
                        if(outputText.equals("game over")) {
                            gameSession.setGameIsRunning(false);
                        }
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

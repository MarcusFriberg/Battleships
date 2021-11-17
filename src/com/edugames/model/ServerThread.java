package com.edugames.model;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Class ServerThread
 * A class that communicates via sockets with another computer as a server, communicates with GameSession.
 * @param: Constructor receives the gameSession (GameSession) as parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrade.com
 * @co-author: Matilda Wintence
 * @co-author: matilda.wintence.edu.edugrade.com
 * @co-author: Martin Andersson
 * @co-author: martin.andersson.edu.edugrade.com
 * @version: 1.0
 */
public class ServerThread extends Thread {
    // Variables
    private GameSession gameSession;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private BufferedReader reader;
    private PrintWriter writer;
    private String outputText;

    //Constructor
    public ServerThread(GameSession gameSession) {
        this.gameSession = gameSession;
        this.start();
    }

    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            socket = serverSocket.accept();
            System.out.println("Client connected");
            System.out.println("Waiting for shot");
            input = socket.getInputStream();
            output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Run method in ServerThread could not initialize socket, reader or writer with the message: " + e);
        }

        setPriority(Thread.MAX_PRIORITY);
        while(gameSession.getGameIsRunning()) {
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
                System.out.println("ServerThread failed to receive or send data with error message: " + e );
            }
        }
    }
}

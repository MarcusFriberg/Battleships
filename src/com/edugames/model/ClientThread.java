package com.edugames.model;

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

    //Constructor
    public ClientThread(GameSession gameSession) {
        this.gameSession = gameSession;
        this.firstShot = true;
        this.start();
    }


    public void run()
    {
        try {
            socket = new Socket("localhost", 8888);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Run method in ClientThread could not initialize socket, reader or writer with the message: " + e);
        }

        System.out.println("ClientThread passed checkpoint 1");
        setPriority(Thread.MAX_PRIORITY);
        while(gameSession.getGameIsRunning())
        {
            System.out.println("ClientThread passed checkpoint 2");
            try {
                sleep(gameSession.getGameDelay());
            }
            catch (Exception e) {
                System.out.println("Error in ClientThread; " + e);
            }
            if (firstShot) {
                gameSession.setLastOutgoingShot(gameSession.getGameController().requestNewShot());
                String firstText = "i shot " + gameSession.getLastOutgoingShot().getX() + gameSession.getLastOutgoingShot().getY();
                firstShot = false;
                System.out.println("Client skickar: " + firstText); // --TODO-- Remove when working
                writer.println(firstText);
            }
            try {
                if(reader.ready()) {
                    writer.println(gameSession.socketHelper(reader.readLine()));
                }
            } catch (IOException e) {
                System.out.println("ClientThread could not call gameSession.socketHelper with message: " + e );
            }
            System.out.println("ClientThread passed checkpoint 4");
        }
    }
}

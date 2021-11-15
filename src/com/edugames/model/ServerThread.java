package com.edugames.model;

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

    //Constructor
    public ServerThread(GameSession gameSession) {
        this.gameSession = gameSession;
        this.start();
    }


    public void run()
    {
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            socket = serverSocket.accept();
            System.out.println("Client connected");
            System.out.println("Waiting for shot");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        PrintWriter writer = new PrintWriter(output, true);
        System.out.println("ServerThread passed checkpoint 1");
        setPriority(Thread.MAX_PRIORITY);
        while(gameSession.getGameIsRunning())
        {
            System.out.println("ServerThread passed checkpoint 2");
            try {
                sleep(gameSession.getGameDelay());
            }
            catch (Exception e) {System.out.println("Error in ServerThread");}
            writer.println(gameSession.socketHelper(reader.readLine()));
            System.out.println("ServerThread passed checkpoint 4");
        }
    }
}

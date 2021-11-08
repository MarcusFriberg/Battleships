package com.edugames.model;

import com.edugames.controller.GameController;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class GameSession {

    Boolean isServer;
    private String si;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String incoming;
    private GameController gameController;
    private int shotFired;
    // Detta tillhör Martins:
    // private int totalShotsFired;
    private String lastShotFired;


    //Make a constructor that takes parameters to start a new session as client or server
    //Create a new server connection
    //Create a GameController and send a parameter with shoot or wait
    //Create methods to send information about incoming shots to the GameController
    //Create methods to receive information from GameController about shots fired and results of enemy shots
    //

/*    Vi behöver kunna skicka information till GameController
- om alla inkommande skott
- om det föregående skottet blev en träff (kräver att vi “kommer ihåg” koordinaterna för vårt senaste skott)
    Vi behöver kunna ta emot information från GameController
- om vilken koordinat vi beskjuter på motståndarens spelplan
- om motståndarens senaste skott blev en träff*/

    // TODO: Är tanken att jag ska göra en metod för att ta emot och skicka för varje s1,s2,s3,s4 osv eller bara en
    // TODO: metod som tar emot och tolkar och sparar det i en incoming String och sen skickar det till GameController?

    public GameSession(Boolean isServer) {
        this.isServer = isServer;
    }

    public void initGameSession(String si) {
        this.si = si;
        lastShotFired = si;
        // TODO: Send to socket here or in a separate method?
        }


    public String incomingFire() {
        // TODO: Divide into sub strings and only send the coordinates?? Sockets incoming here?
        return s1;
    }

    public void outgoingFire(String s1) {
        // TODO: Send to socket
    }


    //TODO: Är detta rätt sätt:


    public void incomingData(String incoming) {
        //TODO: Dela upp i substrängar eller index samt en if (subString1 == "h")/ if (incoming.plats(0) == "h"
        // så skickas det till GameController att det har blivit en träff. På så vis kan alla ligga under incoming
    }


}

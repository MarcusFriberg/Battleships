package com.edugames.model;

public class GameSession {
    private int shotFired;
    // Detta tillhör Martins:
    // private int totalShotsFired;
    private String incomingShot;
    private int lastIncomingShot;

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

    public GameSession() {
    }

    public int incomingShot() {
        if ()

        return shotFired;
    }
}

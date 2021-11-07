package com.edugames.model;
// Imports
import java.util.ArrayList;
import java.util.List;

/*
 * Class Ship
 * A class to create a ship object.
 * The ship will know its position by storing the coordinates in an ArrayList.
 * The ship will know if all the coordinates is hit.
 * Constructor receives the following parameters
 * @param: shipSize(int) A number between 2 and 5, will determine what kind of ship it is.
 * @param: shipAlignment(char) h or v for horizontal or vertical placement.
 * @param: shipStartXPos(int) the x coordinate where the ship starts
 * @param: shipStartYPos(int) the y coordinate where the ship starts
 * For horizontal placement the ship will be placed from the start coordinate and shipSize-1 coordinates to the right.
 * For vertical placement the ship will be placed from the start coordinate and shipSize-1 coordinates down.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @version: 1.0
 */
public class Ship {
    // variables
    private int shipSize;
    private char shipAlignment;
    private int shipStartXPos;
    private int shipStartYPos;
    private String shipType;
    private List<Coordinate> shipCoordinates = new ArrayList<>();
    private Coordinate[][] playerPanelCoordinates;

    // Constructor
    public Ship(int shipSize, char shipAlignment, int shipStartXPos, int shipStartYPos, Coordinate[][] playerPanelCoordinates) {
        this.shipSize = shipSize;
        this.shipAlignment = shipAlignment;
        this.shipStartXPos = shipStartXPos;
        this.shipStartYPos = shipStartYPos;
        this.playerPanelCoordinates = playerPanelCoordinates;
        setShipType();
        storeShipCoordinates();
    }

    // Getters
    public String getShipType() {
        return shipType;
    }

    // Setters
    private void setShipType() {
        switch (shipSize) {
            case 2:
                shipType = "submarine";
                break;
            case 3:
                shipType = "cruiser";
                break;
            case 4:
                shipType = "battleship";
                break;
            case 5:
                shipType = "aircraft carrier";
                break;
            default:
                System.out.println("The system have provided a shipSize to the constructor of class Ship that is not valid. Terminating application to get attention!");
                System.exit(0);
        }
    }
    // write description
    public void storeShipCoordinates(){
        // Create code to store the coordinates that the ship is located on into shipCoordinates.
    }
}

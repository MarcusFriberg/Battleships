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
    private final int shipSize;
    private final char shipAlignment;
    private final int shipStartXPos;
    private final int shipStartYPos;
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

    /*
    * Method setShipType
    * A method to set the variable shipType to a string describing what kind
    * of ship this is. The shipSize is used to set this value.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrad.se
    * @version: 1.0
    */
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

    /*
     * Method storeShipCoordinates
     * A method to store all the coordinates that the ship is placed on.
     * Method uses the shipAlignment to add coordinate objects either on the right side
     * of the start coordinate or below the start coordinate depending on horizontal or vertical
     * alignment. Loop starts at the coordinate and continues as long as iteration is less than
     * startPosition + shipSize. Method will also call the setShipOnThisCoordinate method of the
     * coordinate-object and send itself (this ship) as a parameter and make calls to the coordinates
     * setHasShip()-method with the parameter true and finally call changeImage()-method to update
     * the coordinates image.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrad.se
     * @version: 1.0
     */
    public void storeShipCoordinates(){
        switch (shipAlignment) {
            case 'h':
                for (int i = shipStartXPos; i < (shipStartXPos+shipSize); i++) {
                    shipCoordinates.add(playerPanelCoordinates[i][shipStartYPos]);
                    playerPanelCoordinates[i][shipStartYPos].setShipOnThisCoordinate(this);
                    playerPanelCoordinates[i][shipStartYPos].setHasShip(true);
                    playerPanelCoordinates[i][shipStartYPos].changeImage();
                    // --TODO-- Remove sout below, only to confirm this is working while testing.
                    System.out.println("Added ship to coordinate " + playerPanelCoordinates[i][shipStartYPos].getX() + playerPanelCoordinates[i][shipStartYPos].getY());
                }
                break;
            case 'v':
                for (int i = shipStartYPos; i < (shipStartYPos+shipSize); i++) {
                    shipCoordinates.add(playerPanelCoordinates[shipStartXPos][i]);
                    playerPanelCoordinates[shipStartXPos][i].setShipOnThisCoordinate(this);
                    playerPanelCoordinates[shipStartXPos][i].setHasShip(true);
                    playerPanelCoordinates[shipStartXPos][i].changeImage();
                    // --TODO-- Remove sout below, only to confirm this is working while testing.
                    System.out.println("Added ship to coordinate " + playerPanelCoordinates[shipStartXPos][i].getX() + playerPanelCoordinates[shipStartXPos][i].getY());
                }
                break;
            default:
                System.out.println("Something went wrong when storing ship's coordinate-objects. Terminating application!");
                System.exit(0);
        }
    }

    /*
     * Method checkIfShipIsSunken
     * A method to check if the ship is Sunken.
     * @returns: Boolean - true if ship is sunken, else false.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrad.se
     * @version: 1.0
     */
    public Boolean checkIfShipIsSunken() {
        Boolean sunken = true;
        for(Coordinate coordinate : shipCoordinates) {
            if(!coordinate.getIsHit()) {
                sunken = false;
            }
        }
        return sunken;
    }
}
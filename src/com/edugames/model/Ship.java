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
    private final List<Coordinate> shipCoordinates = new ArrayList<>();
    private final Coordinate[][] playerPanelCoordinates;

    // Constructor
    public Ship(int shipSize, char shipAlignment, int shipStartXPos, int shipStartYPos, Coordinate[][] playerPanelCoordinates) {
        this.shipSize = shipSize;
        this.shipAlignment = shipAlignment;
        this.shipStartXPos = shipStartXPos;
        this.shipStartYPos = shipStartYPos;
        this.playerPanelCoordinates = playerPanelCoordinates;
        storeShipCoordinates();
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
                }
                break;
            case 'v':
                for (int i = shipStartYPos; i < (shipStartYPos+shipSize); i++) {
                    shipCoordinates.add(playerPanelCoordinates[shipStartXPos][i]);
                    playerPanelCoordinates[shipStartXPos][i].setShipOnThisCoordinate(this);
                    playerPanelCoordinates[shipStartXPos][i].setHasShip(true);
                    playerPanelCoordinates[shipStartXPos][i].changeImage();
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
        boolean sunken = true;
        for(Coordinate coordinate : shipCoordinates) {
            if (!coordinate.getIsHit()) {
                sunken = false;
                break;
            }
        }
        return sunken;
    }
}
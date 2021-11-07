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

    public Ship(int shipSize, char shipAlignment, int shipStartXPos, int shipStartYPos) {
        this.shipSize = shipSize;
        this.shipAlignment = shipAlignment;
        this.shipStartXPos = shipStartXPos;
        this.shipStartYPos = shipStartYPos;
    }
}

package com.edugames.model;

/*
* Class Target
* A class to create a Target-object that will be used to fetch the Coordinate-object in the enemypanel we're
* shooting at. Will contain xCoordinate and yCoordinate, both of type int (0-9) to give easy access to the desired
* Coordinate object on those indexes in the enemyPanel's Coordinate[][] array.
* @author: Marcus Friberg
* @author: marcus.friberg@edu.edugrade.se
* @version: 1.0
 */
public class Target {
    // Variables
    private int xCoordinate;
    private int yCoordinate;

    // Constructor
    public Target(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // Getters
    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}

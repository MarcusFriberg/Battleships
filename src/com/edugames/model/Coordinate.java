package com.edugames.model;
/*
 * Class Coordinate
 * The class is created to coordinate a ship or a few ships.
 *
 * @param: The constructor is created for receiveing parameters as:
 * - x(char)the horizontal position of the coordinates.
 * - y(char) the vertical position of the coordinates.
 * - isHit(boolean)  if true shows a X-mark on the ship.
 * - hasShip(boolean) if true shows a image where the ship is located .
 * - imageFileName(String) images of the ship/ships.
 * @author: Shermin Gilanziadeh
 * @author: shermin.gilanizadeh.edu.edugrade.com
 * @version: 1.0
 */

public class Coordinate {
    // Variables
    private final char x;
    private final char y;
    private boolean isHit;
    private boolean hasShip;
    private String imageFileName;
    private Ship shipOnThisCoordinate;


    // Constructor
    public Coordinate(char x, char y, boolean isHit, boolean hasShip, String imageFileName) {
        this.x = x;
        this.y = y;
        this.isHit = isHit;
        this.hasShip = hasShip;
        this.imageFileName = imageFileName;
    }

    // Getters and Setters
    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean getHasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public String getImageFileName() {
      return imageFileName;
    }
    
    public void setImageFileName(String imageFileName) {
      this.imageFileName = imageFileName;
    }
    
    public Ship getShipOnThisCoordinate(){
        return shipOnThisCoordinate;
    }

    public void setShipOnThisCoordinate(Ship ship){
        this.shipOnThisCoordinate = ship;
    }
      
    /*
     * Method changeImage
     * A method used for to change images when a coordinate is hit.
     * Uses isHit, hasShip to show the correct image.
     * @author: Shermin Gilanizadeh
     * @author: shermin.gilanizadeh @edu.edugrad.se
     * @version: 1.0
     */
    public void changeImage() {
        if(this.isHit) {
            if (this.hasShip) {
               // the coordinates show a ship ,when ship is hit image changes image to ship-hit.
                this.setImageFileName("coord-ship-hit.png");
            } else {
                //Image show there is no ship and but coordinate is hit.
                this.setImageFileName("coord-empty-hit.png");}
        } else {
            if(this.hasShip) {
                // Image show there is a ship on the coordinare but the ship is not hit.
                this.setImageFileName("coord-ship-noHit.png");
            } else {
                // image shows an empty coordinate whit no hit.
                this.setImageFileName("coord-empty-noHit.png");
            }
        }
    }
}

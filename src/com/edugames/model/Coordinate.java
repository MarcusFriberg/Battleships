package com.edugames.model;

public class Coordinate
{
    private char x;
    private char y;
    private boolean isHit;
    private boolean hasShip;
    private String imageFileName;
    private Ship shipOnThisCoordinate;

    public Coordinate(char x, char y, boolean isHit, boolean hasShip, String imageFileName)
    {
        this.x = x;
        this.y = y;
        this.isHit = isHit;
        this.hasShip = hasShip;
        this.imageFileName = imageFileName;
        System.out.println(y + " " + x);
    }

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

    public Ship getShipOnThisCoordinate() {
        return shipOnThisCoordinate;
    }

    public void setShipOnThisCoordinate(Ship ship) {
        this.shipOnThisCoordinate = ship;
    }

    public void changeImage()
    {
        if(this.isHit)
        {
            if(this.hasShip)
            {
                this.setImageFileName("coord-ship-hit.png");
            }
            else
            {
                this.setImageFileName("coord-empty-hit.png");
            }
        }
        else
        {
            if(this.hasShip)
            {
                this.setImageFileName("coord-ship-noHit.png");
            }
            else
            {
                this.setImageFileName("coord-empty-noHit.png");
            }

        }
    }

}

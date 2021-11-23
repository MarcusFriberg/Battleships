package com.edugames.model;

import com.edugames.controller.GameController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/*
 * Class AIPlayer
 * A class that creates an AI-player. The AI-player places ships, check the number of ships left, checks if game over and shots.
 * @param: Constructor receives playerPanel-coordinates and gameController(GameController) as parameter.
 * @author: Shermin Gilanziadeh
 * @author: shermin.gilanizadeh.edu.edugrade.com
 * @co-author: Linda Djurström
 * @co-author: linda.djurstrom.edu.edugrade.com
 * @version: 1.0
 */
public class AIPlayer{
    //Variables
    private GameController gameController;
    private Coordinate[][] playerPanelCoordinates;
    private List<Ship> myShips = new ArrayList<>();
    private List<Coordinate> coordinateList = new ArrayList<>();
    private List<Target> possibleTargets = new ArrayList<>();

    private boolean nextTargetShouldBeRandom = true;
    private boolean lastTargetWasAHit = true;
    private boolean directionIsKnown = false;

    private Target lastTarget = null;
    private Target firstHitOnNewShip = null;
    private Target lastTargetThatDidHit = null;

    Target target = null;


    //Constructor
    public AIPlayer(Coordinate[][] coordinates, GameController gameController){
        this.playerPanelCoordinates = coordinates;
        this.gameController = gameController;
        placeShips();
        initTargets();
    }

    /*
     * Method placeShips
     * A method that places ships randomly.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrad.se
     * @version: 1.0
     */
    public void placeShips(){
        //Local variables
        String alignmentLetters = "hv";
        String numbers = "0123456789";
        String letters = "abcdefghij";
        char alignment;
        int xPos;
        int yPos;
        Random random = new Random();
        int randomCoordinate;
        int shipLength;
        boolean check;
        int[] arrayXPos;
        int[] arrayYPos;

        //A for-loop that creates a list with the coordinates stored in playerPanelCoordinates. The list are going to hold
        // available coordinates to place ships on.
        int k = 0;
        for (Coordinate[] playerPanelCoordinate : playerPanelCoordinates){
            for (int j = 0; j < playerPanelCoordinates[1].length; j++){
                coordinateList.add(k, playerPanelCoordinate[j]);
                k++;
            }
        }

        //The main for-loop in this method starts. 10 ships are created. The turn in the loop decides the shipLength.
        for (int i = 0; i < 10; i++){
            if (i < 1)
                shipLength = 5;
            else if (i < 3)
                shipLength = 4;
            else if (i < 6)
                shipLength = 3;
            else
                shipLength = 2;

            //Alignment of the ship that is going to be placed are randomized.
            alignment = alignmentLetters.charAt(random.nextInt(2));
            //A random coordinate from the list coordinateList is chosen as a starting-position. A random int chooses a place in the list
            //and uses the object at that place in the list. The y-coordinates and the x-coordinates of that object is stored in variabels.
            randomCoordinate = random.nextInt(coordinateList.size());
            xPos = numbers.lastIndexOf(coordinateList.get(randomCoordinate).getX());
            yPos = letters.lastIndexOf(coordinateList.get(randomCoordinate).getY());

            //Depending on if the alignment is horisontell och vertical this code checks
            //if the placement is outside the playerPanel. If the placement are outside, the code
            //moves back the starting position the same amount of positions as the length of the
            // ship in horisontell or vertical direction.
            if(alignment == 'h' && xPos > playerPanelCoordinates[1].length - shipLength)
                xPos -= shipLength;
            else if(alignment == 'v' && yPos > playerPanelCoordinates.length - shipLength)
                yPos -= shipLength;

            //New array:s that store the ship coordinates, the arrays are used later in the code. The length of the ship
            //determines the length of the array.
            arrayXPos = new int[shipLength];
            arrayYPos = new int[shipLength];
            //A new for-loop to check if it is ok to place ships on the coordinates that the ship is going to fill.
            //Every turn in the loop checks a new coordinate. The coordinate being checked depends on the
            //direction and the length of the ship. The ship-length is decided by number of turns in the loop
            //and the directions is decided by an if-else sats.
            check = true;
            for(int j = 0; j < shipLength; j++){
                int xPosTemp = xPos;
                int yPosTemp = yPos;
                if (alignment == 'h')
                    xPosTemp = xPosTemp + j;
                else if (alignment == 'v')
                    yPosTemp = yPosTemp + j;
                //if the coordinates is not in the list coordinateList the boolean check is marked as false.
                if(!coordinateList.contains(playerPanelCoordinates[xPosTemp][yPosTemp]))
                    check = false;
                //x-pos and y-pos is stored in two arrays for later use.
                arrayXPos[j] = xPosTemp;
                arrayYPos[j] = yPosTemp;
            }

            //If the ship passes the check a new ship is created by a call to the gameController (who itself calls upon the ship-class).
            //The newly created ship is stored in the list myShips.
            if(check){
                myShips.add(gameController.createShip(shipLength, alignment, xPos, yPos));
                //If ship is added on coordinates, the coordinates needs to be removed from the coordinateList
                // (that holds available coordinates to place ships on). A for-loop loops true the coordinates stored in the two arrays created above.
                for(int j = 0; j < shipLength; j++){
                    //Variables that's going to symbolises one movement back and forth on the x-axel and y-axel. First they are initialized with the
                    //same value as the x and y positions stored in the arrays different positions (depending on the turn in the loop).
                    int XPlus1 = arrayXPos[j];
                    int YPlus1 = arrayYPos[j];
                    int XMinus1 = arrayXPos[j];
                    int YMinus1 = arrayYPos[j];
                    //If the x and y coordinates can be minus and plus one without ending up outside the playerPanel the variables changes value.
                    if (arrayXPos[j] < (playerPanelCoordinates.length - 1))
                        XPlus1 = arrayXPos[j] + 1;
                    if (arrayYPos[j] < (playerPanelCoordinates[1].length - 1))
                        YPlus1 = arrayYPos[j] + 1;
                    if (arrayXPos[j] > 0)
                        XMinus1 = arrayXPos[j] - 1;
                    if (arrayYPos[j] > 0)
                        YMinus1 = arrayYPos[j] - 1;
                    //Next the code removes the coordinate in the loop + all the coordinates nearby area. A try-catch-block ignores error-
                    //messages when the code tries to remove a coordinate already removed from the list and continues to remove the next coordinate.
                    try{
                        coordinateList.remove(playerPanelCoordinates[arrayXPos[j]][arrayYPos[j]]);
                        coordinateList.remove(playerPanelCoordinates[arrayXPos[j]][YPlus1]);
                        coordinateList.remove(playerPanelCoordinates[arrayXPos[j]][YMinus1]);
                        coordinateList.remove(playerPanelCoordinates[XPlus1][YMinus1]);
                        coordinateList.remove(playerPanelCoordinates[XPlus1][arrayYPos[j]]);
                        coordinateList.remove(playerPanelCoordinates[XPlus1][YPlus1]);
                        coordinateList.remove(playerPanelCoordinates[XMinus1][arrayYPos[j]]);
                        coordinateList.remove(playerPanelCoordinates[XMinus1][YMinus1]);
                        coordinateList.remove(playerPanelCoordinates[XMinus1][YPlus1]);
                    }catch(ArrayIndexOutOfBoundsException ignored){
                    }
                }
                //If the check returns false, no ship is placed and the loop backs one turn.
            }else
                i--;
        }
    }

    /*
     * Method checkGameOver.
     * A method that checks if all ships are sunk.
     * @returns boolean. True if all ships are sunk, else false.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public boolean checkGameOver() {
        //Variables.
        boolean gameOver = false;
        //Loops true the list with all the created ships. Calls on the method in ships that checks if the ship has sunken.
        //If the ship has sunken the ship is removed from the list.
        for(int i = 0; i < myShips.size(); i++) {
            if (myShips.get(i).checkIfShipIsSunken())
                myShips.remove(myShips.get(i));
        }
        //If the list myShips is empty boolean gameOver is changed to true.
        if(myShips.isEmpty())
            gameOver = true;
        //Return true if all ship is sunken, else false(the initial value).
        return gameOver;
    }

    /*
     * Method getNumberOfShipsOfShipSize.
     * A method that checks how many ships of a specific size that is left.
     * @params int shipSize. When called upon a shipSize value has to be sent in.
     * @returns int. Returns how many ship of the size that is left.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrad.se
     * @version: 1.0
     */
    public int getNumberOfShipsOfShipSize(int shipSize) {
        //Variables.
        int counter = 0;
        //For-loop that loops true the list myShips with and counter, that counts how many times a ship of the size is in
        //the list.
        for(Ship myShip : myShips) {
            if(myShip.getShipSize() == shipSize) {
                counter++;
            }
        }
        //Returns the counters value.
        return counter;
    }

    /*
     * Method enemyShipWasDestroyed()
     * A method that changes variables after a ship was sunk.
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public boolean enemyShipWasDestroyed() {
        lastTargetThatDidHit = lastTarget;
        // If first Target that hit a new ship is on the same row as last Target that hit a ship
        if(lastTargetThatDidHit.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
            // If we have been shooting from left to right
            if(lastTargetThatDidHit.getXCoordinate() > firstHitOnNewShip.getYCoordinate()) {
                // For each Target in possibleTargets, check if its directly below or above from first Target that hit new ship and remove that Target from possibleTargets
                for(Iterator<Target> target = possibleTargets.iterator();
                    target.hasNext();) {
                    Target targetToRemove = target.next();
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1)) {
                        if(lastTargetThatDidHit.getXCoordinate() < 9 && lastTargetThatDidHit.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1)) {
                        if(lastTargetThatDidHit.getXCoordinate() <9 && lastTargetThatDidHit.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1) && targetToRemove.getYCoordinate() == lastTargetThatDidHit.getYCoordinate()) {
                        if(lastTargetThatDidHit.getXCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() +1)) {
                        if(firstHitOnNewShip.getXCoordinate() > 0 && firstHitOnNewShip.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() -1)) {
                        if(firstHitOnNewShip.getXCoordinate() > 0 && firstHitOnNewShip.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() -1) && targetToRemove.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                        if(firstHitOnNewShip.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                }
            // We have been shooting from right to left
            } else {
                // For each Target in possibleTargets, check if its directly below or above from first Target that hit new ship and remove that Target from possibleTargets
                for(Iterator<Target> target = possibleTargets.iterator();
                    target.hasNext();) {
                    Target targetToRemove = target.next();
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1)) {
                        if(lastTargetThatDidHit.getXCoordinate() > 0 && lastTargetThatDidHit.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1)) {
                        if(lastTargetThatDidHit.getXCoordinate() > 0 && lastTargetThatDidHit.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1) && targetToRemove.getYCoordinate() == lastTargetThatDidHit.getYCoordinate()) {
                        if(lastTargetThatDidHit.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() +1)) {
                        if(firstHitOnNewShip.getXCoordinate() < 9 && firstHitOnNewShip.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() -1)) {
                        if(firstHitOnNewShip.getXCoordinate() <9 && firstHitOnNewShip.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() +1) && targetToRemove.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                        if(firstHitOnNewShip.getXCoordinate() < 9) {
                            target.remove();
                        }
                    }
                }
            }
        }
        // If first Target that hit a new ship is in the same column as last Target that hit a ship
        if(lastTargetThatDidHit.getXCoordinate() == firstHitOnNewShip.getXCoordinate()) {
            // If we have been shooting downwards
            if(lastTargetThatDidHit.getYCoordinate() > firstHitOnNewShip.getYCoordinate()) {
                // For each Target in possibleTargets, check if its directly below or above from first Target that hit new ship and remove that Target from possibleTargets
                for(Iterator<Target> target = possibleTargets.iterator();
                    target.hasNext();) {
                    Target targetToRemove = target.next();
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1)) {
                        if(lastTargetThatDidHit.getYCoordinate() < 9 && lastTargetThatDidHit.getXCoordinate() <9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1)) {
                        if(lastTargetThatDidHit.getYCoordinate() < 9 && lastTargetThatDidHit.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1) && targetToRemove.getXCoordinate() == lastTargetThatDidHit.getXCoordinate()) {
                        if(lastTargetThatDidHit.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() +1)) {
                        if(firstHitOnNewShip.getYCoordinate() > 0 && firstHitOnNewShip.getXCoordinate() < 9){
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() -1)) {
                        if(firstHitOnNewShip.getYCoordinate() > 0 && firstHitOnNewShip.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() -1) && targetToRemove.getXCoordinate() == firstHitOnNewShip.getXCoordinate()) {
                        if(firstHitOnNewShip.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                }
            // We have been shooting upwards
            } else {
                for(Iterator<Target> target = possibleTargets.iterator();
                    target.hasNext();) {
                    Target targetToRemove = target.next();
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1)) {
                        if(lastTargetThatDidHit.getYCoordinate() > 0 && lastTargetThatDidHit.getXCoordinate() < 9){
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1)) {
                        if(lastTargetThatDidHit.getYCoordinate() > 0 && lastTargetThatDidHit.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1) && targetToRemove.getXCoordinate() == lastTargetThatDidHit.getXCoordinate()) {
                        if(lastTargetThatDidHit.getYCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() +1)) {
                        if(firstHitOnNewShip.getYCoordinate() < 9 && firstHitOnNewShip.getXCoordinate() <9) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() -1)) {
                        if(firstHitOnNewShip.getYCoordinate() < 9 && firstHitOnNewShip.getXCoordinate() > 0) {
                            target.remove();
                        }
                    }
                    if(targetToRemove.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() +1) && targetToRemove.getXCoordinate() == firstHitOnNewShip.getXCoordinate()) {
                        if(firstHitOnNewShip.getYCoordinate() < 9) {
                            target.remove();
                        }
                    }
                }
            }

        }
        nextTargetShouldBeRandom = true;
        lastTargetWasAHit = true;
        directionIsKnown = false;
        firstHitOnNewShip = null;
        lastTargetThatDidHit = null;

        //TODO: ev.kolla koordinaterna på target som sänkte skeppet. Jämföra med koordinaterna i enemyPanel som innehåller
        //hasShip true, isHit true och ta bort alla direkt intilliggande targets från possibletargets.
        return true;
    }

    /*
     * Method initTargets()
     * A method that creates a list with available targets.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrad.se
     * @version: 1.0
     */
    public void initTargets() {
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                possibleTargets.add(new Target(x, y));
            }
        }
    }

    //Setter
    public boolean setLastTargetWasAHit(boolean lastTargetWasAHit) {
        this.lastTargetWasAHit = lastTargetWasAHit;
        if(lastTargetWasAHit) {
            lastTargetThatDidHit = lastTarget;
            if(nextTargetShouldBeRandom) {
                firstHitOnNewShip = lastTarget;
                nextTargetShouldBeRandom = false;
                directionIsKnown = false;
            } else {
                directionIsKnown = true;
                // If first Target that hit a new ship is on the same row as last Target that hit a ship
                if(lastTargetThatDidHit.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                    // For each Target in possibleTargets, check if its directly below or above from last Target that hit new ship and remove that Target from possibleTargets
                    for(Iterator<Target> target = possibleTargets.iterator();
                        target.hasNext();) {
                        Target targetToRemove = target.next();
                        if (targetToRemove.getXCoordinate() == lastTargetThatDidHit.getXCoordinate() && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() + 1)) {
                            if (lastTargetThatDidHit.getYCoordinate() < 9) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                        if(targetToRemove.getXCoordinate() == lastTargetThatDidHit.getXCoordinate() && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1)) {
                            if(lastTargetThatDidHit.getYCoordinate() > 0) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                        if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1)) {
                            if(lastTargetThatDidHit.getYCoordinate() > 0) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                        if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1)) {
                            if(lastTargetThatDidHit.getYCoordinate() > 0) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                        if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1)) {
                            if(lastTargetThatDidHit.getYCoordinate() < 9) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                        if(targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() +1) && targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1)) {
                            if(lastTargetThatDidHit.getYCoordinate() < 9) {
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                                target.remove();
                            }
                        }
                    }
                }
                // If first Target that hit a new ship is in the same column as last Target that hit a ship
                if(lastTargetThatDidHit.getXCoordinate() == firstHitOnNewShip.getXCoordinate()) {
                    // For each Target in possibleTargets, check if its directly below or above from last Target that hit new ship and remove that Target from possibleTargets
                    for(Iterator<Target> target = possibleTargets.iterator();
                        target.hasNext();) {
                        Target targetToRemove = target.next();
                        if (targetToRemove.getYCoordinate() == lastTargetThatDidHit.getYCoordinate() && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() + 1)) {
                            if (lastTargetThatDidHit.getXCoordinate() < 9) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }
                        if (targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() + 1)) {
                            if (lastTargetThatDidHit.getXCoordinate() < 9) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }
                        if (targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() + 1)) {
                            if (lastTargetThatDidHit.getXCoordinate() < 9) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }
                        if(targetToRemove.getYCoordinate() == lastTargetThatDidHit.getYCoordinate() && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1)) {
                            if(lastTargetThatDidHit.getXCoordinate() > 0) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }
                        if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() +1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1)) {
                            if(lastTargetThatDidHit.getXCoordinate() > 0) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }
                        if(targetToRemove.getYCoordinate() == (lastTargetThatDidHit.getYCoordinate() -1) && targetToRemove.getXCoordinate() == (lastTargetThatDidHit.getXCoordinate() -1)) {
                            if(lastTargetThatDidHit.getXCoordinate() > 0) {
                                target.remove();
                                System.out.println("removed target at " + targetToRemove.getXCoordinate() + targetToRemove.getYCoordinate());
                            }
                        }

                    }
                }
            }
        }
        return true;
    }

    /*
     * Method fireAtTarget()
     * A method that shots at a Target. Calls upon method fetchNewTarget(). Stores the target in
     * other variables and removes it from the list of possible targets.
     * @returns a Target to the gameController.
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fireAtTarget() {
            target = fetchNewTarget();
            lastTarget = target;
            possibleTargets.remove(target);
            return target;
    }

    /*
     * Method fetchNewTarget()
     * A method that decides if the target should be a random or not.
     * @returns a Target to the method fireAtTarget.
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fetchNewTarget() {
        //gameController decides if the next target should be random depending on if it was a hit or not
        // or if a ship was sunk. One method is chosen.
        if(nextTargetShouldBeRandom)
            target = fetchNewShipNotFoundTarget();
        else
            target = fetchNewNoneRandomTarget();
        return target;
    }

    /*
     * Method fetchNewShipNotFoundTarget()
     * A method that decides if the target should be a new random or continue diagonal.
     * @returns a Target to the method fetchNewTarget()
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fetchNewShipNotFoundTarget() {
        boolean foundNewTarget = false;
        //If last target was a hit(a ship was sunk), the target should be a new random. Else it continues diagonal.
        //The diagonal searches for available targets in diagonal starting from the last targets coordinates.
        if(lastTargetWasAHit) {
            target = fetchRandomTarget();
        } else {
            int x = lastTarget.getXCoordinate();
            int y = lastTarget.getYCoordinate();

            if(x < 9 && y < 9) {
                x += 1;
                y += 1;
                for(Target possibleTarget : possibleTargets) {
                    if(possibleTarget.getXCoordinate() == x && possibleTarget.getYCoordinate() == y) {
                        target = possibleTarget;
                        foundNewTarget = true;
                    }
                }
                if(!foundNewTarget) {
                    target = fetchRandomTarget();
                }
            } else {
                target = fetchRandomTarget();
            }
        }
        return target;
    }

    public Target fetchRandomTarget() {
        Random random = new Random();
        target = possibleTargets.get(random.nextInt(possibleTargets.size()));
        lastTargetWasAHit = false;
        return target;
    }

    /*
     * Method fetchNewNoneRandomTarget
     * A method that decides that depending on if the direction is known another method is chosen.
     * @returns a Target to the method fetchNewTarget()
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fetchNewNoneRandomTarget() {
        if(directionIsKnown) {
            target = fetchNewTargetInDirectionFromLastTarget();
        }else{
            target = fetchNewTargetNearLastTarget();
        }
        return target;
    }

    /*
     * Method fetchNewTargetInDirectionFromLastTarget
     * A method when the ship-direction is known, and we are seeking for hits in booth directions from the last target.
     * @returns a Target to the method fetchNewNoneRandomTarget()
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fetchNewTargetInDirectionFromLastTarget() {
        boolean newTargetHasBeenSet = false;
        //If the last target is on the same y-coordinate, it has moved on the x-axel.
        if(lastTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
            //If the last-targets x-axel is bigger than the firstHitOnNewShips x-axel(on the right).
            if(lastTarget.getXCoordinate() > firstHitOnNewShip.getXCoordinate()) {
                //If the lastTargets x-coordinate is 9 or if the last target wasn't a hit.
                if(lastTarget.getXCoordinate() == 9 || !lastTargetWasAHit) {
                    //Searching for the target on the left of the first hit on the ship instead. Changes direction.
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() - 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                    //If the target was a hit and smaller than 9, we are continuing searching for the target on the right of the firstHitOnNewShip.
                } else {
                    for(Target possibleTarget : possibleTargets){
                        if(possibleTarget.getXCoordinate() == (lastTarget.getXCoordinate() + 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()){
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                }
                //If the last-targets x-axel is smaller than the firstHitOnNewShips x-axel(on the left).
            } else if(lastTarget.getXCoordinate() < firstHitOnNewShip.getXCoordinate()){
                //If the lastTargets x-coordinate is 0 or if the last target wasn't a hit.
                if(lastTarget.getXCoordinate() == 0 || !lastTargetWasAHit){
                    //Searching for the target on the right of the first hit on the ship instead. Changes direction.
                    for(Target possibleTarget : possibleTargets){
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() + 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()){
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                }else{
                    for(Target possibleTarget : possibleTargets){
                        if(possibleTarget.getXCoordinate() == (lastTarget.getXCoordinate() - 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()){
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                }
            }
            //If the last target is on the same x-coordinate, it has moved on the y-axel.
        } else if(lastTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate()) {
            //If the last-targets y-axel is bigger than the firstHitOnNewShips y-axel(downwards).
            if(lastTarget.getYCoordinate() > firstHitOnNewShip.getYCoordinate()) {
                //If the lastTargets x-coordinate is 9 or if the last target wasn't a hit.
                if(lastTarget.getYCoordinate() == 9 || !lastTargetWasAHit) {
                    //Searching for the target upwards of the first hit on the ship instead. Changes direction.
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate() && possibleTarget.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() - 1)) {
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                } else {
                    for(Target possibleTarget : possibleTargets) { // --TODO-- På raden nedan var firstHitOnNewShip och lastTarget förväxlade, därför sköt den om och om igen på koordinaten nedanför firstHitOnNewShip
                        if(possibleTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate() && possibleTarget.getYCoordinate() == (lastTarget.getYCoordinate() + 1)) {
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                }
                //If the last-targets y-axel is smaller than the firstHitOnNewShips y-axel(upwards).
            } else if(lastTarget.getYCoordinate() < firstHitOnNewShip.getYCoordinate()) {
                //If the lastTargets y-coordinate is 0 or if the last target wasn't a hit.
                if(lastTarget.getYCoordinate() == 0 || !lastTargetWasAHit) {
                    //Searching for the target downwards of the first hit on the ship instead. Changes direction.
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate()) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate() + 1) {
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                } else {
                    for(Target possibleTarget : possibleTargets) { // --TODO-- På raden nedan var firstHitOnNewShip och lastTarget förväxlade, därför sköt den om och om igen på koordinaten nedanför firstHitOnNewShip
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate()) && possibleTarget.getYCoordinate() == lastTarget.getYCoordinate() - 1) {
                            target = possibleTarget;
                            newTargetHasBeenSet = true;
                        }
                    }
                }
            }
        }
        // If a new target has not been set in the code above the next target in the given direction has already been hit and we need to change direction.
        if(!newTargetHasBeenSet) {
            if(lastTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                if(lastTarget.getXCoordinate() > firstHitOnNewShip.getXCoordinate()) {
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() - 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                            target = possibleTarget;
                        }
                    }
                } else {
                    for(Target possibleTarget : possibleTargets){
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() + 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()){
                            target = possibleTarget;
                        }
                    }
                }
            } else {
                if(lastTarget.getYCoordinate() > firstHitOnNewShip.getYCoordinate()) {
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate() && possibleTarget.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() - 1)) {
                            target = possibleTarget;
                        }
                    }
                } else {
                    for(Target possibleTarget : possibleTargets) {
                        if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate()) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate() + 1) {
                            target = possibleTarget;
                        }
                    }
                }
            }
        }
        return target;
    }

    /*
     * Method fetchNewRandomTargetNearLastTarget()
     * A method when a ship is found, and we are searching for direction of the ships.
     * @returns a Target to the method fetchNewNoneRandomTarget()
     * @co-author: Shermin Gilanziadeh
     * @co-author: shermin.gilanziadeh@edu.edugrad.se
     * @co-author: Linda Djurström
     * @co-author: linda.djurstrom@edu.edugrade.se
     * @co-author: Marcus Friberg
     * @co-author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public Target fetchNewTargetNearLastTarget() {
        boolean foundTarget = false;
        //Searches the list of possible targets one by one and se if any of the coordinates around the ship is in the list, and return that one.
        for(Target possibleTarget : possibleTargets) {
            if (possibleTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate() && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate() + 1) {
                target = possibleTarget;
                foundTarget = true;
            }
        }
        if(!foundTarget) {
            for(Target possibleTarget : possibleTargets) {
                if (possibleTarget.getXCoordinate() == firstHitOnNewShip.getXCoordinate() && possibleTarget.getYCoordinate() == (firstHitOnNewShip.getYCoordinate() - 1)) {
                    target = possibleTarget;
                    foundTarget = true;
                }
            }
        }

        if(!foundTarget) {
            for(Target possibleTarget : possibleTargets) {
                if (possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() - 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                    target = possibleTarget;
                    foundTarget = true;
                }
            }
        }
        if(!foundTarget) {
            for(Target possibleTarget : possibleTargets) {
                if(possibleTarget.getXCoordinate() == (firstHitOnNewShip.getXCoordinate() + 1) && possibleTarget.getYCoordinate() == firstHitOnNewShip.getYCoordinate()) {
                    target = possibleTarget;
                }
            }
        }
        return target;
    }
}

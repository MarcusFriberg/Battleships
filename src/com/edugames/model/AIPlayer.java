package com.edugames.model;

import com.edugames.controller.GameController;
import java.util.ArrayList;
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
        int[] arrayXpos;
        int[] arrayYpos;

        //A for-loop that creates a list with the coordinates stored in playerPanelCoordinates. The list are going to hold
        // available coordinates to place ships on.
        int k = 0;
        for(Coordinate[] playerPanelCoordinate : playerPanelCoordinates){
            for(int j = 0; j < playerPanelCoordinates[1].length; j++){
                coordinateList.add(k, playerPanelCoordinate[j]);
                k++;
            }
        }

        //The main for-loop in this method starts. 10 ships are created. The turn in the loop decides the shipLength.
        for(int i = 0; i < 10; i++){
            if(i < 1)
                shipLength = 5;
            else if(i < 3)
                shipLength = 4;
            else if(i < 6)
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
            arrayXpos = new int[shipLength];
            arrayYpos = new int[shipLength];
            //A new for-loop to check if it is ok to place ships on the coordinates that the ship is going to fill.
            //Every turn in the loop checks a new coordinate. The coordinate being checked depends on the
            //direction and the length of the ship. The ship-length is decided by number of turns in the loop
            //and the directions is decided by an if-else sats.
            check = true;
            for(int j = 0; j < shipLength; j++){
                int xPosTemp = xPos;
                int yPosTemp = yPos;
                if(alignment == 'h')
                    xPosTemp = xPosTemp + j;
                else if(alignment == 'v')
                    yPosTemp = yPosTemp + j;
                //if the coordinates is not in the list coordinateList the boolean check is marked as false.
                if (!coordinateList.contains(playerPanelCoordinates[xPosTemp][yPosTemp]))
                    check = false;
                //x-pos and y-pos is stored in two arrays for later use.
                arrayXpos[j] = xPosTemp;
                arrayYpos[j] = yPosTemp;
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
                    int Xplus1 = arrayXpos[j];
                    int Yplus1 = arrayYpos[j];
                    int Xminus1 = arrayXpos[j];
                    int Yminus1 = arrayYpos[j];
                    //If the x and y coordinates can be minus and plus one without ending up outside the playerPanel the variables changes value.
                    if(arrayXpos[j] < (playerPanelCoordinates.length - 1))
                        Xplus1 = arrayXpos[j] + 1;
                    if(arrayYpos[j] < (playerPanelCoordinates[1].length - 1))
                        Yplus1 = arrayYpos[j] + 1;
                    if(arrayXpos[j] > 0)
                        Xminus1 = arrayXpos[j] - 1;
                    if(arrayYpos[j] > 0)
                        Yminus1 = arrayYpos[j] - 1;
                    //Next the code removes the coordinate in the loop + all the coordinates nearby area. A try-catchblock ignores error-
                    //messages when the code tries to remove a coordinate already removed from the list and continues to remove the next coordinate.
                    try{
                        coordinateList.remove(playerPanelCoordinates[arrayXpos[j]][arrayYpos[j]]);
                        coordinateList.remove(playerPanelCoordinates[arrayXpos[j]][Yplus1]);
                        coordinateList.remove(playerPanelCoordinates[arrayXpos[j]][Yminus1]);
                        coordinateList.remove(playerPanelCoordinates[Xplus1][Yminus1]);
                        coordinateList.remove(playerPanelCoordinates[Xplus1][arrayYpos[j]]);
                        coordinateList.remove(playerPanelCoordinates[Xplus1][Yplus1]);
                        coordinateList.remove(playerPanelCoordinates[Xminus1][arrayYpos[j]]);
                        coordinateList.remove(playerPanelCoordinates[Xminus1][Yminus1]);
                        coordinateList.remove(playerPanelCoordinates[Xminus1][Yplus1]);
                    }catch(ArrayIndexOutOfBoundsException ignored){
                    }
                }
                //If the check returns false, no ship is placed and the loop backs one turn.
            } else
                i--;
        }
    }

    /*
     * Method checkGameOver.
     * A method that checks if all ships are sunk.
     * @returns boolean. True if all ships are sunk, else false.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrad.se
     * @version: 1.0
     */
    public boolean checkGameOver(){
        //Variables.
        boolean gameOver = false;
        //Loops true the list with all the created ships. Calls on the method in ships that checks if the ship has sunken.
        //If the ship has sunken the ship is removed from the list.
        for (int i = 0; i < myShips.size(); i++){
            if (myShips.get(i).checkIfShipIsSunken())
                myShips.remove(myShips.get(i));
        }
        //If the list myShips is empty boolean gameOver is changed to true.
        if (myShips.isEmpty())
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
    public int getNumberOfShipsOfShipSize(int shipSize){
        //Variables.
        int counter = 0;
        //For-loop that loops true the list myShips with and counter, that counts how many times a ship of the size is in
        //the list.
        for (int i = 0; i < myShips.size(); i++){
            if (myShips.get(i).getShipSize() == shipSize){
                counter++;
            }
        }
        //Returns the counters value.
        return counter;
    }

    /*
    Shermin
    * Random skott på ej träffad ruta. Gå diagonalt antingen till ett skepp är hittat eller den träffar en kant.
    * Om en kant träffas så ska ett nytt random skott skjutas och sedan börja om med att gå diagonalt.
    *
    Linda
    * Om ett skepp träffas då ska vi skjuta vertikalt upp och ner samt horisontellt på sidorna. Sedan känna av hur den ska fortsätta beroende på träffar.
    * Är det mer än två åt samma håll, ska den antingen horisontell eller vertikal. När den inte hitter mer på en sida gå över till den andra.
     */


    public void enemyShipWasDestroyed(){
        //TODO: Code here please :)
    }

    public void initTargets(){
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                possibleTargets.add(new Target(x,y));
            }
        }
    }

    public Target fireAtTarget(){
        Random random = new Random();
        Target target = possibleTargets.get(random.nextInt(possibleTargets.size()));
        possibleTargets.remove(target);
        return target;
    }

}





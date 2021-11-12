package com.edugames.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {
    private Coordinate[][] playerPanelCoordinates;
    private List<Ship> myShips = new ArrayList<>();
    private List<Coordinate> coordinateList = new ArrayList<>();


    public AIPlayer(Coordinate[][] coordinates) {
        this.playerPanelCoordinates = coordinates;
        placeShips();
    }


    public void placeShips() {
        String alignmentLetters = "hv";
        char alignment;
        String numbers = "0123456789";
        String letters = "ABCDEFGHIJ";
        int xPos;
        int yPos;
        Random random = new Random();
        int randomCoordinate;
        int counter;
// -----------------------------------------------------------------------------------------
        int k = 0;
        for (Coordinate[] playerPanelCoordinate : playerPanelCoordinates) {
            for (int j = 0; j < playerPanelCoordinates[1].length; j++) {
                coordinateList.add(k, playerPanelCoordinate[j]);
                k++;
            }
        }
// ----------------------------------------------------------------------------------------
        for (int i = 0; i < 10; i++) {
            if (i < 1)
                counter = 5;
            else if (i < 3)
                counter = 4;
            else if (i < 6)
                counter = 3;
            else
                counter = 2;
//-----------------------------------------------------------------------------------------------------------

            alignment = alignmentLetters.charAt(random.nextInt(2));
            randomCoordinate = random.nextInt(coordinateList.size());
            xPos = numbers.lastIndexOf(coordinateList.get(randomCoordinate).getX());
            yPos = letters.lastIndexOf(coordinateList.get(randomCoordinate).getY());

//--------------------------------------------------------------------------------------------------

//ToDo: Ev. kan denna flyttas ned så att det inte skapas upp något skepp alls.
            if (alignment == 'h' && xPos > playerPanelCoordinates[1].length - counter)
                xPos -= counter;
            else if (alignment == 'v' && yPos > playerPanelCoordinates.length - counter)
                yPos -= counter;

// -----------------------------------------------------------------------------------------------------
            boolean check = true;
            int[] tempY = new int[counter];
            int[] tempX = new int[counter];

            for (int j = 0; j < counter; j++) {
                int yPosTemp = yPos;
                int xPosTemp = xPos;
                if (alignment == 'v')
                    yPosTemp = yPosTemp + j;
                else if (alignment == 'h')
                    xPosTemp = xPosTemp + j;

                if (!coordinateList.contains(playerPanelCoordinates[xPosTemp][yPosTemp]))
                    check = false;

                tempY[j] = yPosTemp;
                tempX[j] = xPosTemp;
            }
//-----------------------------------------------------------------------------------------------------------------
            if (check) {
                myShips.add(new Ship(counter, alignment, xPos, yPos, playerPanelCoordinates));

                for (int j = 0; j < counter; j++) {
                    try {
                        coordinateList.remove(playerPanelCoordinates[tempX[j]][tempY[j]]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    int temp2 = tempX[j];
                    int temp4 = tempY[j];
                    int temp5 = tempX[j];
                    int temp6 = tempY[j];

                    if (temp2 < (playerPanelCoordinates.length - 1))
                        temp2 = tempX[j] + 1;
                    if (temp4 < (playerPanelCoordinates[1].length - 1))
                        temp4 = tempY[j] + 1;
                    if (temp5 > 0)
                        temp5 = tempX[j] - 1;
                    if (temp6 > 0)
                        temp6 = tempY[j] - 1;

                    try {
                        coordinateList.remove(playerPanelCoordinates[temp2][temp6]);
                        coordinateList.remove(playerPanelCoordinates[temp2][tempY[j]]);
                        coordinateList.remove(playerPanelCoordinates[temp2][temp4]);
                        coordinateList.remove(playerPanelCoordinates[temp5][tempY[j]]);
                        coordinateList.remove(playerPanelCoordinates[temp5][temp6]);
                        coordinateList.remove(playerPanelCoordinates[temp5][temp4]);
                        coordinateList.remove(playerPanelCoordinates[tempX[j]][temp4]);
                        coordinateList.remove(playerPanelCoordinates[tempX[j]][temp6]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            } else
                i--;
        }
    }

    //---------------------------------------------------------------------------
    public boolean checkGameOver() {
        boolean gameOver = false;

        for (int i = 0; i < myShips.size(); i++) {
            if (myShips.get(i).checkIfShipIsSunken()) {
                myShips.remove(myShips.get(i));
            }
        }
        if (myShips.isEmpty()) {
            gameOver = true;
        }
        return gameOver;
    }

    //--------------------------------------------------------------------------------
    public int getNumberOfShipsOfShipSize(int shipSize) {

        int counter = 0;

        for (int i = 0; i < myShips.size(); i++) {
            if (myShips.get(i).getShipSize() == shipSize) {
                counter++;
            }
        }

        return counter;
    }

    }





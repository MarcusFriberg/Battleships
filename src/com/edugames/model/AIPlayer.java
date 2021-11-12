package com.edugames.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer
{
    private Coordinate[][] playerPanelCoordinates;
    private List<Ship> myShips = new ArrayList<>();
    private List<Coordinate> coordinatelist = new ArrayList<>();


    public AIPlayer(Coordinate[][] coordinates) {
        this.playerPanelCoordinates = coordinates;
        placeShips();
    }






    //---------------------------------------------------------------------------------------
    public void placeShips()
    {
        String letters = "hv";
        Random random = new Random();
        char alignment;
        int counter;
        int xpos;
        int ypos;
        String letters1 = "ABCDEFGHIJ";
        String numbers1 = "0123456789";
        int randomcoordinate;
        int k = 0;


// -----------------------------------------------------------------------------------------

        for(int i = 0; i < playerPanelCoordinates.length; i++) {
            for(int j = 0; j < playerPanelCoordinates[1].length; j++){
                coordinatelist.add(k, playerPanelCoordinates[i][j]);
                k++;
            }
        }
// ----------------------------------------------------------------------------------------

        for(int i = 0; i < 10; i++){
            if (i < 1)
                counter = 5;
            else if (i < 3)
                counter = 4;
            else if (i < 6)
                counter = 3;
            else
                counter = 2;

            for(int j = 0; j < coordinatelist.size(); j++) {
                System.out.println(j + " " + coordinatelist.get(j).getX() + "" + coordinatelist.get(j).getY());
            }

//-----------------------------------------------------------------------------------------------------------

         alignment = letters.charAt(random.nextInt(2));
         randomcoordinate = random.nextInt(coordinatelist.size());
         xpos = numbers1.lastIndexOf(coordinatelist.get(randomcoordinate).getX());
         ypos = letters1.lastIndexOf(coordinatelist.get(randomcoordinate).getY());

//--------------------------------------------------------------------------------------------------

//ToDo: Ev. kan denna flyttas ned så att det inte skapas upp något skepp alls.
           if (alignment == 'h' && xpos > playerPanelCoordinates[1].length - counter)
               xpos -= counter;
           else if (alignment == 'v' && ypos > playerPanelCoordinates.length - counter)
               ypos -= counter;

// -----------------------------------------------------------------------------------------------------
           boolean check = true;

           int[] tempy = new int[counter];
           int[] tempx = new int[counter];

           for (int j = 0; j < counter; j++){
                   int ypostemp = ypos;
                   int xpostemp = xpos;
                   if(alignment == 'v')
                       ypostemp = ypostemp + j;
                   else if(alignment == 'h')
                       xpostemp = xpostemp + j;

                   if (coordinatelist.contains(playerPanelCoordinates[xpostemp][ypostemp]) == false)
                       check = false;

               tempy[j] = ypostemp;
               tempx[j] = xpostemp;
           }
//-----------------------------------------------------------------------------------------------------------------

            if(check) {
                myShips.add(new Ship(counter, alignment, xpos, ypos, playerPanelCoordinates));

                for(int j = 0; j < counter; j++){
                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[tempx[j]][tempy[j]]));
                        System.out.println("removed1 " + tempx[j] + "" + tempy[j]);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad1");
                    }


                    int temp2 = tempx[j];
                    int temp4 = tempy[j];
                    int temp5 = tempx[j];
                    int temp6 = tempy[j];

                    if (temp2 < (playerPanelCoordinates[i].length - 1))
                        temp2 = tempx[j] + 1;
                    if (temp4 < (playerPanelCoordinates[i].length - 1))
                        temp4 = tempy[j] + 1;
                    if (temp5 > 0)
                        temp5 = tempx[j] - 1;
                    if (temp6 > 0)
                        temp6 = tempy[j] - 1;

                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp2][tempy[j]]));
                        System.out.println("removed2 " + temp2 + "" + tempy[j]);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad2");
                    }
                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp5][tempy[j]]));
                        System.out.println("removed3 " + temp5 + "" + tempy[j]);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad3");
                    }
                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[tempx[j]][temp4]));
                        System.out.println("removed4 " + tempx[j] + "" + temp4);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad4");
                    }

                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[tempx[j]][temp6]));
                        System.out.println("removed5 " + tempx[j] + "" + temp6);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad5");
                    }
                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp2][temp4]));
                        System.out.println("removed6 " + temp2 + "" + temp4);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad6");
                    }

                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp5][temp6]));
                        System.out.println("removed7 " + temp5 + "" + temp6);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad7");
                    }

                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp5][temp4]));
                        System.out.println("removed8 " + temp5 + "" + temp4);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad8");
                    }

                    try {
                        coordinatelist.remove(coordinatelist.indexOf(playerPanelCoordinates[temp2][temp6]));
                        System.out.println("removed9 " + temp2 + "" + temp6);
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Redan raderad9");
                    }


                }
            }
               else
                   i--;

        }
    }
}







        //myShips.add(new Ship(4, 'v', 4, 2, playerPanelCoordinates));
        //myShips.add(new Ship(4, 'v', 9, 3, playerPanelCoordinates));
        //myShips.add(new Ship(3, 'h', 3, 9, playerPanelCoordinates));
        //myShips.add(new Ship(3, 'v', 2, 2, playerPanelCoordinates));
        //myShips.add(new Ship(3, 'h', 6, 1, playerPanelCoordinates));
        //myShips.add(new Ship(2, 'h', 6, 3, playerPanelCoordinates));
        //myShips.add(new Ship(2, 'v', 1, 7, playerPanelCoordinates));
        //myShips.add(new Ship(2, 'h', 6, 5, playerPanelCoordinates));
        //myShips.add(new Ship(2, 'h', 7, 9, playerPanelCoordinates));



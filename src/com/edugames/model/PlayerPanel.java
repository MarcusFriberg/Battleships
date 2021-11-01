package com.edugames.model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
* Class PlayerPanel.
* This class creates coordinate-objects and update the graphics in the PlayerPanel.
* - First there is a constructor for the class itself.
* - The first method creates 100 objects of the class coordinate to represent the squares on the Playerpanel.
* - The second method creates 100 images with the information stored in the 100 coordinate objects about imageViewture-adress.
* - The third method creates a gridpanel that stores and shows images.
* @author Linda Djurström
* @version: 1.0.
 */

    public class PlayerPanel
    {
        //Variabels created for y and x positions.
        private char[] xpos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        private char[] ypos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        //Construction of a new two-dimensional array for coordinates.
        Coordinate[][] coordinates = new Coordinate[10][10];

        //New two-dimensional arrays are created for Image and Imageview.
        Image[][] image = new Image[10][10];
        ImageView[][] imageView = new ImageView[10][10];

        //A new Gridpane is created.
        GridPane gridPane = new GridPane();

        /*
         * *@author Linda Djurström
         * An empty constructor for the class PlayerPanel.
         */
        public PlayerPanel() {}

        /*
         * Method createCoordinates().
         * Method createCoordinates() creates 100 coordinate-objects and stores them in a two-dimensional array.
         * @ returns a two-dimensional array with coordinate-objects.
         * @author Linda Djurström
         * @version: 1.0.
         */
        public Coordinate[][] createCoordinates() {

            //An intertwined for-loop to construct 100 objects at once.
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < 10; j++) {

                    //The loop sets the two first char-variabels with the help from the arrays xpos and ypos.
                    coordinates[i][j] = new Coordinate(xpos[i], ypos[j], false, false, "coord-empty-noHit.png");
                }
            }
            return coordinates;
        }

        /*
        * Method createImages().
        * The second method creates images from the information in the coordinate-objects.
        * @params requires an input-parameter in the form of a two-dimensional Coordinate-array.
        * @ returns a two-dimensional Imageview - array.
        * @author Linda Djurström
         * @version: 1.0.
        */
        public ImageView[][] createImages(Coordinate[][] coordinates) throws FileNotFoundException {

            //A for-loop that creats 100 images and imageviews. The loop sets one Image to one Imageview by every turn in the loop.
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < 10; j++) {
                    image[i][j] = new Image(new FileInputStream(coordinates[i][j].getImageFileName()));
                    imageView[i][j] = new ImageView();
                    imageView[i][j].setFitWidth(39);
                    imageView[i][j].setFitHeight(39);
                    imageView[i][j].setImage(image[i][j]);
                }
            }
            return imageView;
        }

        /*
         * Method newGridpane().
         * The third method creates a gridpane with images created in the createImages-method.
         * @params requires an input-parameter in the form of a two-dimensional ImageView-array.
         * @ returns a gridPane.
         * @author Linda Djurström
         * @version: 1.0.
         */

        public GridPane newGridpane(ImageView[][] imageView)
        {
            //The Gridpane is formatted.
            GridPane gridPane = new GridPane();
            gridPane.setMinSize(400, 400);
            gridPane.setVgap(1);
            gridPane.setHgap(1);
            gridPane.setAlignment(Pos.CENTER);

            //A for-loop assigns one position in the grid to one image in the Imageview-array.
            for (int i = 0; i <= 9; i++)
            {
                for (int j = 0; j <= 9; j++)
                {
                    //For every turn in the loop the position in the imageView array increases. It is placed in the
                    //grid by the postions in i and j, which also increases by every turn in the loop.
                    gridPane.add(imageView[i][j], i, j);
                }
            }
            return gridPane;
        }
    }



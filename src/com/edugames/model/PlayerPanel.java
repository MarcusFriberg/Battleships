package com.edugames.model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;

/*
 * Class PlayerPanel.
 * This class creates coordinate-objects and update the graphics in the PlayerPanel.
 * - First there is a constructor for the class itself.
 * - The first method creates 100 objects of the class coordinate to represent the squares on the Playerpanel.
 * - The second method creates a GridPane that creates and draws out the images on the GridPane.
 * @author Linda Djurström
 * @version: 1.0.
 */

public class PlayerPanel {
    // Variabels created for y and x positions.
    private char[] xpos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] ypos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    // Construction of a new two-dimensional array for coordinates.
    Coordinate[][] coordinates = new Coordinate[10][10];
    // A new Gridpane is created.
    GridPane gridPane = new GridPane();

    // Constructor.
    public PlayerPanel() {}

    /*
     * Method initPlayerPanel().
     * Method initPlayerPanel() creates 100 coordinate-objects and stores them in a two-dimensional array.
     * @ returns a two-dimensional array with coordinate-objects.
     * @author Linda Djurström
     * @version: 1.0.
     */
    public Coordinate[][] initPlayerPanel() {
        // An intertwined for-loop to construct 100 objects at once.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // The loop sets the two first char-variabels with the help from the arrays xpos and ypos.
                coordinates[i][j] = new Coordinate(xpos[i], ypos[j], false, false, "coord-empty-noHit.png");
            }
        }
        return coordinates;
    }

    /*
     * Method drawPlayerPanel().
     * This method creates a GridPane with images created from filename-property of the Coordinate-objects.
     * @returns a gridPane.
     * @author Linda Djurström
     * @version: 1.0.
     */
    public GridPane drawPlayerPanel() throws FileNotFoundException {
        // The GridPane is formatted.
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 400);
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        gridPane.setAlignment(Pos.CENTER);

        // A for-loop assigns one position in the grid to one image.
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                // Tell this Coordinate-object to update its image-property depending on its current state.
                coordinates[i][j].changeImage();
                // Create a new imageView to hold the image.
                ImageView imageView = new ImageView();
                imageView.setFitWidth(39);
                imageView.setFitHeight(39);
                // Set the image of the imageview.
                imageView.setImage(new Image(coordinates[i][j].getImageFileName()));
                // Add the imageView to the GridPane-cell.
                gridPane.add(imageView, i, j);
            }
        }
        // --TODO-- Remove testcode below when functions to draw ships are in place
        // Test code starts here
        coordinates[2][2].setHit(true);
        coordinates[7][7].setHasShip(true);
        coordinates[7][8].setHasShip(true);
        coordinates[7][8].setHit(true);
        coordinates[2][2].changeImage();
        coordinates[7][7].changeImage();
        coordinates[7][8].changeImage();
        // Test code ends here
        return gridPane;
    }
}
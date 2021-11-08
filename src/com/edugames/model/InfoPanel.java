package com.edugames.model;

// Imports
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*
 * Class InfoPanel
 * A class to create a HBox with info panel to include in GameView
 * @param: Constructor receives the isServer(Boolean) as parameter.
 * @author: Matilda Wintence
 * @author: matilda.wintence.edu.edugrade.com
 * @version: 2.0
 */
public class InfoPanel {
    // Variables
    private Boolean isServer;
    private String gameModeImage;

    // Constructor
    public InfoPanel() {
        // Empty Constructor
    }

    /*
     * Method init
     * A method to initialize InfoPanel.
     * Creates the HBox and sends it to GameView via GameController.
     * @author: Matilda Wintence
     * @author: matilda.wintence@edu.edugrade.se
     * @version: 1.0
     */
    public void initInfoPanel(Boolean isServer) {
        // Change the labelImage depending on Boolean isServer is true/false
        this.isServer = isServer;
        if (isServer) {
            gameModeImage = "server.png";
        } else {
            gameModeImage = "client.png";
        }
    }

    /*
     * Method drawInfoPanel
     * A method that updates the InfoPanel that displays information about remaining ships and what mode user is playing in.
     * @author: Matilda Wintence
     * @author: matilda.wintenceg@edu.edugrade.se
     * @version: 1.0
     */
    public GridPane drawInfoPanel() {
        // Create new GridPane to contain the entire info panel
        GridPane infoPanelPane = new GridPane();
        // Create new Images and ImageViews for the ships and assign the Image and assign the variables values
        Image airCraftCarrier = new Image("airCraftCarrier.png");
        ImageView airCraftCarrierImageView = new ImageView(airCraftCarrier);
        Image battleShip = new Image("battleship.png");
        ImageView battleShipImageView = new ImageView(battleShip);
        Image cruiser = new Image("cruiser.png");
        ImageView cruiserImageView = new ImageView(cruiser);
        Image submarine = new Image("submarine.png");
        ImageView submarineImageView = new ImageView(submarine);
        // --TODO-- Replace String numbers with a call to AIPlayers method to return number of ships of a given type
        // Create new Labels and assign values to the variables, make a toString to print out the Integer
        Label airCraftCarrierCountLabel = new Label("  x " + "1");
        Label battleshipCountLabel = new Label("  x " + "2");
        Label cruiserCountLabel = new Label("  x " + "3");
        Label submarineCountLabel = new Label("  x " + "4");
        // Set font style and size of the labels
        airCraftCarrierCountLabel.setFont(new Font("Impact", 20));
        battleshipCountLabel.setFont(new Font("Impact", 20));
        cruiserCountLabel.setFont(new Font("Impact", 20));
        submarineCountLabel.setFont(new Font("Impact", 20));
        // Set color and opacity of the labels
        airCraftCarrierCountLabel.setTextFill(Color.web("0b00e3", 1));
        battleshipCountLabel.setTextFill(Color.web("0b00e3", 1));
        cruiserCountLabel.setTextFill(Color.web("0b00e3", 1));
        submarineCountLabel.setTextFill(Color.web("0b00e3", 1));
        // Create new Image "playing as"
        Image playingAs = new Image("playingAs.png");
        // Create new ImageView where playingAs is presented
        ImageView playingAsImageView = new ImageView(playingAs);
        // Create new Image from labelImage that changes depending on what button is pressed in StartView
        Image gameMode = new Image(gameModeImage);
        // Create new ImageView where the imageChoice is presented
        ImageView gameModeImageView = new ImageView(gameMode);
        // Create an Image with an empty space to fill out the infoPanel
        Image space = new Image("space.png");
        // Create new ImageView where space is presented
        ImageView spaceImageView = new ImageView(space);
        // Add the ImageViews to the GridPane and set their position
        infoPanelPane.add(airCraftCarrierImageView, 0, 0);
        infoPanelPane.add(airCraftCarrierCountLabel, 1, 0);
        infoPanelPane.add(battleShipImageView,0,1);
        infoPanelPane.add(battleshipCountLabel, 1,1);
        infoPanelPane.add(cruiserImageView, 2, 0);
        infoPanelPane.add(cruiserCountLabel, 3, 0);
        infoPanelPane.add(submarineImageView, 2, 1);
        infoPanelPane.add(submarineCountLabel, 3, 1);
        infoPanelPane.add(spaceImageView,4,0);
        infoPanelPane.add(playingAsImageView, 5, 0);
        infoPanelPane.add(gameModeImageView,5,1);
        infoPanelPane.setPadding(new Insets(0,0,20,0));
        return infoPanelPane;
    }
}
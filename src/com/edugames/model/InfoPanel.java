package com.edugames.model;

// Imports
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

    //Hej hej hej

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
     * @author: matilda.wintence@edu.edugrade.se
     * @version: 2.0
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
        // Create two labels with empty space to get everything into place
        Label emptySpace1 = new Label("                                                                 ");
        Label emptySpace2 = new Label("                              ");
        // Create Slider and set min, max and starting value
        Slider gameSpeedDelaySlider = new Slider(0, 10, 2);
        // Set preferred size
        gameSpeedDelaySlider.setPrefSize(200,35);
        // Choose to show tick marks and tick labels
        gameSpeedDelaySlider.setShowTickMarks(true);
        gameSpeedDelaySlider.setShowTickLabels(true);
        gameSpeedDelaySlider.showTickLabelsProperty();
        // Set the tick intervalls to 1.0
        gameSpeedDelaySlider.setMajorTickUnit(1.0);
        // Set no minor tick marks
        gameSpeedDelaySlider.setMinorTickCount(0);
        // Make the thumb snap to ticks
        gameSpeedDelaySlider.setSnapToTicks(true);
        // Create new label that shows value of chosen value on slider
        Label gameSpeedDelayLabel = new Label("Fördröjning satt till " + (int)gameSpeedDelaySlider.getValue() + " sekunder");
        // Set the texts font, size, color and opacity
        gameSpeedDelayLabel.setFont(new Font("Impact", 16));
        gameSpeedDelayLabel.setTextFill(Color.web("414242", 0.9));
        // Add an override method that shows changing value on slider
        gameSpeedDelaySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gameSpeedDelayLabel.setText("Fördröjning satt till " + (int)gameSpeedDelaySlider.getValue() + " sekunder");
                // TODO: Inset method here that updates the controller and handles the delay, send with gameSpeedDelaySlider.getValue
            }
        });
        // Add the nodes to the GridPane and set their position
        infoPanelPane.add(airCraftCarrierImageView, 0, 0);
        infoPanelPane.add(airCraftCarrierCountLabel, 1, 0);
        infoPanelPane.add(battleShipImageView,0,1);
        infoPanelPane.add(battleshipCountLabel, 1,1);
        infoPanelPane.add(cruiserImageView, 2, 0);
        infoPanelPane.add(cruiserCountLabel, 3, 0);
        infoPanelPane.add(submarineImageView, 2, 1);
        infoPanelPane.add(submarineCountLabel, 3, 1);
        infoPanelPane.add(emptySpace1, 4, 0);
        infoPanelPane.add(gameSpeedDelayLabel, 5,0);
        infoPanelPane.add(gameSpeedDelaySlider,5,1);
        infoPanelPane.add(emptySpace2,6, 0);
        infoPanelPane.add(playingAsImageView, 7, 0);
        infoPanelPane.add(gameModeImageView,7,1);
        infoPanelPane.setPadding(new Insets(0,0,20,0));
        return infoPanelPane;
    }
}
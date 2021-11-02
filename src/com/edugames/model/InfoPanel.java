package com.edugames.model;

// Imports
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*
 * Class InfoPanel
 * A class to create a HBox with info panel to include in GameView
 * @param: Constructor receives the isServer(Boolean) as parameter.
 * @author: Matilda Wintence
 * @author: matilda.wintence.edu.edugrade.com
 * @version: 1.0
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
            gameModeImage = "labelsever.png";
        } else {
            gameModeImage = "labelclient.png";
        }
    }

    /*
     * Method drawInfoPanel
     * A method that updates the InfoPanel that displays information about remaining ships and what mode user is playing in.
     * @author: Matilda Wintence
     * @author: matilda.wintenceg@edu.edugrade.se
     * @version: 1.0
     */
    public HBox drawInfoPanel() {
        // Create new HBox to contain the entire info panel
        HBox infoPanelBox = new HBox();
        // Create new Images and ImageViews for the ships and assign the Image and assign the variables values
        Image airCraftCarrier = new Image("aircraftcarrier.png");
        ImageView airCraftCarrierImageView = new ImageView(airCraftCarrier);
        Image battleShip = new Image("battleship.png");
        ImageView battleShipImageView = new ImageView(battleShip);
        Image cruiser = new Image("cruiser.png");
        ImageView cruiserImageView = new ImageView(cruiser);
        Image submarine = new Image("submarine.png");
        ImageView submarineImageView = new ImageView(submarine);
        airCraftCarrierImageView.setScaleX(0.5);
        airCraftCarrierImageView.setScaleY(0.5);
        battleShipImageView.setScaleX(0.5);
        battleShipImageView.setScaleY(0.5);
        cruiserImageView.setScaleX(0.5);
        cruiserImageView.setScaleY(0.5);
        submarineImageView.setScaleX(0.5);
        submarineImageView.setScaleY(0.5);
        // --TODO-- Replace String numbers with a call to AIPlayers method to return number of ships of a given type
        // Create new Labels and assign values to the variables, make a toString to print out the Integer
        Label airCraftCarrierCountLabel = new Label("  x " + "1");
        airCraftCarrierCountLabel.setMaxWidth(100);
        Label battleshipCountLabel = new Label("  x " + "2");
        battleshipCountLabel.setMaxWidth(100);
        Label cruiserCountLabel = new Label("  x " + "3");
        cruiserCountLabel.setMaxWidth(100);
        Label submarineCountLabel = new Label("  x " + "4");
        submarineCountLabel.setMaxWidth(100);
        // Set font style and size of the labels
        airCraftCarrierCountLabel.setFont(new Font("Courier New", 20));
        battleshipCountLabel.setFont(new Font("Courier New", 20));
        cruiserCountLabel.setFont(new Font("Courier New", 20));
        submarineCountLabel.setFont(new Font("Courier New", 20));
        // Set color and opacity of the labels
        airCraftCarrierCountLabel.setTextFill(Color.web("4592da", 0.8));
        battleshipCountLabel.setTextFill(Color.web("4592da", 0.8));
        cruiserCountLabel.setTextFill(Color.web("4592da", 0.8));
        submarineCountLabel.setTextFill(Color.web("4592da", 0.8));
        // Create new Image from labelImage that changes depending on what button is pressed in StartView
        Image gameMode = new Image(gameModeImage);
        // Create new ImageView where the imageChoice is presented
        ImageView gameModeImageView = new ImageView(gameMode);
        // Scale the iVew to preferred size in x and y-axis
        gameModeImageView.setScaleX(0.2);
        gameModeImageView.setScaleY(0.2);
        // Create new HBox to hold the ships and counters
        HBox airCraftCarrierWrapper = new HBox();
        HBox battleShipWrapper = new HBox();
        HBox cruiserWrapper = new HBox();
        HBox submarineWrapper = new HBox();
        // Add images of ships and label with counter to the HBoxes
        airCraftCarrierWrapper.getChildren().addAll(airCraftCarrierImageView, airCraftCarrierCountLabel);
        battleShipWrapper.getChildren().addAll(battleShipImageView, battleshipCountLabel);
        cruiserWrapper.getChildren().addAll(cruiserImageView, cruiserCountLabel);
        submarineWrapper.getChildren().addAll(submarineImageView, submarineCountLabel);
        // Create new VBoxes to hold all the HBoxes
        VBox shipsAndCounterFirstWrapper = new VBox();
        VBox shipsAndCounterSecondWrapper = new VBox();
        // Add all the HBoxes containing ships and counters to the VBox
        shipsAndCounterFirstWrapper.getChildren().addAll(airCraftCarrierWrapper, battleShipWrapper);
        shipsAndCounterSecondWrapper.getChildren().addAll(cruiserWrapper, submarineWrapper);
        // Scale it to the preferred size in x and y-axis
        // Add padding to the vBox
        shipsAndCounterFirstWrapper.setPadding(new Insets(0, 0, 0, 0));
        shipsAndCounterSecondWrapper.setPadding(new Insets(0, 0, 0, 0));
        // Add label the VBox containing ships and counter and labelChoice
        infoPanelBox.getChildren().addAll(shipsAndCounterFirstWrapper, shipsAndCounterSecondWrapper, gameModeImageView);
        return infoPanelBox;
    }
}
package com.edugames.model;

// Imports
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
    private String labelImage;

    // Create new HBox to contain the entire info panel
    HBox infoPanelBox = new HBox();


    // Constructor
    public InfoPanel() {}

        /*
         * Method init
         * A method to initialize InfoPanel.
         * Creates the HBox and sends it to GameView via GameController.
         * @author: Matilda Wintence
         * @author: matilda.wintence@edu.edugrade.se
         * @version: 1.0
         */

        public HBox initInfoPanel(Boolean isServer) {
            // Change the labelImage depending on Boolean isServer is true/false
            this.isServer = isServer;
            if (isServer) {
                labelImage = "labelsever.png";
            } else {
                labelImage = "labelclient.png";
            }

            // Create variables of Image, ImageView and Label for InfoPanel "playing as"
            Image imageChoice;
            ImageView iVewChoice;
            Label labelChoice;
            // Create Image variables of the ships
            Image imageAircraftCarrier;
            Image imageCruiser;
            Image imageBattleship;
            Image imageSubmarine;
            // Create ImageView variables for the Images of the ships
            ImageView iVewAircraftCarrier;
            ImageView iVewCruiser;
            ImageView iVewBattleship;
            ImageView iVewSubmarine;
            // Create counter variables for the remaining ships with set starting values
            int counterAircraftCarrier = 1;
            int counterCruiser = 2;
            int counterBattleship = 3;
            int counterSubmarine = 4;
            // Create Labels variables for the "remaining ship" counters
            Label labelAircraftCarrier;
            Label labelCruiser;
            Label labelBattleship;
            Label labelSubmarine;
            // Create new Image from labelImage that changes depending on what button is pressed in StartView
            imageChoice = new Image(labelImage);
            // Create new ImageView where the imageChoice is presented
            iVewChoice = new ImageView(imageChoice);
            // Scale the iVew to preferred size in x and y-axis
            iVewChoice.setScaleX(0.2);
            iVewChoice.setScaleY(0.2);
            // Create new Images and Ivew for the ships and assign the Image and assign the variables values
            imageAircraftCarrier = new Image("aircraftcarrier.png");
            iVewAircraftCarrier = new ImageView(imageAircraftCarrier);
            imageBattleship = new Image("battleship.png");
            iVewBattleship = new ImageView(imageBattleship);
            imageCruiser = new Image("cruiser.png");
            iVewCruiser = new ImageView(imageCruiser);
            imageSubmarine = new Image("submarine.png");
            iVewSubmarine = new ImageView(imageSubmarine);
            // Create new label
            labelChoice = new Label();
            // Set graphic
            labelChoice.setGraphic(iVewChoice);
            // Set the preferred size of the label
            labelChoice.setMinSize(10, 100);
            // Add padding to the label
            labelChoice.setPadding(new Insets(30, 0, 30, 110));

            // Create new Labels and assign values to the variables, make a toString to print out the Integer
            labelAircraftCarrier = new Label("  x " + Integer.toString(counterAircraftCarrier));
            labelBattleship = new Label("  x " + Integer.toString(counterBattleship));
            labelCruiser = new Label("  x " + Integer.toString(counterCruiser));
            labelSubmarine = new Label("  x " + Integer.toString(counterSubmarine));
            // Set font style and size of the labels
            labelAircraftCarrier.setFont(new Font("Courier New", 20));
            labelBattleship.setFont(new Font("Courier New", 20));
            labelCruiser.setFont(new Font("Courier New", 20));
            labelSubmarine.setFont(new Font("Courier New", 20));
            // Set color and opacity of the labels
            labelAircraftCarrier.setTextFill(Color.web("4592da", 0.8));
            labelBattleship.setTextFill(Color.web("4592da", 0.8));
            labelCruiser.setTextFill(Color.web("4592da", 0.8));
            labelSubmarine.setTextFill(Color.web("4592da", 0.8));

            // TODO: I don't know if this is needed, I'll keep it as a comment as I've not been able to try the code in
            //  main
            //BorderPane borderPane = new BorderPane();
            //borderPane.setScaleX(859);
            //borderPane.setScaleY(100);

            // Create new HBox to hold the ships and counters
            HBox hBoxAircraftCarrier = new HBox();
            HBox hBoxBattleship = new HBox();
            HBox hBoxCruiser = new HBox();
            HBox hBoxSubmarine = new HBox();
            // Add images of ships and label with counter to the HBoxes
            hBoxAircraftCarrier.getChildren().addAll(iVewAircraftCarrier, labelAircraftCarrier);
            hBoxBattleship.getChildren().addAll(iVewBattleship, labelBattleship);
            hBoxCruiser.getChildren().addAll(iVewCruiser, labelCruiser);
            hBoxSubmarine.getChildren().addAll(iVewSubmarine, labelSubmarine);
            // Create new VBox to hold all the HBoxes
            VBox vBoxShipsAndCounter = new VBox();
            // Add all the HBoxes containing ships and counters to the VBox
            vBoxShipsAndCounter.getChildren().addAll(hBoxAircraftCarrier, hBoxBattleship, hBoxCruiser, hBoxSubmarine);
            // Scale it to the preferred size in x and y-axis
            vBoxShipsAndCounter.setScaleX(0.2);
            vBoxShipsAndCounter.setScaleY(0.2);
            // Add padding to the vBox
            vBoxShipsAndCounter.setPadding(new Insets(30, 1200, 0, 0));

            // Add label the VBox containing ships and counter and labelChoice
            infoPanelBox.getChildren().addAll(vBoxShipsAndCounter, labelChoice);
            // Scale the HBox to the preferred size in x and y-axis
            infoPanelBox.setScaleX(100);
            infoPanelBox.setScaleY(859);

            return infoPanelBox;
        }

        /*
         * Method updateInfoPanel
         * A method that updates the "shipsSunken".
         * @author: Matilda Wintence
         * @author: matilda.wintenceg@edu.edugrade.se
         * @version: 1.0
         */

        public HBox updateInfoPanel() {
            //TODO: Write a code that makes the ships counter update and send an updated infoPanelBox, I'll do this later when we have a functioning
            // ship class: if shipCruiserSunken then for example: counterCruiser --1;
            return infoPanelBox;
        }

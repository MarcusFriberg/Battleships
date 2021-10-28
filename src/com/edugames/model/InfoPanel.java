/*package com.edugames;

//TODO: Add a label that shows if you're playing as client or server

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InfoPanel {

    Button weStartButton;
    Button enemyStartButton;
    Image imageYouStart;
    Image imageEnemyStart;
    ImageView iVewYouStart;
    ImageView iVewEnemyStart;


    //root.setCenter(weStartButton);

    public void InfoPanel() {
        weStartButton = new Button();
        imageYouStart = new Image(getClass().getResourceAsStream("button1.png"));
        iVewYouStart = new ImageView(imageYouStart);
        weStartButton.setGraphic(iVewYouStart);
        weStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //När vi klickar på knappen så kommer vi att kunna starta spelet och här lägger vi in den funktionen.
            }
        });

        enemyStartButton = new Button();
        imageEnemyStart = new Image(getClass().getResourceAsStream("button.png"));
        iVewEnemyStart = new ImageView(imageEnemyStart);
        weStartButton.setGraphic(iVewEnemyStart);
        enemyStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //När vi klickar på knappen så kommer vi att kunna starta spelet och här lägger vi in den funktionen.
            }
        });
    }
    Button youStartButton;
    Button enemyStartButton;

    Image imageYouStart;
    Image imageEnemyStart;
    Image imageAircraftCarrier;
    Image imageCruiser;
    Image imageBattleship;
    Image imageSubmarine;

    ImageView iVewYouStart;
    ImageView iVewEnemyStart;
    ImageView iVewAircraftCarrier;
    ImageView iVewCruiser;
    ImageView iVewBattleship;
    ImageView iVewSubmarine;

    private int counterAircraftCarrier = 1;
    private int counterCruiser = 2;
    private int counterBattleship = 3;
    private int counterSubmarine = 4;

    private Label labelAircraftCarrier;
    private Label labelCruiser;
    private Label labelBattleship;
    private Label labelSubmarine;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Battleships");

        youStartButton = new Button();
        imageYouStart = new Image(getClass().getResourceAsStream("button.png"));
        iVewYouStart = new ImageView(imageYouStart);
        youStartButton.setGraphic(iVewYouStart);
        youStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //När vi klickar på knappen så kommer vi att kunna starta spelet och här lägger vi in den funktionen.
            }
        });

        enemyStartButton = new Button();
        imageEnemyStart = new Image(getClass().getResourceAsStream("button2.png"));
        iVewEnemyStart = new ImageView(imageEnemyStart);
        enemyStartButton.setGraphic(iVewEnemyStart);
        enemyStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //När vi klickar på knappen så kommer vi att kunna starta spelet och här lägger vi in den funktionen.
                //Vill också att knapparna försvinner då, eller att man iaf inte kan klicka på dem
            }
        });
        imageAircraftCarrier = new Image(getClass().getResourceAsStream("aircraftcarriers.png"));
        iVewAircraftCarrier = new ImageView(imageAircraftCarrier);
        imageBattleship = new Image(getClass().getResourceAsStream("battleship.png"));
        iVewBattleship = new ImageView(imageBattleship);
        imageCruiser = new Image(getClass().getResourceAsStream("cruiser.png"));
        iVewCruiser = new ImageView(imageCruiser);
        imageSubmarine = new Image(getClass().getResourceAsStream("submarine.png"));
        iVewSubmarine = new ImageView(imageSubmarine);


        labelAircraftCarrier = new Label("  x " + Integer.toString(counterAircraftCarrier));
        labelBattleship = new Label("  x " + Integer.toString(counterBattleship));
        labelCruiser = new Label("  x " + Integer.toString(counterCruiser));
        labelSubmarine = new Label("  x " + Integer.toString(counterSubmarine));

        labelAircraftCarrier.setFont(new Font("Courier New", 35));
        labelBattleship.setFont(new Font("Courier New", 35));
        labelCruiser.setFont(new Font("Courier New", 35));
        labelSubmarine.setFont(new Font("Courier New", 35));

        labelAircraftCarrier.setTextFill(Color.web("4592da", 0.8));
        labelBattleship.setTextFill(Color.web("4592da", 0.8));
        labelCruiser.setTextFill(Color.web("4592da", 0.8));
        labelSubmarine.setTextFill(Color.web("4592da", 0.8));


        BorderPane borderPane = new BorderPane();

        HBox hBoxAircraftCarrier = new HBox();
        HBox hBoxBattleship = new HBox();
        HBox hBoxCruiser = new HBox();
        HBox hBoxSubmarine = new HBox();

        hBoxAircraftCarrier.getChildren().addAll(iVewAircraftCarrier, labelAircraftCarrier);
        hBoxBattleship.getChildren().addAll(iVewBattleship, labelBattleship);
        hBoxCruiser.getChildren().addAll(iVewCruiser, labelCruiser);
        hBoxSubmarine.getChildren().addAll(iVewSubmarine, labelSubmarine);


        HBox hBox = new HBox();
        hBox.getChildren().addAll(youStartButton, enemyStartButton);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.TOP_RIGHT);

        VBox vBoxShipsAndCounter = new VBox();
        vBoxShipsAndCounter.getChildren().addAll(hBoxAircraftCarrier, hBoxBattleship, hBoxCruiser, hBoxSubmarine);
        vBoxShipsAndCounter.setAlignment(Pos.BOTTOM_LEFT);


        borderPane.setCenter(hBox);
        borderPane.setBottom(vBoxShipsAndCounter);
        Scene scene = new Scene(borderPane, 4000,4000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;

}*/

package com.edugames.controller;

import com.edugames.model.Coordinate;
import com.edugames.model.GamePanel;
import com.edugames.view.GameView;
import javafx.stage.Stage;

public class GameController {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;
    private GameView gameView;
    private Coordinate[][] playerPanelCoordinates;
    private Coordinate[][] enemyPanelCoordinates;

    // Constructor
    public GameController(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
        initGameSession();
        initGameView();

    }


    //Create methods to communicate with GameSession to shoot or wait
    //Create methods to update models on action
    //Create methods to update graphics on action



    // Initial Setup of a new game that happens when player selects to start in either server or client mode

    public void initGameSession() {
        // Code to init a new GameSession
    }

    public void initGameView() {
        gameView = new GameView(primaryStage, isServer);
        playerPanelCoordinates = gameView.initPlayerPanel();
        enemyPanelCoordinates = gameView.initEnemyPanel();
        gameView.present();
    }
}

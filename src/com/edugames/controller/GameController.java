package com.edugames.controller;

import com.edugames.view.GameView;
import javafx.stage.Stage;

public class GameController {
    // Variables
    private Stage primaryStage;
    private Boolean isServer;
    private GameView gameView;


    // Constructor
    public GameController(Stage primaryStage, Boolean isServer) {
        this.primaryStage = primaryStage;
        this.isServer = isServer;
        initGameSession();
        initPlayerPanel();
        initEnemyPanel();
        initInfoPanel();
        initGameView();

    }


    //Create methods to communicate with GameSession to shoot or wait
    //Create methods to update models on action
    //Create methods to update graphics on action



    // Initial Setup of a new game that happens when player selects to start in either server or client mode

    public void initGameSession() {
        // Code to init a new GameSession
    }

    public void initPlayerPanel() {
        // Code to init a new PlayerPanel
    }

    public void initEnemyPanel() {
        // Code to init a new EnemyPanel
    }

    public void initInfoPanel() {
        // Code to init a new InfoPanel
    }

    public void initGameView() {
        gameView = new GameView(primaryStage, isServer);
        gameView.present();
    }
}

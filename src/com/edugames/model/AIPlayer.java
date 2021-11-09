package com.edugames.model;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer
{
    Coordinate[][] playerPanelCoordinates;
    private List<Ship> myShips = new ArrayList<>();

    public AIPlayer(Coordinate[][] coordinates){
        this.playerPanelCoordinates = coordinates;

        myShips.add(new Ship(5, 'h', 3, 7, playerPanelCoordinates));
        myShips.add(new Ship(4, 'v', 4, 2, playerPanelCoordinates));
        myShips.add(new Ship(4, 'v', 9, 3, playerPanelCoordinates));
        myShips.add(new Ship(3, 'h', 3, 9, playerPanelCoordinates));
        myShips.add(new Ship(3, 'v', 2, 2, playerPanelCoordinates));
        myShips.add(new Ship(3, 'h', 6, 1, playerPanelCoordinates));
        myShips.add(new Ship(2, 'h', 6, 3, playerPanelCoordinates));
        myShips.add(new Ship(2, 'v', 1, 7, playerPanelCoordinates));
        myShips.add(new Ship(2, 'h', 6, 5, playerPanelCoordinates));
        myShips.add(new Ship(2, 'h', 7, 9, playerPanelCoordinates));
        }
}

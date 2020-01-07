package com.codecool.quest.logic;

import com.codecool.quest.Main;

public class GameLoop {

    private GameMap map;
    private int mapWidth;
    private int mapHeight;
    private int[][] neighborsAmount;
    private boolean[][] isAlive;

    public GameLoop(GameMap map) {
        this.map = map;
        mapWidth = map.getWidth();
        mapHeight = map.getHeight();
        neighborsAmount = new int[mapWidth][mapHeight];
        isAlive = new boolean[mapWidth][mapHeight];
    }

    public void playRound() {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                Cell cell = map.getCell(x, y);

                if (cell.getItem().isAlive()) {
                    neighborsAmount[x][y] = calculateNeighbors(x, y);

                    if (neighborsAmount[x][y] == 2 || neighborsAmount[x][y] == 3) {
                        isAlive[x][y] = true;
                    }
                    else {
                        isAlive[x][y] = false;
                    }
                }
                else if (!cell.getItem().isAlive() && x >= 1 && x <= mapWidth - 2 && y >= 1 && y <= mapHeight - 2) {
                    neighborsAmount[x][y] = calculateNeighbors(x, y);

                    if (neighborsAmount[x][y] == 3) {
                        isAlive[x][y] = true;
                    }
                }
            }
        }
        updateCellArray(isAlive);
        Main.refresh();
    }

    private void updateCellArray(boolean[][] isAlive) {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {

                if (isAlive[x][y]) {
                    map.getCell(x, y).getItem().setAlive(true);
                }
                else {
                    map.getCell(x, y).getItem().setAlive(false);
                }
            }
        }
    }

    private int calculateNeighbors(int x, int y) {
        int aliveNeighborCounter = 0;

        // right neighbor
        if (map.getCell(x + 1, y).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // left neighbor
        if (map.getCell(x - 1, y).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // up neighbor
        if (map.getCell(x, y - 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // down neighbor
        if (map.getCell(x, y + 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // down-right neighbor
        if (map.getCell(x + 1, y + 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // up-right neighbor
        if (map.getCell(x + 1, y - 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // up-left neighbor
        if (map.getCell(x - 1, y - 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }

        // down-left neighbor
        if (map.getCell(x - 1, y + 1).getItem().isAlive()) {
            aliveNeighborCounter++;
        }
        return aliveNeighborCounter;
    }
}

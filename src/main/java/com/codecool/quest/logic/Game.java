package com.codecool.quest.logic;

import static java.lang.Math.round;

public class Game {
    private GameMap map;
    private GameTimer gameTimer;
    private double timeToChange;

    public Game(GameMap map) {
        this.map = map;
    }

    public void init(double timeToChange) {
        this.timeToChange = round(timeToChange * 100.0) / 100.0;
        GameLoop gameLoop = new GameLoop(map);
        gameTimer = new GameTimer(timeToChange);
        gameTimer.setup(gameLoop::playRound);
        gameTimer.play();
    }

    public void pause() {
        gameTimer.pause();
    }

    public void continueGame() {
        gameTimer.play();
    }

    public void stop() {
        gameTimer.stop();
    }

    public double getTimeToChange() {
        return timeToChange;
    }
}

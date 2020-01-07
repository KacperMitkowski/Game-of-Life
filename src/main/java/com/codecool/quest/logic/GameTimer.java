package com.codecool.quest.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class GameTimer {
    private static final double gameSpeed = 1;
    private double frameTime;
    private Timeline timer = new Timeline();

    GameTimer() {
        this(gameSpeed);
    }

    GameTimer(double frameTime) {
        this.frameTime = frameTime;
    }

    public void setup(Runnable loopMethod) {
        timer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime),
                ae -> loopMethod.run());

        timer.getKeyFrames().add( kf );
    }

    public void play() {
        timer.play();
    }

    public void pause() {
        timer.pause();
    }

    public void stop() {
        timer.stop();
    }
}


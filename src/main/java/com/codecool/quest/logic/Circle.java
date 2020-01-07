package com.codecool.quest.logic;

public class Circle extends Item {

    private boolean isAlive;

    public Circle(Cell cell, boolean isAlive) {
        super(cell);
        this.isAlive = isAlive;
    }

    @Override
    public String getTileName() {
        return "circle";
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

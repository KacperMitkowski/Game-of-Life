package com.codecool.quest.logic;

abstract public class Item implements Drawable {

    public Item(Cell cell) {
        cell.setItem(this);
    }

    abstract public boolean isAlive();

    abstract public void setAlive(boolean alive);
}

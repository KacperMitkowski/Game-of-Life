package com.codecool.quest.logic;

public enum CellType {
    FLOOR("floor");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

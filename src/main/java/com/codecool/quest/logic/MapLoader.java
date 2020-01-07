package com.codecool.quest.logic;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case '.':
                            cell.setType(CellType.FLOOR);
                            new Circle(cell, false);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Circle(cell, true);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}

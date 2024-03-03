package ru.pas_zhukov.models;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Field extends Dimension implements Serializable {
    private final Set<Coordinates> walls = new HashSet<>();
    public Field(int width, int height) {
        super(width, height);
        initBasicWalls();
    }

    private void initBasicWalls() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (column == 0 | row == 0) {
                    walls.add(new Coordinates(row, column));
                } else if (column == height - 1 | row == width - 1) {
                    walls.add(new Coordinates(row, column));
                }
            }
        }
    }

    public Dimension getOptimalWindowSize(int tileSize) {
        return new Dimension((width + 1) * tileSize, (height + 2) * tileSize);
    }

    public Set<Coordinates> getWalls() {
        return walls;
    }

    public void addWall(Coordinates wallCoordinates) {
        walls.add(wallCoordinates);
    }

    public void removeWall(Coordinates wallCoordinates) {
        walls.remove(wallCoordinates);
    }
}

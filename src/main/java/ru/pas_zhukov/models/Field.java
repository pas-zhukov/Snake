package ru.pas_zhukov.models;

import java.awt.*;

public class Field extends Dimension {
    public Field(int width, int height) {
        super(width, height);
    }

    public Dimension getOptimalWindowSize(int tileSize) {
        return new Dimension(width * tileSize, height * tileSize);
    }
}

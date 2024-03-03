package ru.pas_zhukov.models;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse {
    private Coordinates coordinates;

    public Mouse() {
        this.coordinates = new Coordinates(0, 0);
    }
    public Mouse(Coordinates coords) {
        this.coordinates = coords;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getX() {
        return coordinates.getX();
    }
    public int getY() {
        return coordinates.getY();
    }


}

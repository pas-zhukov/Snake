package ru.pas_zhukov.models;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private int x;
    private int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void increaseY() {
        this.y += 1;
    }
    public void decreaseY() {
        this.y -= 1;
    }
    public void increaseX() {
        this.x += 1;
    }
    public void decreaseX() {
        this.x -= 1;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinates() {
        x = 0;
        y = 0;
    }
    public void setNewCoords(Coordinates newCoords) {
        this.x = newCoords.getX();
        this.y = newCoords.getY();
    }

    public Coordinates getCopy() {
        return new Coordinates(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}

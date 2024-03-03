package ru.pas_zhukov.models;

import java.util.ArrayList;
import java.util.List;

public class Snake{
    private List<Coordinates> coordinates;
    private Direction movingDirection;
    private Coordinates hiddenTail;
    public Snake(Field field) {
        movingDirection = Direction.UP;
        coordinates = new ArrayList<>();
        coordinates.add(new Coordinates((int)field.getWidth() / 2, (int) field.getHeight() / 2));
        coordinates.add(new Coordinates((int)field.getWidth() / 2, ((int) field.getHeight() / 2) + 1));
        coordinates.add(new Coordinates((int)field.getWidth() / 2, ((int) field.getHeight() / 2) + 2));
        hiddenTail = coordinates.get(coordinates.size() - 1).getCopy();
    }
    public List<Coordinates> getCoordinates() {
        return coordinates;
    }
    public Coordinates getHeadCoordinates() {
        return coordinates.get(0);
    }
    public void changeDirection(Direction direction) {
        movingDirection = direction;
    }

    public void move() {
        hiddenTail.setNewCoords(coordinates.get(coordinates.size() - 1));
        for (int i = getSize() - 1; i > 0; i--) {
            coordinates.get(i).setNewCoords(coordinates.get(i - 1));
        }
        moveHead();
    }
    public void moveHead() {
        if (movingDirection == Direction.UP) {
            coordinates.get(0).decreaseY();
        } else if (movingDirection == Direction.DOWN) {
            coordinates.get(0).increaseY();
        }
        else if (movingDirection == Direction.LEFT) {
            coordinates.get(0).decreaseX();
        }
        else if (movingDirection == Direction.RIGHT) {
            coordinates.get(0).increaseX();
        }
    }

    public Direction getDirection() {
        return movingDirection;
    }

    public void grow() {
        coordinates.add(hiddenTail.getCopy());
    }

    public int getSize() {
        return coordinates.size();
    }
}

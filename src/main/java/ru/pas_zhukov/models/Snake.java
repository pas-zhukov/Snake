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
    public void changeDirection(Direction direction) {
        movingDirection = direction;
    }

    public void move() {
        hiddenTail = coordinates.get(coordinates.size() - 1).getCopy();
        for (int i = getSize() - 1; i > 0; i--) {
            coordinates.set(i, coordinates.get(i - 1).getCopy());
        }

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
        coordinates.add(new Coordinates(hiddenTail.getX(), hiddenTail.getY()));
    }

    public int getSize() {
        return coordinates.size();
    }
}

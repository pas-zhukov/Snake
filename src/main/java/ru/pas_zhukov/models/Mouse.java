package ru.pas_zhukov.models;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse {
    private Coordinates coordinates;
    public Mouse(Field field) {
        coordinates = getRandomPosition(field);
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void randomReplace(Field field, Snake snake) {
        boolean mousePositionFlag;
        Coordinates randCoords;
        do {
            mousePositionFlag = true;
            randCoords = getRandomPosition(field);
            for (Coordinates coords : snake.getCoordinates()) {
                if (randCoords.equals(coords)) mousePositionFlag = false;
            }
        } while (!mousePositionFlag);
        coordinates = randCoords;
    }
    public int getX() {
        return coordinates.getX();
    }
    public int getY() {
        return coordinates.getY();
    }
    private Coordinates getRandomPosition(Field field) {
        int randX = ThreadLocalRandom.current().nextInt(1, field.width - 1);
        int randY = ThreadLocalRandom.current().nextInt(1, field.height - 1);
        return new Coordinates(randX, randY);
    }
}

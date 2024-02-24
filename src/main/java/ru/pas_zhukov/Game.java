package ru.pas_zhukov;

import ru.pas_zhukov.models.Coordinates;
import ru.pas_zhukov.models.Field;
import ru.pas_zhukov.models.Mouse;
import ru.pas_zhukov.models.Snake;
import ru.pas_zhukov.views.GameOverWindow;
import ru.pas_zhukov.views.GameWindow;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends Thread implements Runnable {
    private final GameWindow gameWindow;
    private final Field field;
    private final Snake snake;
    private final Mouse mouse;
    private int score = 0;

    public Game() {
        field = new Field(20, 20);
        snake = new Snake(field);
        mouse = new Mouse(getRandomPosition());
        gameWindow = new GameWindow(field, snake, mouse);
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            snake.move();
            checkGameStatus();
            gameWindow.refreshWindow();
        }
    }

    private void checkGameStatus() {
        List<Coordinates> snakeCoords = snake.getCoordinates();
        // проверяем не съедена ли мышка
        if (snakeCoords.get(0).equals(mouse.getCoordinates())) {
            snake.grow();
            respawnMouse();
            score++;
            return;
        }

        boolean eatYourself = false;
        // проверяем не врезались ли в себя
        for (int i = 1; i < snakeCoords.size(); i++) {
            if (snakeCoords.get(0).equals(snakeCoords.get(i))) eatYourself = true;
        }

        boolean wallTouched = isWallTouched(snakeCoords);
        if (eatYourself | wallTouched) {
            new GameOverWindow(gameWindow, score, this);
        }


    }

    private boolean isWallTouched(List<Coordinates> snakeCoords) {
        int width = (int) field.getWidth();
        int height = (int) field.getHeight();

        for (int row = 0; row < height; row ++) {
            for (int column = 0; column < width; column++) {
                if (column == 0 | row == 0) {
                    if (snakeCoords.get(0).equals(new Coordinates(row, column))) return true;
                } else if (column == height - 1 | row == width - 1) {
                    if (snakeCoords.get(0).equals(new Coordinates(row, column))) return true;
                }
            }
        }
        return false;
    }

    public void respawnMouse() {
        boolean mousePositionFlag;
        Coordinates randCoords;
        do {
            mousePositionFlag = true;
            randCoords = getRandomPosition();
            for (Coordinates coords : snake.getCoordinates()) {
                if (randCoords.equals(coords)) mousePositionFlag = false;
            }
        } while (!mousePositionFlag);
        mouse.setCoordinates(randCoords);
    }

    private Coordinates getRandomPosition() {
        int randX = ThreadLocalRandom.current().nextInt(1, field.width - 1);
        int randY = ThreadLocalRandom.current().nextInt(1, field.height - 1);
        return new Coordinates(randX, randY);
    }
}

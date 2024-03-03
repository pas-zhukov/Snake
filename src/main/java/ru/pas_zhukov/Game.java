package ru.pas_zhukov;

import ru.pas_zhukov.exceptions.GameOverException;
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
        while (!interrupted()) {
            try {
                sleep(100);
                snake.move();
                checkGameStatus();
                gameWindow.refreshWindow();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (GameOverException e) {
                break;
            }
        }
    }

    private void checkGameStatus() throws GameOverException {
        if (isMouseEaten()) {
            snake.grow();
            respawnMouse();
            score++;
        }
        if (isEatenByYourself() | isWallTouched()) {
            new GameOverWindow(gameWindow, score, this);
            throw new GameOverException();
        }
    }

    private boolean isMouseEaten() {
        return snake.getHeadCoordinates().equals(mouse.getCoordinates());
    }

    private boolean isWallTouched() {
        return field.getWalls().contains(snake.getHeadCoordinates());
    }

    private boolean isEatenByYourself() {
        List<Coordinates> snakeCoords = snake.getCoordinates();
        Coordinates headCoordinates = snake.getHeadCoordinates();
        for (int i = 1; i < snakeCoords.size(); i++) {
            if (snakeCoords.get(i).equals(headCoordinates)) return true;
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

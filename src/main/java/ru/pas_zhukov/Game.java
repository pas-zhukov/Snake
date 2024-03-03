package ru.pas_zhukov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pas_zhukov.exceptions.GameOverException;
import ru.pas_zhukov.models.Coordinates;
import ru.pas_zhukov.models.Field;
import ru.pas_zhukov.models.Mouse;
import ru.pas_zhukov.models.Snake;
import ru.pas_zhukov.services.ScoreFileService;
import ru.pas_zhukov.views.GameOverWindow;
import ru.pas_zhukov.views.GameWindow;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends Thread implements Runnable {
    public static Logger logger = LoggerFactory.getLogger(Game.class);
    private final GameWindow gameWindow;
    private Field field;
    private final Snake snake;
    private final Mouse mouse;
    private int score = 0;

    public Game() {
        this(new Field(20, 20));
    }

    public Game(Field field) {
        this.field = field;
        snake = new Snake(field);
        mouse = new Mouse();
        respawnMouse();
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
            new GameOverWindow(this, gameWindow, score, this);
            try {
                if (score > ScoreFileService.getMaxScore()) ScoreFileService.saveMaxScore(score);
            } catch (IOException e) {
                logger.warn("Could not save max score to file");
            }
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
                if (randCoords.equals(coords)) {
                    mousePositionFlag = false;
                    break;
                }
            }
            for (Coordinates coords : field.getWalls()) {
                if (randCoords.equals(coords)) {
                    mousePositionFlag = false;
                    break;
                }
            }
        } while (!mousePositionFlag);
        mouse.setCoordinates(randCoords);
    }

    private Coordinates getRandomPosition() {
        int randX = ThreadLocalRandom.current().nextInt(1, field.width - 1);
        int randY = ThreadLocalRandom.current().nextInt(1, field.height - 1);
        return new Coordinates(randX, randY);
    }

    public Field getField() {
        return field;
    }
}

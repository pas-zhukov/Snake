package ru.pas_zhukov.controllers;

import ru.pas_zhukov.models.Direction;
import ru.pas_zhukov.models.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyListener extends KeyAdapter {
    private final Snake snake;

    public SnakeKeyListener(Snake snake) {
        super();
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction directionToBeUsed = null;
        if (e.getKeyCode()==KeyEvent.VK_W) directionToBeUsed = Direction.UP;
        else if (e.getKeyCode()==KeyEvent.VK_S) directionToBeUsed = Direction.DOWN;
        else if (e.getKeyCode()==KeyEvent.VK_A) directionToBeUsed = Direction.LEFT;
        else if (e.getKeyCode()==KeyEvent.VK_D) directionToBeUsed = Direction.RIGHT;
        else return;

        if (snake.getDirection() == Direction.UP & directionToBeUsed == Direction.DOWN) {
            directionToBeUsed = Direction.UP;
        }
        else if (snake.getDirection() == Direction.DOWN & directionToBeUsed == Direction.UP) {
            directionToBeUsed = Direction.DOWN;
        }
        else if (snake.getDirection() == Direction.RIGHT & directionToBeUsed == Direction.LEFT) {
            directionToBeUsed = Direction.RIGHT;
        }
        else if (snake.getDirection() == Direction.LEFT & directionToBeUsed == Direction.RIGHT) {
            directionToBeUsed = Direction.LEFT;
        }
        snake.changeDirection(directionToBeUsed);
    }
}

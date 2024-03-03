package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Coordinates;
import ru.pas_zhukov.models.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SnakeView {
    private JPanel gamePanel;
    private Snake snake;
    private final JLabel snakeHead = new JLabel(new ImageIcon(Objects.requireNonNull(SnakeView.class.getResource("/snake-head.png"))));
    private final ImageIcon snakeTailImageIcon = new ImageIcon(Objects.requireNonNull(SnakeView.class.getResource("/snake-tail.png")));
    private List<JLabel> snakeTail = new ArrayList<>();
    public SnakeView(JPanel gamePanel, Snake snake) {
        this.gamePanel = gamePanel;
        this.snake = snake;
    }

    public void drawSnake() {
        if (snake.getSize() > snakeTail.size()) {
            while (snake.getSize() > snakeTail.size()) {
                snakeTail.add(new JLabel(snakeTailImageIcon));
            }
        }
        List<Coordinates> snakeCoords = snake.getCoordinates();
        List<GridBagConstraints> snakeConstraints = snakeCoords.stream().map((s) -> new GameGridConstraints(s.getX(), s.getY())).collect(Collectors.toList());
        for (int i = 1; i < snake.getSize(); i++) {
            gamePanel.add(snakeTail.get(i), snakeConstraints.get(i));
        }
        //snakeConstraints.forEach((s) -> gamePanel.add(new JLabel(snakeTailImageIcon), s));
        gamePanel.add(snakeHead, snakeConstraints.get(0));
    }

}

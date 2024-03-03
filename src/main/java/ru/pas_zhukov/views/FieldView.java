package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Coordinates;
import ru.pas_zhukov.models.Field;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FieldView {
    private final ImageIcon wallPartImageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/wall.png")));

    private final JPanel gamePanel;
    private final Field field;

    public FieldView(JPanel gamePanel, Field field) {
        this.gamePanel = gamePanel;
        this.field = field;
        drawWalls();
    }

    public void drawWalls() {
        for (Coordinates coordinates : field.getWalls()) {
            drawWall(coordinates);
        }
    }

    public void drawWall(Coordinates coordinates) {
        GridBagConstraints constraints = new GameGridConstraints(coordinates);
        JLabel wallPart = new JLabel(wallPartImageIcon);
        gamePanel.add(wallPart, constraints);
    }
}

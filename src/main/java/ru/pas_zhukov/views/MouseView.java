package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MouseView {
    private JPanel gamePanel;
    private Mouse mouse;
    private final JLabel mouseLabel = new JLabel(new ImageIcon(Objects.requireNonNull(SnakeView.class.getResource("/mouse.png"))));

    public MouseView(JPanel gamePanel, Mouse mouse) {
        this.gamePanel = gamePanel;
        this.mouse = mouse;
    }
    public void drawMouse() {
        GridBagConstraints constraints = new CustomGridConstraints(mouse.getX(), mouse.getY());
        gamePanel.add(mouseLabel, constraints);
    }
}

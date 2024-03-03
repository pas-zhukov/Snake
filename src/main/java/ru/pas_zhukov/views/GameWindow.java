package ru.pas_zhukov.views;

import ru.pas_zhukov.controllers.SnakeKeyListener;
import ru.pas_zhukov.models.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameWindow extends JFrame {
    private static final int tileSize = 32;
    private final FieldView fieldView;
    private final SnakeView snakeView;
    private final MouseView mouseView;
    private final JPanel mainPanel = new JPanel();
    public GameWindow(Field field, Snake snake, Mouse mouse) {
        super("Игра - Змейка");
        initFrame(field);
        this.fieldView = new FieldView(mainPanel, field);
        this.snakeView = new SnakeView(mainPanel, snake);
        this.mouseView = new MouseView(mainPanel, mouse);
        addKeyListener(new SnakeKeyListener(snake));
        fieldView.drawWalls();
        this.setVisible(true);
    }

    private void initFrame(Field field) {
        this.setResizable(false);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/python.png"))).getImage());
        this.setSize(field.getOptimalWindowSize(tileSize));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);
    }

    public void refreshWindow() {
        snakeView.drawSnake();
        mouseView.drawMouse();
        this.revalidate();
    }

}

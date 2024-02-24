package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Direction;
import ru.pas_zhukov.models.Field;
import ru.pas_zhukov.models.Mouse;
import ru.pas_zhukov.models.Snake;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class GameWindow extends JFrame {
    private static int tileSize = 32;
    private SnakeView snakeView;
    private MouseView mouseView;
    private final ImageIcon wallPartImageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/wall.png")));
    private final JPanel mainPanel = new JPanel();
    public GameWindow(Field field, Snake snake, Mouse mouse) {
        super("Игра - Змейка");

        // инициализация окна
        this.setResizable(false);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/python.png"))).getImage());
        this.setSize(field.getOptimalWindowSize(tileSize));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);

        this.snakeView = new SnakeView(mainPanel, snake);
        this.mouseView = new MouseView(mainPanel, mouse);

        // рисуем стены
        this.drawWalls(field);

        // контроллер...
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction directionToBeUsed = null;
                if (e.getKeyCode()==KeyEvent.VK_W) directionToBeUsed = Direction.UP;
                if (e.getKeyCode()==KeyEvent.VK_S) directionToBeUsed = Direction.DOWN;
                if (e.getKeyCode()==KeyEvent.VK_A) directionToBeUsed = Direction.LEFT;
                if (e.getKeyCode()==KeyEvent.VK_D) directionToBeUsed = Direction.RIGHT;

                if (snake.getDirection() == Direction.UP & directionToBeUsed == Direction.DOWN) {
                    directionToBeUsed = Direction.UP;
                }
                if (snake.getDirection() == Direction.DOWN & directionToBeUsed == Direction.UP) {
                    directionToBeUsed = Direction.DOWN;
                }
                if (snake.getDirection() == Direction.RIGHT & directionToBeUsed == Direction.LEFT) {
                    directionToBeUsed = Direction.RIGHT;
                }
                if (snake.getDirection() == Direction.LEFT & directionToBeUsed == Direction.RIGHT) {
                    directionToBeUsed = Direction.LEFT;
                }
                snake.changeDirection(directionToBeUsed);
            }
        });

        this.setVisible(true);
    }

    public void drawWalls(Field field) {
        int width = (int) field.getWidth();
        int height = (int) field.getHeight();

        for (int row = 0; row < height; row ++) {
            for (int column = 0; column < width; column++) {
                if (column == 0 | row == 0) {
                    drawWall(row, column);
                } else if (column == height - 1 | row == width - 1) {
                    drawWall(row, column);
                }
            }
        }
    }

    public void drawWall(int x, int y) {
        GridBagConstraints constraints = new CustomGridConstraints(x, y);
        JLabel wallPart = new JLabel(wallPartImageIcon);
        mainPanel.add(wallPart, constraints);
    }

    public void refreshWindow() {
        snakeView.drawSnake();
        mouseView.drawMouse();
        this.revalidate();
    }

}

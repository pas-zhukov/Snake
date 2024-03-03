package ru.pas_zhukov.views;

import ru.pas_zhukov.Game;
import ru.pas_zhukov.models.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameOverWindow extends JDialog {
    public GameOverWindow(Game game, GameWindow frame, int score, Thread thread) {
        super(frame);
        this.setTitle("Конец игры :(");
        this.setSize(220, 100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setLayout(new FlowLayout());
        this.setModal(true);
        this.add(new JLabel("Конец игры! Твой счёт: " + score));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JButton button = new JButton("Закрыть");
        JButton newGameBtn = new JButton("Новая игра");
        button.addActionListener(e -> {
            Field usedField = (Field) game.getField().clone();
            frame.dispose();
            this.dispose();
            thread.interrupt();
            new MainWindow();
        });
        newGameBtn.addActionListener(e -> {
            Field usedField = (Field) game.getField().clone();
            frame.dispose();
            this.dispose();
            thread.interrupt();
            new Game(usedField).start();
        });
        this.add(button);
        this.add(newGameBtn);
        this.setVisible(true);
    }
}

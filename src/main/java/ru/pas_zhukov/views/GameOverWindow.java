package ru.pas_zhukov.views;

import ru.pas_zhukov.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JDialog {
    public GameOverWindow(JFrame frame, int score, Thread thread) {
        super(frame);
        this.setSize(200, 100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setLayout(new FlowLayout());
        this.setModal(true);
        this.add(new JLabel("Конец игры! Твой счёт: " + score));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JButton button = new JButton("Выход");
        JButton newGameBtn = new JButton("Новая игра");
        button.addActionListener(e -> System.exit(0));
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GameOverWindow.super.dispose();
                thread.interrupt();
                new Game().start();
            }
        });
        this.add(button);
        this.add(newGameBtn);
        this.setVisible(true);
    }
}

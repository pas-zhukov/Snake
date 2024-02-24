package ru.pas_zhukov.views;

import javax.swing.*;
import java.awt.*;

public class GameOverWindow extends JDialog {
    public GameOverWindow(JFrame frame, int score) {
        super(frame);
        this.setSize(200, 100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setLayout(new FlowLayout());
        this.setModal(true);
        this.add(new JLabel("Конец игры! Твой счёт: " + score));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JButton button = new JButton("Ок");
        button.addActionListener(e -> System.exit(0));
        this.add(button);
        this.setVisible(true);
    }
}

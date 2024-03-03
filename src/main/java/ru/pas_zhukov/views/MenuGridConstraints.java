package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Coordinates;

import java.awt.*;

public class MenuGridConstraints extends GridBagConstraints {
    public MenuGridConstraints(int x_position, int y_position) {
        super();
        this.anchor = CENTER;
        this.gridx = x_position;
        this.gridy = y_position;
        this.weightx = 1;
        this.weighty = 1;
        this.gridwidth = 1;
        this.gridheight = 1;
        this.insets = new Insets(5, 10, 5, 10);
    }
}

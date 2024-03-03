package ru.pas_zhukov.views;

import ru.pas_zhukov.models.Coordinates;

import java.awt.*;

public class CustomGridConstraints extends GridBagConstraints {
    public CustomGridConstraints(int x_position, int y_position) {
        super();
        this.anchor = CENTER;
        this.gridx = x_position;
        this.gridy = y_position;
        this.weightx = 0;
        this.weighty = 0;
        this.gridwidth = 1;
        this.gridheight = 1;
        this.insets = new Insets(0, 0, 0, 0);
    }

    public CustomGridConstraints(Coordinates coordinates) {
        this(coordinates.getX(), coordinates.getY());
    }
}

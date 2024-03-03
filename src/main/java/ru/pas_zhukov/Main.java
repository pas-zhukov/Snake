package ru.pas_zhukov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.pas_zhukov.views.MainWindow;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        try {
            MainWindow mainWindow = new MainWindow();
        }
        catch (Exception ex) {
            logger.error("Фатальная ошибка, завершение программы...", ex);
            System.out.println(ex.getMessage());
        }
    }
}
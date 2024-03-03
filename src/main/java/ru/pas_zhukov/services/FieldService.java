package ru.pas_zhukov.services;

import ru.pas_zhukov.models.Coordinates;
import ru.pas_zhukov.models.Field;

import java.io.*;

public class FieldService {

    public static void main(String[] args) {
        Field field = new Field(24, 24);
        field.addWall(new Coordinates(10, 10));
        field.addWall(new Coordinates(10, 11));
        field.addWall(new Coordinates(10, 12));
        field.addWall(new Coordinates(10, 13));
        field.addWall(new Coordinates(10, 14));
        field.addWall(new Coordinates(10, 15));
        field.addWall(new Coordinates(10, 16));
        field.addWall(new Coordinates(10, 17));

        try {
            new FieldService(field).saveAsFile(new File("./testCustomMap.bmap"));
        } catch (IOException ignored) {
            System.out.println("Не удалось создать тестовую карту");
        }
    }

    private Field field;

    public FieldService(Field field) {
        this.field = field;
    }

    public static Field loadFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Field) objectInputStream.readObject();
    }

    public void saveAsFile(File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(field);

        objectOutputStream.close();
    }
}

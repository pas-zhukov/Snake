package ru.pas_zhukov.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pas_zhukov.Game;
import ru.pas_zhukov.models.Field;
import ru.pas_zhukov.services.FieldService;
import ru.pas_zhukov.services.ScoreFileService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainWindow extends JFrame {
    public static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    private JLabel maxScoreLabel;
    private JTextField infoPanel;
    private Field field = null;
    private final JPanel mainPanel = new JPanel();
    public MainWindow() {
        super("Змейка - главное меню");
        initFrame();
        initMaxScoreLabel();
        initInfoPanel();
        initButtons();
        initMenuPanel();
        this.setVisible(true);
    }

    private void initFrame() {
        this.setResizable(false);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/python.png"))).getImage());
        this.setSize(350, 350);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);
    }

    private void initMaxScoreLabel() {
        maxScoreLabel = new JLabel("Максимальный счет: " + ScoreFileService.getMaxScore());
        GridBagConstraints constraints = new MenuGridConstraints(0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(maxScoreLabel, constraints);
    }

    private void initInfoPanel() {
        infoPanel = new JTextField("Используется карта по умолчанию.");
        infoPanel.setEditable(false);
        GridBagConstraints constraints = new MenuGridConstraints(0, 1);
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(infoPanel), constraints);
    }

    private void initButtons() {
        JButton startNewGame = new JButton("Играть");
        GridBagConstraints newGameConstraints = new MenuGridConstraints(0, 2);
        newGameConstraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(startNewGame, newGameConstraints);
        startNewGame.addActionListener(e -> {
            if (field != null) {
                new Game(field).start();
            }
            else {
                new Game().start();
            }
            this.dispose();
        });

        JButton openMapEditor = new JButton("Редактор карт");
        GridBagConstraints editorConstraints = new MenuGridConstraints(0, 3);
        editorConstraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(openMapEditor, editorConstraints);
        openMapEditor.addActionListener(e -> new MapEditor());


        JButton exit = new JButton("Выход");
        GridBagConstraints exitConstraints = new MenuGridConstraints(0, 4);
        exitConstraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(exit, exitConstraints);
        exit.addActionListener(e -> System.exit(0));
    }

    private void initMenuPanel() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem loadMap = new JMenuItem("Импортировать карту");
        JMenuItem setDefaultMap = new JMenuItem("Установить карту по умолчанию");
        fileMenu.add(loadMap);
        fileMenu.add(setDefaultMap);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
        loadMap.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    field = FieldService.loadFromFile(file);
                    infoPanel.setText("Карта успешно импортирована: " + file.getName());
                    this.revalidate();
                }
                catch (IOException | ClassNotFoundException ex) {
                    logger.warn("Couldn't load field from file", ex);
                    infoPanel.setText("Не удалось импортировать карту. Используется карта по умолчанию.");
                    this.revalidate();
                }
            }
        });
        setDefaultMap.addActionListener(e -> {
            field = new Field(20, 20);
            infoPanel.setText("Установлена карта по умолчанию.");
            this.revalidate();
        });
    }

    public void refreshWindow() {

    }
}

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SapperGame extends JFrame {
    private Game game;

    private JPanel panel; // создаем переменную типа панель
    private final int COLS = 9; // константа - количество столбцов
    private final int ROWS = 9; // константа - количество строк
    private final int BOMBS = 10; // константа -  количество бомб всего
    private final int IMAGE_SIZE = 50; // константа - размер картинки

    public static void main(String[] args) {
        new SapperGame().setVisible(true);
    }

    private SapperGame() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImage();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { // перепишем форму, которая отрисовывает нашу форму
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this); // прорисовываем на панели картинку
                }
            }
        }; // инициализируем объект панель в форме

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start(); 
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE)); // Устанавливаем размер формы
        add(panel); // добавляем панель на форму

    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Действие по умолчанию при закрытии программы
        setTitle("Sapper Game"); // Указываем заголовок
        setLocationRelativeTo(null); // Устанавливаем окно по центру
        setResizable(false); // Не нужно изменять размер окна
        setVisible(true); // Чтобы форма была видна
        setIconImage(getImage("icon")); // устанавливаем иконку программы
        pack(); // Изменяет форму так, чтобы в ней всё поместилось
    }

    private void setImage() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    // Функция для получения картинки с именем name
    private Image getImage(String name) {
        String filename = "img/" + name + ".png"; // имя файла
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // загрузка ресурса с именем файла filename
        return icon.getImage(); // возвращаем картинку ресурса
    }
}

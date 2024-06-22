import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class KeyListenerExample extends JFrame implements KeyListener {
    private int x = 0; // начальная позиция картинки по оси X
    private int y = 25; // начальная позиция картинки по оси Y
    private BufferedImage image;
    int a = 10;

    public KeyListenerExample() {
        setTitle("Двигающаяся картинка");
        setSize(600, 605);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            // Загрузка изображения из файла PNG
            image = ImageIO.read(new File("src\\heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(this); // Регистрация KeyListener на панели
        setFocusable(true); // Установка фокуса на панель
        setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, x, y, null); // отрисовка изображения на заданной позиции (x, y)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyListenerExample imageMovement = new KeyListenerExample();
            imageMovement.setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется в этом примере
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            a = 20;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            moveImage(-a, 0); // передвигаем картинку влево
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveImage(a, 0); // передвигаем картинку вправо
        } else if (keyCode == KeyEvent.VK_UP) {
            moveImage(0, -a); // передвигаем картинку вверх
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveImage(0, a); // передвигаем картинку вниз
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            a = 10;
        }
    }

    public void moveImage(int dx, int dy) {
        x += dx;
        y += dy;
        if (dx < 0 && x < 0) {
            x = 550;
        }
        if (dx > 0 && x > 500) {
            x = 0;
        }
        if (dy > 0 && y > 555) {
            y = 25;
        }
        if (dy < 0 && y < 26) {
            y = 555;
        }
        repaint(); // перерисовываем окно
    }
}
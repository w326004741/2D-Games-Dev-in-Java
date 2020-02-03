package week3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Weichen Wang
 * @date 2020/1/23 - 5:23 PM
 * @description: ${description}
 */
public class DisplayRasterImage extends JFrame implements KeyListener {
    private static String workingDirectory;
    private Image alienImage;
    private Image smallImage;

    public DisplayRasterImage() {


        // set up JFrame 这是窗口运行时出现的坐标 以屏幕为基础 JFrame窗口大小为600，600
        setBounds(200, 200, 600, 600);
        setVisible(true);

        // load image from disk. Make sure you have the path right
        ImageIcon icon = new ImageIcon(workingDirectory + "/src/week3/alien_1.png");
        alienImage = icon.getImage();
        //   为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
        smallImage = alienImage.getScaledInstance(100, 100, Image.SCALE_FAST);
        //   再由修改后的Image来生成合适的Icon
        ImageIcon smallicon = new ImageIcon(smallImage);

        repaint();
        addKeyListener(this);
    }

    // application's paint method (may first happen before image is finished loading, hence repaint() above)

    @Override
    public void paint(Graphics g) {
        // draw a black rectangle on the whole canvas
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);
        // display the image (final argument is an 'ImageObserver' object)
        g.drawImage(smallImage, 250, 250, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key type");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KEy press");

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Release");
    }

    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);

        DisplayRasterImage d = new DisplayRasterImage();
    }
}

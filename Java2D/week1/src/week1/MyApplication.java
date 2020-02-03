package week1;

import javax.swing.*;
import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/1/14 - 6:00 PM
 * @description: Colored Squared 方块随机填充颜色
 */
public class MyApplication extends JFrame {

    // Set JFrame Windows Size
    public static final Dimension WindowSize = new Dimension(600, 600);

    public MyApplication() {
        // create and set up the window
        this.setTitle("Colored Squares");
        // 设置用户单击窗口的关闭按钮操作为默认关闭窗口操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        // coordinate x & y 坐标
        int x = screensize.width / 2 - WindowSize.height / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        // set new location by x and y, set new size by width and height
        setBounds(x, y, WindowSize.width, WindowSize.height);
        // show or hide this window depending on the value parameter b(true or false)
        setVisible(true);
    }

    /**
     * The paint() method of the JFrame class is automatically invoked whenever it needs to be painted(system-invoked)
     * main()方法会自动调用 piant(Graphics g) 方法
     * <p>
     * 该方法属于JFrame内部的方法， 可直接Override 覆写
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 每画一个 向右移50
        for (int x = 0; x <= 666; x += 50) {
            for (int y = 0; y <= 666; y += 50) {
                int a = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                int c = (int) (Math.random() * 256);

                Color color = new Color(a, b, c);
                System.out.println(color);
                g.setColor(color);
                // fillRect(int xTopLeft, int yTopLeft, int width, int height); 方块为40，则方块之间的gap为10
                g.fillRect(x + 20, y + 40, 40, 40);
            }
        }
    }

    public static void main(String[] args) {
        MyApplication m = new MyApplication();
    }
}

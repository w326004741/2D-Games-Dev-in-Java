package week2;

import javax.swing.*;
import java.awt.*;

/**
 * @author Weichen Wang
 * // * @date 2020/1/19 - 12:04 PM
 * @description: ${description}
 */
//  Task class
public class MovingSquaresApplication extends JFrame implements Runnable {

    // member
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMGAMEOBJECTS = 30;
    //  encapsulated class as an array then invoke all method of GameObject classs
    // 封装类为数组 并可以调用类中的方法
    private GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];
    private boolean isInitialised = false; // default = false

    // Constructor
    // 第二调用
    public MovingSquaresApplication() {
        // Initialization of the task class object
        /**Application 窗口设置*/
        // create and set up the window
        this.setTitle("Moving Colored Squares");
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
        System.out.println("Thread call me ");

        // create and initialise some GameObject instances
        for (int i = 0; i < NUMGAMEOBJECTS; i++) {
            GameObjectsArray[i] = new GameObject();  //创建并实例化对象 一个index创建一个新对象
        }
        // 意义？
        isInitialised = true;
        // create a thread
        Thread t = new Thread(this); // MovingSquaresApplication = object of task class
//        // Start a thread
        t.start(); // start the thread, and then invoke run() method automatically

    }

    // thread's entry point
    // 第三调用 move()
    @Override
    public void run() {
        // Tell system how to run custom thread
        System.out.println("Thread running" + Thread.currentThread().getId());

        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // animate game objects
            for (int i = 0; i < NUMGAMEOBJECTS; i++) {
                GameObjectsArray[i].move(); // 随机生成x,y坐标并覆盖上一个 存入数组 调用30次
            }
            // force an application repaint
            this.repaint();
        }
    }

    // application's paint method
    // 第一调用
    @Override
    public void paint(Graphics g) {
//        System.out.println("painting");
        if (!isInitialised) {
            return;
        }
        // clear the canvas with a big white rectangle 不刷白=画油画
        g.setColor(Color.white);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);

        // redraw all game objects
        for (int i = 0; i < NUMGAMEOBJECTS; i++) {
            GameObjectsArray[i].paint(g);
        }
    }

    // application's entry point
    public static void main(String[] args) {
        // create an instance of TaskClass
        MovingSquaresApplication myApplication = new MovingSquaresApplication();
        // automatically call paint() method first
        // and call constructor next, start thread as well -> thread call run() method
    }
}


package week3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Weichen Wang
 * @date 2020/1/23 - 6:06 PM
 * @description: ${description}
 */
public class InvadersApplication extends JFrame implements Runnable, KeyListener {

    // data member
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMALIENTS = 30;
    // Encapsulate 封装类为数组 长度为30
    private Sprite2D[] AliensArray = new Sprite2D[NUMALIENTS];
    private Sprite2D PlayerShip;

    private static String workingDirectory;
    private Image alienImage;
    private Image playerImage;
    private boolean isInitialised = false; // default = false

    // constructor
    public InvadersApplication() {
        // Initialization of the task class object
        /**Application 窗口设置*/
        // create and set up the window
        this.setTitle("Space Invaders!");
        // 设置用户单击窗口的关闭按钮操作为默认关闭窗口操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        // coordinate x & y 坐标
        int x = screensize.width / 2 - WindowSize.height / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);



        // create and initialise 30 aliens, passing them each the image we have loaded
        for (int i = 0; i < NUMALIENTS; i++) {
            AliensArray[i] = new Sprite2D(alienImage); // 创建并实例化对象 创建30个外星人icon在window内
        }

        /** for playerImage icon
         * create and initialise the player's spaceship*/
        ImageIcon playerIcon = new ImageIcon(workingDirectory + "/src/week3/tank.png");
        playerImage = playerIcon.getImage();

        PlayerShip = new Sprite2D(playerImage);
        PlayerShip.setPosition(300, 530);

        /** create a thread and start it*/
        Thread t = new Thread(this);
        t.start();

        /** send keyboard events arriving into this JFrame to its own event handlers*/
        addKeyListener(this);
        System.out.println("add key listener called");

        // it's now safe to paint the image
        isInitialised = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                PlayerShip.setXSpeed(-4);
                System.out.println("Move Left");
                break;
            case KeyEvent.VK_RIGHT:
                PlayerShip.setXSpeed(4);
                System.out.println("Move Right");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                PlayerShip.setXSpeed(0);
                break;
            case KeyEvent.VK_RIGHT:
                PlayerShip.setXSpeed(0);
                break;
        }
    }

    // thread's entry point
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 2: animate game objects
            for (Sprite2D enemy : AliensArray) {
                enemy.moveEnemy();// alien icon move randomly
            }
//            for (int i = 0; i < NUMALIENTS; i++) {
//                AliensArray[i].moveEnemy(); // alien icon move randomly
//            }
            PlayerShip.movePlayer();

            this.repaint(); // 3: force an application repaint
        }
    }

    @Override
    public void paint(Graphics g) {
        // 随机生成alienImage 在固定范围内
        if (isInitialised) { // don’t try to draw uninitialized objects!
            // clear canvas each 30 times 刷白
            g.setColor(Color.black);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);

            // redraw all game objects
            for (Sprite2D enemy : AliensArray) {
                enemy.paint(g);
            }
//        for (int i = 0; i < NUMALIENTS; i++) {
//            AliensArray[i].paint(g);
//        }
            PlayerShip.paint(g);
        }
    }

    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
//        System.out.println("Working Directory = " + workingDirectory);
        InvadersApplication invadersApplication = new InvadersApplication();
    }
}

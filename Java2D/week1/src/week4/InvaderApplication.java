package week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:48 AM
 * @description: ${description}
 */
public class InvaderApplication extends JFrame implements Runnable, KeyListener {

    private static final Dimension WindowSize = new Dimension(800, 600);
    private static final int NUMALIENS = 30;
    private Alien[] AlienArray = new Alien[NUMALIENS];
    private Spaceship PlayerShip;

    private BufferStrategy bufferStrategy;
    private Graphics offscreenGraphics;
    private boolean isInitialised = false; // default = false
    private static String workingDirectory;
    private Image alienImage;
    private Image playerImage;

    public InvaderApplication() {


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

        /** For Alien Image load from disk*/
        ImageIcon icon = new ImageIcon(workingDirectory + "/src/week4/alien_ship_1.png");
        alienImage = icon.getImage();

        // create and initialise 30 aliens, passing them each the image we have loaded
        for (int i = 0; i < NUMALIENS; i++) {
            AlienArray[i] = new Alien(alienImage);
            /** Initialise the aliens in a grid formation rather than randomly positioned
             * 第一排先画5个在到第二排*/
            double xx = (i % 5) * 88 + 70;     // 对5求余 每行有5个aliens. +：离x边框的距离
            double yy = (i / 5) * 40 + 50;  // 每排5个  +：y边框的距离
            AlienArray[i].setPosition(xx, yy);
        }
        // 设置Alien移动加速度
        Alien.setxSpeed(4);

        icon = new ImageIcon(workingDirectory + "/src/week4/player_ship.png");
        playerImage = icon.getImage();

        PlayerShip = new Spaceship(playerImage);
        PlayerShip.setPosition(300, 530);

        /** tell all sprites the window width
         * For border of alien and spaceship 否则都为0*/
        Sprite2D.setWinWidth(WindowSize.width);
        Sprite2D.setWinHeight(WindowSize.height);

//        /** create a thread and start it*/
        Thread t = new Thread(this);
        t.start();

        /** send keyboard events arriving into this JFrame to its own event handlers*/
        addKeyListener(this);
        System.out.println("add key listener called");


        /**
         * This code should be executed after the JFrame has been displayed.
         * i.e. after setBounds() and setVisible()...*/
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        offscreenGraphics = bufferStrategy.getDrawGraphics(); // getDrawGraphics(): 创建用于绘制缓冲区的图形上下文。

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
                PlayerShip.setxSpeed(-4);
                System.out.println("Left");
                break;
            case KeyEvent.VK_RIGHT:
                PlayerShip.setxSpeed(4);
                System.out.println("Right");
                break;
            case KeyEvent.VK_UP:
                PlayerShip.setySpeed(-4);
                System.out.println("Up");
                break;
            case KeyEvent.VK_DOWN:
                PlayerShip.setySpeed(4);
                System.out.println("Down");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                PlayerShip.setxSpeed(0);
                break;
            case KeyEvent.VK_RIGHT:
                PlayerShip.setxSpeed(0);
                break;
            case KeyEvent.VK_UP:
                PlayerShip.setySpeed(0);
                break;
            case KeyEvent.VK_DOWN:
                PlayerShip.setySpeed(0);
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean alienDirectionReversalNeeded = false;
//            // 2: animate game objects
            for (int i = 0; i < NUMALIENS; i++) {
                if (AlienArray[i].move()) {
                    alienDirectionReversalNeeded = true; // 保证30个aliens 全部都到达边缘
//                    Alien.reverseDirection();
//                    for (int x = 0; x < NUMALIENS; x++) {
//                        AlienArray[x].jumpDownwards();
//                    }
                }
            }
            if (alienDirectionReversalNeeded) { // 保证30个aliens 全部都到达边缘的化
                Alien.reverseDirection();   // 反向运动

                for (int i = 0; i < NUMALIENS; i++) {
                    AlienArray[i].jumpDownwards();
                }
            }

            PlayerShip.move();
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (isInitialised) {
            // at the start of the paint() method
            g = offscreenGraphics; // draw to offscreen buffer

            g.setColor(Color.black);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);

            // redraw all game objects
            for (Alien alien : AlienArray) {
                alien.paint(g);
            }
            PlayerShip.paint(g);

            // at the end of paint() method
            // flip the buffers offscreen <--> onscreen. display the buffer
            bufferStrategy.show();
        }
    }

    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        InvaderApplication invadersApplication = new InvaderApplication();
    }
}

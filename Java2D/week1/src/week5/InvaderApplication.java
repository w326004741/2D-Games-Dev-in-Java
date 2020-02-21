package week5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:48 AM
 * @description: ${description}
 */
public class InvaderApplication extends JFrame implements Runnable, KeyListener {
    // member data
    public static final Dimension WindowSize = new Dimension(800, 600); // this is now public so that other classes can read!
    private BufferStrategy strategy;
    private Graphics offscreenBuffer;
    private static final int NUMALIENS = 30;
    private Alien[] AliensArray = new week5.Alien[NUMALIENS];

    private Spaceship PlayerShip;
    private Image bulletImage;

    private ArrayList<PlayerBullet> bulletsList = new ArrayList<>();

    private boolean isInitialised = false;
    private static String workingDirectory;

    // constructor
    public InvaderApplication() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders! .. (getting there)");


        // load images from disk. Make sure you have the path right!
        ImageIcon icon = new ImageIcon(workingDirectory + "/src/images/alien_ship_1.png");
        Image alienImage = icon.getImage();

        icon = new ImageIcon(workingDirectory + "/src/images/alien_ship_2.png");
        Image alienImage2 = icon.getImage();

        icon = new ImageIcon(workingDirectory + "/src/images/bullet.png");
        bulletImage = icon.getImage();

        // create and initialise some aliens, passing them each the image we have loaded
        for (int i = 0; i < NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage, alienImage2);
            double xx = (i % 5) * 80 + 70;
            double yy = (i / 5) * 40 + 50;

            AliensArray[i].setPosition(xx, yy);
            AliensArray[i].setXSpeed(2);
        }
        // create and initialise the player's spaceship
        icon = new ImageIcon(workingDirectory + "/src/images/player_ship.png");
        Image shipImage = icon.getImage();
        PlayerShip = new Spaceship(shipImage, bulletImage);
        PlayerShip.setPosition(300, 530);

        // create and start our animation thread
        Thread t = new Thread(this);

        t.start();
        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);
        // initialise double-buffering
        createBufferStrategy(2);

        strategy = getBufferStrategy();
        offscreenBuffer = strategy.getDrawGraphics();
        isInitialised = true;
    }

    // thread's entry point
    public void run() {
        while (true) {
            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }

            // 2: animate game objects
            boolean alienDirectionReversalNeeded = false;
            for (int i = 0; i < NUMALIENS; i++) {
                if (AliensArray[i].isAlive) {
                    if (AliensArray[i].move())
                        alienDirectionReversalNeeded = true;
                }
            }
            if (alienDirectionReversalNeeded) {
                for (int i = 0; i < NUMALIENS; i++) {
                    if (AliensArray[i].isAlive) {
                        AliensArray[i].reverseDirection();
                    }
                }
            }
            PlayerShip.move();

            Iterator iterator = bulletsList.iterator();
            while (iterator.hasNext()) {
                PlayerBullet bullet = (PlayerBullet) iterator.next();
                if (bullet.move()) { // 如果子弹y<0了 则 remove
                    // true was returned by move method if bullet needs destroying due to going offscreen
                    // iterator.remove is a safe way to remove from the ArrayList while iterating thru it
                    iterator.remove();
                } else {
                    // check for collision between this bullet and any alien
                    double x2 = bullet.x, y2 = bullet.y;
                    double w1 = 50, h1 = 32; // for alien width & height
                    double w2 = 6, h2 = 16; // for bullet width & height

                    for (int i = 0; i < NUMALIENS; i++) {
                        if (AliensArray[i].isAlive) {
                            double x1 = AliensArray[i].x; // alien x position
                            double y1 = AliensArray[i].y;// alien y position
                            if (        // 子弹在右边碰到，or 子弹在左边碰到
                                    ((x1 < x2 && x1 + w1 > x2) || (x2 < x1 && x2 + w2 > x1))
                                            &&
                                            ((y1 < y2 && y1 + h1 > y2) || (y2 < y1 && y2 + h2 > y1))
                            ) {
                                // destroy alien and bullet
                                AliensArray[i].isAlive = false;
                                iterator.remove();
                                break; // no need to keep checking aliens so break out of for loop
                            }
                        }
                    }
                }
            }
            // 3: force an application repaint
            this.repaint();
        }

    }

    // Three Keyboard Event-Handler functions
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                PlayerShip.setXSpeed(-4);
                System.out.println("Left");
                break;
            case KeyEvent.VK_RIGHT:
                PlayerShip.setXSpeed(4);
                System.out.println("Right");
                break;
            case KeyEvent.VK_SPACE:
                bulletsList.add(PlayerShip.shootBullet()); //
                System.out.println("Shoot");
                break;
        }

//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            PlayerShip.setXSpeed(-4);
//        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            PlayerShip.setXSpeed(4);
//        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            bulletsList.add(PlayerShip.shootBullet());
//        }
    }

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

    public void keyTyped(KeyEvent e) {
    } //

    // application's paint method
    public void paint(Graphics g) {
        if (!isInitialised)
            return;
        g = offscreenBuffer; // draw to offscreen buffer
        // clear the canvas with a big black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);

        // redraw all game objects
        for (int i = 0; i < NUMALIENS; i++) {
            AliensArray[i].paint(g);
        }

        PlayerShip.paint(g);

        // 1. for loop
//        for (int i = 0; i < bulletsList.size(); i++) {
//            PlayerBullet b = bulletsList.get(i);
//            b.paint(g);
//        }

        // 2. foreach
//        for (PlayerBullet pb : bulletsList) {
//            pb.paint(g);
//        }

        // 3. Iterator Method
        Iterator iterator = bulletsList.iterator();
        while (iterator.hasNext()) {
            PlayerBullet b = (PlayerBullet) iterator.next();
            b.paint(g);
        }
        // flip the buffers
        strategy.show();
    }

    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        InvaderApplication w = new InvaderApplication();
    }
}

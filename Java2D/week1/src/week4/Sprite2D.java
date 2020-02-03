package week4;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:41 AM
 * @description: ${description}
 * <p>
 * SuperClass for Alien and Spaceship class
 * 1. use protected type for data member
 */

public class Sprite2D {

    // member data
    protected double x, y;
    protected Image myImage;
    protected static int winWidth;
    protected static int winHeight;


    public Sprite2D(Image i) {
        this.myImage = i;
    }

    // abstract move() method offer for alien and spaceship class
//    public abstract void move();

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public void paint(Graphics g) {
        g.drawImage(myImage, (int) x, (int) y, null);
    }

    public static void setWinWidth(int w) {
        winWidth = w;
    }

    public static void setWinHeight(int h) {
        winHeight = h;
    }
}

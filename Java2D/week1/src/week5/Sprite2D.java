package week5;

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
    protected double xSpeed = 0;
    protected Image myImage, myImage2;
    int framesDrawn = 0;

    // constructor
    public Sprite2D(Image i, Image i2) {
        myImage = i;
        myImage2 = i2;
    }

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public void setXSpeed(double dx) {
        xSpeed = dx;
    }


    public void paint(Graphics g) {
        framesDrawn++;
        // alternate between 2 images every 50 frames 50帧
        if (framesDrawn % 100 < 50) {
//            System.out.println(framesDrawn % 100);
            g.drawImage(myImage, (int) x, (int) y, null);
        } else {
            g.drawImage(myImage2, (int) x, (int) y, null);
        }
    }
}

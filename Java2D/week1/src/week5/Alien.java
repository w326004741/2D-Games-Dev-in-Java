package week5;

import week3.InvadersApplication;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:45 AM
 * @description: ${description}
 */
public class Alien extends Sprite2D {
    public boolean isAlive = true;

    public Alien(Image i, Image i2) {
        super(i, i2); // invoke constructor on superclass Sprite2D
    }


    public void paint(Graphics g) {
        if (isAlive) {
            super.paint(g);
        }
    }

    public boolean move() {
        x += xSpeed;
        // direction reversal needed?
        if (x <= 0 || x >= InvaderApplication.WindowSize.width - myImage.getWidth(null)) {
            return true;
        } else {
            return false;
        }
    }

    public void reverseDirection() {
        xSpeed = -xSpeed;
        y += 20;
    }
}

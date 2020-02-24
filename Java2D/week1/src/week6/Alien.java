package week6;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/22 - 1:51 PM
 * @description: ${description}
 */
public class Alien extends Sprite2D {
    public boolean isAlive = true;

    public Alien(Image i, Image i2) {
        super(i, i2); // invoke constructor on superclass Sprite2D
    }

    public void paint(Graphics g) {
        if (isAlive) super.paint(g);
    }

    public boolean move() {
        x += xSpeed;
        // direction reversal needed?
        if (x <= 0 || x >= InvadersApplication.WindowSize.width - myImage.getWidth(null)) return true;
        else
            return false;

    }

    public void reverseDirection() {
        xSpeed = -xSpeed;
        y += 20;
    }
}

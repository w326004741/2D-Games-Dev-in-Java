package week4;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:45 AM
 * @description: ${description}
 */
public class Alien extends Sprite2D {

    private static double xSpeed = 0;

    public Alien(Image i) {
        super(i);
    }

    public boolean move() {
//        System.out.println("ALien move");
        x += xSpeed;

        System.out.println(x);

        // direction reversal
        if (x <= 0 || x >= winWidth - myImage.getWidth(null)) {
            return true;
        } else {
            return false;
        }
    }

    public static void setxSpeed(double dx) {
        xSpeed = dx;
    }

    // check Collision
    public static void reverseDirection() {
        System.out.println("reverse");
        // 碰到边框则反向移动
        xSpeed = -xSpeed;
    }

    // Move down a bit 向下移动20
    public void jumpDownwards() {
//        System.out.println("down");
        y += 4;
    }
}

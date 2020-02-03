package week4;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:47 AM
 * @description: ${description}
 */
public class Spaceship extends Sprite2D {

    private double xSpeed = 0;
    private double ySpeed = 0;

    public Spaceship(Image i) {
        super(i);
    }

    public void setxSpeed(double dx) {
        this.xSpeed = dx;
    }


    public void setySpeed(double dy) {
        this.ySpeed = dy;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
        // keep player in window
        if (x <= 0) {
            x = 0;
            xSpeed = 0;
        } else if (x > winWidth - myImage.getWidth(null)) {
            x = winWidth - myImage.getWidth(null);
            xSpeed = 0;
        }

        if (y <= 300) {
            y = 300;
            ySpeed = 0;
        } else if (y > winHeight - myImage.getHeight(null)) {
            y = winHeight - myImage.getHeight(null);
            ySpeed = 0;
        }

//        if (y <= 300) {
//            y = 300;
//            ySpeed = 0;
//
//        } else if (y > 560) {
//            y = 560;
//            ySpeed = 0;
//        }
    }
}

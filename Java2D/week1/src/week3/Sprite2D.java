package week3;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/1/23 - 6:06 PM
 * @description: ${description}
 * <p>
 * <p>
 * Player
 */
public class Sprite2D {

    // data member
    private double x, y;
    private double xSpeed = 0;
    private Image myImage;
    private Image smallImage;


    // constructor
    public Sprite2D(Image i) {
        // 控制alien 出现范围
        x = (Math.random() * 570);
        y = (Math.random() * 400);

        myImage = i;
        /** change image size to smaller
         * ref: https://stackoverflow.com/questions/5895829/resizing-image-in-java*/
        smallImage = myImage.getScaledInstance(40, 40, Image.SCALE_FAST);
    }

    // public interface
    public void moveEnemy() {

        x += (Math.random() - Math.random());
        y += (Math.random() - Math.random());
//        if (x < 0) {
//            x = 0;
//        } else if (x > 600) {
//            x = 600;
//        }
//
//        if (y < 50) {
//            y = 50;
//        } else if (y > 400) {
//            y = 400;
//        }
    }

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public void movePlayer() {
        x += xSpeed;
        // keep player in window
    }

    public void setXSpeed(double dx) {
        xSpeed = dx;
    }

    public void paint(Graphics g) {
        g.drawImage(smallImage, (int) x, (int) y, null);
    }
}

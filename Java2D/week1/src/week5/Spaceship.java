package week5;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Weichen Wang
 * @date 2020/2/2 - 11:47 AM
 * @description: ${description}
 */
public class Spaceship extends Sprite2D {

    private Image bulletImage;

    public Spaceship(Image i, Image bullet) {
        super(i, i); // invoke constructor on superclass Sprite2D
        bulletImage = bullet;
    }

    public void move() {
        // apply current movement
        x += xSpeed;

        // stop movement at screen edge?
        if (x <= 0) {
            x = 0;
            xSpeed = 0;
        } else if (x >= InvaderApplication.WindowSize.width - myImage.getWidth(null)) {
            x = InvaderApplication.WindowSize.width - myImage.getWidth(null);
            xSpeed = 0;
        }
    }

    // method to handle shooting
    public PlayerBullet shootBullet() {
        // add a new bullet to our list
        PlayerBullet b = new PlayerBullet(bulletImage);
        b.setPosition(this.x + 54 / 2, this.y);
        return b;
    }

}

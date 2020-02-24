package week6;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/2/22 - 1:51 PM
 * @description: ${description}
 */
public class PlayerBullet extends Sprite2D {
    public PlayerBullet(Image i) {
        super(i, i); // invoke constructor on superclass Sprite2D
    }

    public boolean move() {
        y -= 10;
        return (y < 0); // return true if bullet is offscreen and needs destroying
    }
}

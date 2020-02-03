package week2;

import java.awt.*;

/**
 * @author Weichen Wang
 * @date 2020/1/19 - 12:23 PM
 * @description: ${description}
 */
public class GameObject {

    // member data
    private double x, y;
    private Color c;

    /**
     * Constructor
     * Constructor method randomises the object’s position and color
     * Initialise variables and object
     */
    public GameObject() {
        x = Math.random() * 600;
        y = Math.random() * 600;
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        c = new Color(r, g, b);
    }

    /**
     * Public move() method is used to randomly alter x,y members
     */
    public void move() {
//        System.out.println("Called Move()");
        x += (Math.random() - Math.random());
        y += (Math.random() - Math.random());
    }

    /**
     * Public paint(Graphics g) method draws the object as a square using g.fillRect()
     *
     * @param g
     */
    public void paint(Graphics g) {
//        System.out.println("Painting Game");
        g.setColor(c);
        g.fillRect((int) x, (int) y, 40, 40);
    }
}

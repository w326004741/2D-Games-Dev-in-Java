package week3;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Weichen Wang
 * @date 2020/1/23 - 5:17 PM
 * @description: ${description}
 */
public class MyApplication extends JFrame implements KeyListener {

    public MyApplication() {
        //  send keyboard events arriving into this JFrame to its own event handlers
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key press!!");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

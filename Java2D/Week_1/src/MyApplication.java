import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.util.Random;

import javax.swing.JFrame;

public class MyApplication extends JFrame {

	// Dimension(int width, int height), constant value 定值
	// Set JFrame Window Size
	public static final Dimension WindowSize = new Dimension(600, 600);

	public MyApplication() {
		// create and set up the window.
		this.setTitle("Pacman, or something...");
		// 用户单击窗口的关闭按钮时程序执行的操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window, centred on the screen
		// Toolkit: utility class 工具类，getDefaultToolkit：Static method静态方法
		// getScreenSize: 返回当前屏幕的分辨率 size
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width / 2 - WindowSize.width / 2; // coordinate x 坐标
		int y = screensize.height / 2 - WindowSize.height / 2; // coordinate y 坐标
		// set new location by x & y, set new size by width & height.
		setBounds(x, y, WindowSize.width, WindowSize.height);
		// Shows or hides this Window depending on the value of parameter b.
		setVisible(true);
	}

	// The paint() method of the JFrame class is automatically invoked
	// whenever it needs to be painted (system-invoked） 会自动调用
	public void paint(Graphics g) {
		// Creates a new Font from the specified name, style and point size
//		Font f = new Font("Times", Font.PLAIN, 24); // 字体名字，style，大小
//		g.setFont(f);
//		System.out.println("Set Font");

		// Color c = Color.GRAY; // 上色
		// 每画一个 向右移动50
		for (int x = 0; x <= 666; x += 50) {
			for (int y = 0; y <= 666; y += 50) {
				int a = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);
				int c = (int) (Math.random() * 256);

				Color color = new Color(a, b, c);
				System.out.println(color);
				g.setColor(color);
				// fillRect(int xTopLeft, int yTopLeft, int width, int height);
				g.fillRect(x + 20, y + 40, 40, 40);
			}
		}
//		g.setColor(color);
//		g.drawString("Pacman!!", 20, 60); //
	}

	public static void main(String[] args) {
		MyApplication w = new MyApplication();
	}
}

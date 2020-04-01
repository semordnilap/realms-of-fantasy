package net.marvy.core;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import net.marvy.core.gfx.Screen;

public class GameListener {
	static JFrame frame;

	static Canvas canvas;
	
	// TODO finish GameListener

	private static List<Integer> active_keys = new ArrayList<>();

	private static List<Integer> to_deactivate = new ArrayList<>();

	private static boolean[] mouse_buttons = new boolean[3];

	private static boolean[] mouse_buttons_last = new boolean[3];

	public static void init() {
		frame = Screen.instance.getFrame();
		canvas = Screen.instance.getCanvas();
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Variables.is_running = false;
			}
		});
		
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getButton()) {
				case 1:
					GameListener.mouse_buttons[0] = true;
					GameListener.mouse_buttons_last[0] = false;
					break;
				case 2:
					GameListener.mouse_buttons[1] = true;
					GameListener.mouse_buttons_last[1] = false;
					break;
				case 3:
					GameListener.mouse_buttons[2] = true;
					GameListener.mouse_buttons_last[2] = false;
					break;
				}
			}

			public void mouseReleased(MouseEvent e) {
				switch (e.getButton()) {
				case 1:
					GameListener.mouse_buttons[0] = false;
					GameListener.mouse_buttons_last[0] = true;
					break;
				case 2:
					GameListener.mouse_buttons[1] = false;
					GameListener.mouse_buttons_last[1] = true;
					break;
				case 3:
					GameListener.mouse_buttons[2] = false;
					GameListener.mouse_buttons_last[2] = true;
					break;
				}
			}
		});
		
		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent e) {}
		});
		
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {}

			public void mouseMoved(MouseEvent e) {}
		});
		
		canvas.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {}
		});
	}
}

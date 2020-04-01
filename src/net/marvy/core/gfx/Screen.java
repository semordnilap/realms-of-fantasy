package net.marvy.core.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import net.marvy.core.Constants;
import net.marvy.core.Game;
import net.marvy.core.io.GameResourceLoader;

public class Screen {
	
	public static Screen instance;

	private int width, height;
	private String title;

	private JFrame frame;
	private Canvas canvas;

	private BufferedImage image;
	private int[] pixels;
	private BufferStrategy bs;
	private Graphics g;

	private boolean keyframe_requested = false;

	public Screen(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.frame = new JFrame();
		this.frame.setTitle(title);
		
		this.canvas = new Canvas();
		
		Dimension d = new Dimension(width, height);
		this.canvas.setMinimumSize(d);
		this.canvas.setPreferredSize(d);
		this.canvas.setMaximumSize(d);
		
		this.frame.add(this.canvas);
		this.frame.pack();
		
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.frame.setIconImage(GameResourceLoader.readImageFile("logo"));
		
		this.canvas.requestFocusInWindow();
		this.canvas.createBufferStrategy(Constants.BUFF_STRATEGY);
		
		this.image = new BufferedImage(width, height, 1);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
		
		this.bs = this.canvas.getBufferStrategy();
		this.g = this.bs.getDrawGraphics();
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public void show() {
		this.frame.setVisible(true);
	}

	public void hide() {
		this.frame.setVisible(false);
	}

	public void dispose() {
		this.g.dispose();
		this.image.flush();
		this.bs.dispose();
		this.frame.dispose();
	}

	public void requestKeyframe() {
		this.keyframe_requested = true;
	}

	public void setSubtitle(String subtitle) {
		if (subtitle == "" || subtitle == null) {
			this.frame.setTitle(this.title);
		} else {
			this.frame.setTitle(this.title + subtitle);
		}
	}

	public void setPixel(int x, int y, int color) {
		int index = y * this.width + x;
		if (x < 0 || x >= this.width || y < 0 || y >= this.height || index >= this.pixels.length)
			return;
		this.pixels[index] = color;
	}
	
//	public void addToPixel(int x, int y, int color) {
//		int index = y * this.width + x;
//		if(x < 0 || x >= this.width || y < 0 || y >= this.height || index >= this.pixels.length) return;
//		
//		this.pixels[index] = Misc.addColors(this.pixels[index], color);
//	}

	public void clearPixels(int color) {
		for (int i = 0; i < this.pixels.length; i++)
			this.pixels[i] = color;
	}

	public void render(boolean is_key_frame) {
		if (is_key_frame || this.keyframe_requested)
			clearPixels(0xff000000);
		
		Game.states[Game.current_state].render(is_key_frame, this.image.getGraphics());
		
		this.g.drawImage(this.image, 0, 0, this.width, this.height, null);
		
		// Show current Buffer. This throws a weird error when window closing - but causes nothing else.
		// Just ignore the error!
		try {
			this.bs.show();
		} catch (IllegalStateException e) {}
	}
}

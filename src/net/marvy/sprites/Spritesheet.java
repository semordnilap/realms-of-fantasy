package net.marvy.sprites;

import java.awt.image.BufferedImage;

import net.marvy.core.Log;
import net.marvy.core.io.GameResourceLoader;

public class Spritesheet {

	private String filename;
	private BufferedImage image;
	private int sprite_width, sprite_height;
	
	public Spritesheet(String filename, int sprite_width, int sprite_height) {	
		
		Log.debug("Loading spritesheet '{}'", filename);
		
		this.filename = filename;
		this.sprite_width = sprite_width;
		this.sprite_height = sprite_height;
		
		this.image = GameResourceLoader.readImageFile(filename);
		
		if((this.image.getWidth() % sprite_width != 0) || (this.image.getHeight() % sprite_height != 0)) { 
			Log.error("Invalid spritesheet image file size: {}x{} ('{}')", this.image.getWidth(), this.image.getHeight(), filename);
		}
	}
	
	public int[] getSprite(int x_index, int y_index) {
		
		int[] sprite_pixels = this.image.getRGB(x_index * this.sprite_width, y_index * this.sprite_height,
				this.sprite_width, this.sprite_height, null, 0, this.sprite_width);
		
		return sprite_pixels;
	}
	
	public int getSpriteWidth() {
		return this.sprite_width;
	}
	
	public int getSpriteHeight() {
		return this.sprite_height;
	}
	
	public String getFilename() {
		return filename;
	}
}

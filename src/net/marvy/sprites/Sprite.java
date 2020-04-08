package net.marvy.sprites;

import java.awt.image.BufferedImage;

import net.marvy.core.Log;
import net.marvy.core.gfx.Misc;
import net.marvy.core.gfx.Screen;
import net.marvy.core.io.GameResourceLoader;

public class Sprite {

	int width, height;
	int[] pixels;
	
	Spritesheet spritesheet;
	
	public Sprite(String filename) {
		Log.trace("Loading sprite '{}'", filename);
		
		BufferedImage image = GameResourceLoader.readImageFile(filename);
		
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);
	}
	
	public Sprite(Spritesheet spritesheet, int sprite_pos_x, int sprite_pos_y) {
		Log.trace("Loading sprite ({},{}) from spritesheet '{}'", sprite_pos_x, sprite_pos_y, spritesheet.getFilename());
		
		this.width = spritesheet.getSpriteWidth();
		this.height = spritesheet.getSpriteHeight();
		
		this.pixels = spritesheet.getSprite(sprite_pos_x, sprite_pos_y);
	}
	
	public void render(Screen s, int startX, int startY, double scale) {		
		int rw, rh;
		int[] temp;
		
		if(scale == 1) {
			rw = this.width;
			rh = this.height;
			temp = pixels;
		} else {
			rw = (int)(this.width * scale);
			rh = (int)(this.height * scale);
			temp = Misc.resizeNearestNeighbor(pixels, this.width, this.height, scale);
		}
		
		for(int i = 0; i < temp.length; i++) {
			if((temp[i] & 0xff000000) >>> 24 == 255)
				s.setPixel(startX + (i % rw), startY + (int)(i / rh), temp[i]);
			else if((temp[i] & 0xff000000) >>> 24 == 0)
				continue;
			else
				s.addToPixel(startX + (i % rw), startY + (int)(i / rh), temp[i]);
		}		
	}
	
}

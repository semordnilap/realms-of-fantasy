package net.marvy.tiles;

import net.marvy.core.GameMath.Vector2;
import net.marvy.core.gfx.Screen;
import net.marvy.sprites.Sprite;
import net.marvy.world.World;

public class Tile {

	private World world;
	private Vector2<Integer> world_pos;
	
	private Sprite sprite;
	
	private int meta = 0;
	
	public Tile() {
		// TODO init tile
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void render(Screen s, int x, int y, int scale) {
		getSprite().render(s, x, y, scale);
	}
}

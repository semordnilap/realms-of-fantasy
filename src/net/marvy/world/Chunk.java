package net.marvy.world;

import net.marvy.core.Constants;
import net.marvy.core.Log;
import net.marvy.tiles.Tile;
import net.marvy.core.GameMath.Vector2;

public class Chunk {

	private Tile[] tiles;
	
	private World world;
	private Vector2<Integer> world_index;
	
	public Chunk() {
		this.tiles = new Tile[Constants.CHUNK_SIZE * Constants.CHUNK_SIZE];
	}
	
	public void setTile(int x, int y, Tile t) {
		if(x < 0 || x > Constants.CHUNK_SIZE || 
		   y < 0 || y > Constants.CHUNK_SIZE ||
		   (y * Constants.CHUNK_SIZE + x) >= this.tiles.length) {
			Log.warn("Trying to set Tile outside of Chunk borders!");
			return;
		} else {
			this.tiles[y * Constants.CHUNK_SIZE + x] = t;
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || x > Constants.CHUNK_SIZE || 
		   y < 0 || y > Constants.CHUNK_SIZE ||
		   (y * Constants.CHUNK_SIZE + x) >= this.tiles.length) {
			Log.warn("Trying to retrieve Tile outside of Chunk borders! Retruned null.");
			return null;
		} else {
			return this.tiles[y * Constants.CHUNK_SIZE + x];
		}
	}
	
	public int getNumTiles() {
		return this.tiles.length;
	}
	
}

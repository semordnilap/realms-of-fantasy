package net.marvy.states;

import java.awt.Color;
import java.awt.Graphics;
import net.marvy.core.State;
import net.marvy.core.gfx.Screen;
import net.marvy.sprites.Sprites;

public class StateTest extends State {
	
	int x = 0;

	public void init() {}

	public void update() {
		this.x++;
	}

	public void render(boolean is_key_frame, Graphics g) {
		Screen.instance.requestKeyframe();
		
		g.setColor(Color.RED);
		g.fillRect(50 + this.x, 50, 100, 80);
		
		Sprites.GRASS.render(Screen.instance, 20, 280, this.x * 0.05);
	}
}

package net.marvy.core;

import java.awt.Graphics;

public abstract class State {
	
	public static final int TEST = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	// public static final int LOAD = 3; --- the load screen

	public abstract void init();

	public abstract void update();

	public abstract void render(boolean paramBoolean, Graphics paramGraphics);
}

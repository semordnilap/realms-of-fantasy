package net.marvy.core;

public class GameMath {

	public static int ensureRange(int n, int min, int max) {
		return Math.min(Math.max(n, min), max);
	}
	
}

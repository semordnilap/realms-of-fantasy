package net.marvy.core;

public class Constants {
	public static final String TITLE = "Realms of Fantasy";

	public static final int VERSION = 0;
	public static final int SUBVERSION = 0;
	public static final int UPDATE = 0;

	public static final String VERSION_STRING = String.format("v%d.%d.%d", VERSION, SUBVERSION, UPDATE);
	public static final String TITLE_AND_VERSION = TITLE + " " + VERSION_STRING;

	public static final int TPS = 20;
	public static final int SKIP_TICKS = 1000 / TPS;
	public static final int MAX_FRAMESKIP = 10;

	public static final int NUM_GAME_STATES = 5;

	public static final boolean SCREEN_RESIZABLE = false; // TODO Should be true I guess..
	public static final int BUFF_STRATEGY = 3;
	
	public static final int CHUNK_SIZE = 16;
}

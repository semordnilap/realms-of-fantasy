package net.marvy.core;

import org.tinylog.Logger;
import org.tinylog.configuration.Configuration;

import net.marvy.core.gfx.Screen;
import net.marvy.states.StateMenu;
import net.marvy.states.StatePlay;
import net.marvy.states.StateTest;

public class Game {
	
	public static State[] states;

	public static int current_state;

	/**
	 * Initialize the whole game.
	 * TODO should this be pre init, then load screen, then actual init?
	 */
	public static void init() {
		// First things first, we need to load settings - this way we can find
		// out, whether to run debug mode or not.
		Settings.instance = new Settings();
		Settings.instance.loadFromRawFile("settings");
		
		// If running debug mode, write debug messages as well
		if(Settings.instance.getBoolean("debug_mode")) {
			Configuration.set("writer.level", "debug");
		}
		
		Logger.info("{} {}", Constants.TITLE_AND_VERSION, 
				(Settings.instance.getBoolean("debug_mode") ? "[DEBUG MODE]" : ""));
		
		// This is where the actual init starts!
		Logger.info("Initializing...");

		Logger.debug("Setting up game states...");
		states = new State[Constants.NUM_GAME_STATES];

		states[0] = new StateTest();
		states[1] = new StateMenu();
		states[2] = new StatePlay();
		current_state = State.TEST; // TODO change to StateMenu ASAP

		// Init the current game state
		// TODO should we init all?
		states[current_state].init();

		// Init screen with settings
		Logger.debug("Creating display...");
		Screen.instance = new Screen(Settings.instance.getInteger("screen_dimensions", 0),
				Settings.instance.getInteger("screen_dimensions", 1), Constants.TITLE_AND_VERSION);

		Logger.debug("Initializing input listener...");
		GameListener.init();

		Logger.info("Initialization successful");
		Screen.instance.show();
	}

	/**
	 * Main game loop method.
	 */
	public static void run() {
		Logger.info("Running game loop...");

		int loops = 0;
		long next_tick = System.currentTimeMillis();
		
		int second_counter = 0;		
		int fps = 0;
		boolean is_key_frame = true;
		
		Variables.is_running = true;
		while (Variables.is_running) {
			loops = 0;
			while (System.currentTimeMillis() > next_tick && loops < Constants.MAX_FRAMESKIP) {
				next_tick += Constants.SKIP_TICKS;
				loops++;
				
				if (!Variables.is_paused)
					states[current_state].update();
				
				second_counter += Constants.SKIP_TICKS;
				if (second_counter >= 1000) {
					Logger.debug("FPS: {}", fps); // TODO remove and show fps differently
					second_counter = 0;
					fps = 0;
					is_key_frame = true;
				}
			}
			Screen.instance.render(is_key_frame);
			if (is_key_frame)
				is_key_frame = false;
			fps++;
		}
	}
	
	/*
	 * Disposes of everything and stops the game. Called after game loop stops running.
	 */
	public static void stop() {
		Logger.info("Terminating...");

		Screen.instance.dispose();
		System.exit(0);
	}

	/**
	 * Main entry point of the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game.init();
		
		Game.run();
		
		Game.stop();
		
	}
}

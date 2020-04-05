package net.marvy.core;

import net.marvy.core.io.RawFileParser;

public class Settings {
	
	public static Settings MAIN;
	public static Settings KEYCONFIG;

	private RawFileParser parser;

	public void loadFromRawFile(String filename) {
		this.parser = new RawFileParser(filename);
		this.parser.parse();
	}

	public String getString(String field, int index) {
		return this.parser.getArgAsString(field, index);
	}

	public String getString(String field) {
		return getString(field, 0);
	}

	public boolean getBoolean(String field, int index) {
		return this.parser.getArgAsBoolean(field, index);
	}

	public boolean getBoolean(String field) {
		return getBoolean(field, 0);
	}

	public int getInteger(String field, int index) {
		return this.parser.getArgAsInteger(field, index);
	}

	public int getInteger(String field) {
		return getInteger(field, 0);
	}
}

package net.marvy.core;

import org.tinylog.Logger;

public class Log {

	public static final int TRACE = 0;
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WARN = 3;
	public static final int ERROR = 4;
	
	private static int min_severity = INFO;
	
	public static void trace(Object message) {
		if(min_severity <= TRACE)
			Logger.trace(message);
	}
	
	public static void trace(String message, Object... args) {
		if(min_severity <= TRACE)
			Logger.trace(message, args);
	}
	
	public static void debug(Object message) {
		if(min_severity <= DEBUG)
			Logger.debug(message);
	}
	
	public static void debug(String message, Object... args) {
		if(min_severity <= ERROR)
			Logger.debug(message, args);
	}
	
	public static void info(Object message) {
		if(min_severity <= INFO)
			Logger.info(message);
	}
	
	public static void info(String message, Object... args) {
		if(min_severity <= INFO)
			Logger.info(message, args);
	}
	
	public static void warn(Object message) {
		if(min_severity <= WARN)
			Logger.warn(message);
	}
	
	public static void warn(String message, Object... args) {
		if(min_severity <= WARN)
			Logger.warn(message, args);
	}
	
	public static void error(Object message) {
		if(min_severity <= ERROR)
			Logger.error(message);
	}
	
	public static void error(String message, Object... args) {
		if(min_severity <= ERROR)
			Logger.error(message, args);
	}
	
	public static void setMinSeverity(int severity) {
		min_severity = GameMath.ensureRange(severity, TRACE, ERROR);
	}
	
}

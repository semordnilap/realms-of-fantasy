package net.marvy.core.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tinylog.Logger;

public class RawFileParser {
	String[] lines;

	String line_pattern = "[ \\t]*\\[(.+)\\].*";

	Pattern p = Pattern.compile(this.line_pattern);

	Map<String, String[]> parse_map = null;

	public RawFileParser(String filename) {
		this.lines = GameResourceLoader.readRawFile(filename);
	}

	public void parse() { // TODO fix this fucking mess!
		Map<String, String[]> output = new HashMap<String, String[]>();
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = this.lines).length, b = 0; b < i;) {
			String line = arrayOfString[b];
			Matcher matcher = this.p.matcher(line);
			if (matcher.matches()) {
				String[] args, data = matcher.group(1).split(":");
				String field = data[0];
				if (data.length > 1) {
					args = Arrays.copyOfRange(data, 1, data.length);
				} else {
					args = new String[] { "true" };
				}
				output.put(field, args);
			} else {
				System.out.println("Invalid line");
			}
			b++;
		}
		this.parse_map = output;
	}

	public Map<String, String[]> getParseMap() {
		if (this.parse_map == null) {
			Logger.error("Raw file has not been parsed yet!");
			return new HashMap<String, String[]>();
		}
		return this.parse_map;
	}

	public String[] getField(String field) {
		field = field.toUpperCase();
		
		if (this.parse_map == null) {
			Logger.error("Raw file has not been parsed yet!");
			return new String[0];
		}
		
		if (this.parse_map.containsKey(field)) {
			return this.parse_map.get(field);
		} else {
			Logger.error("Raw file doesn't contain field: {}", field);
			return new String[0];
		}
	}

	public String getArgAsString(String field, int index) {
		String[] f = getField(field);
		String s = "N/A";
		
		if (index < f.length) {
			s = f[index];
		} else {
			Logger.error("Raw file argument index ({}[{}]) out of bounds.",
					field, index);
		}
		
		return s;
	}

	public boolean getArgAsBoolean(String field, int index) {
		String[] f = getField(field);
		boolean o = false;
		
		if (index < f.length) {
			String arg = f[index].toLowerCase();
			if (arg == "true" || arg.matches("^[1-9]\\d*$") || arg == "t") {
				o = true;
			} else {
				o = false;
			}
		} else {
			Logger.error("Raw file argument index ({}[{}]) out of bounds.",
					field, index);
		}
		
		return o;
	}

	public int getArgAsInteger(String field, int index) {
		String[] f = getField(field);
		int i = Integer.MIN_VALUE;
		
		if (index < f.length) {
			String arg = f[index];
			try {
				i = Integer.decode(arg);
			} catch (NumberFormatException e) {
				Logger.error("Couldn't decode raw file argument ({}[{}]) as int.",
						field, index);
			}
		} else {
			Logger.error("Raw file argument index ({}[{}]) out of bounds.",
					field, index);
		}
		
		return i;
	}

//	public static void main(String[] args) {
//		RawFileParser r = new RawFileParser("settings");
//		r.parse();
//		Map<String, String[]> output = r.getParseMap();
//		for (Map.Entry<String, String[]> entry : output.entrySet()) {
//			String key = entry.getKey();
//			String[] value = entry.getValue();
//			System.out.print(key + " : ");
//			System.out.print(Arrays.toString(value) + "\n");
//		}
//		System.out.println("-------------------------------------------");
//		System.out.println("Running debug mode: " + r.getArgAsBoolean("DEBUG_MODE", 0));
//		System.out.println("Screen dimensions: " + r.getArgAsInteger("SCREEN_DIMENSIONS", 0) + "x"
//				+ r.getArgAsInteger("SCREEN_DIMENSIONS", 1));
//	}
}

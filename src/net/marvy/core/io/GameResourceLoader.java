package net.marvy.core.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.tinylog.Logger;

public class GameResourceLoader {
	private static final String PREFIX_RAW = "raws/";
	private static final String PREFIX_IMG = "textures/";
	private static final String PREFIX_SOUND = "sounds/";

	private static final String SUFFIX_RAW = ".txt";
	private static final String SUFFIX_IMG = ".png";
	private static final String SUFFIX_SOUND = ".ogg";

	public void init() {
		// TODO init resloader?
	}

	public static String[] readRawFile(String filename) {
		InputStream is = GameResourceLoader.class.getClassLoader()
				.getResourceAsStream(PREFIX_RAW + filename + SUFFIX_RAW);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			is.close();
			br.close();
		} catch (IOException e) {
			Logger.error("Couldn't read raw file \"{}\".", new Object[] { filename });
		}
		String[] lines = sb.toString().split("\n");
		return lines;
	}
	
	public static BufferedImage readImageFile(String filename) {
		try {
			return ImageIO.read(GameResourceLoader.class.getClassLoader()
					.getResourceAsStream(PREFIX_IMG + filename + SUFFIX_IMG));
		} catch (IOException e) {
			Logger.warn("Couldn't find or read image file {}. Image set to null.", (PREFIX_IMG + filename + SUFFIX_IMG));
			
			return null;
		}
	}
}

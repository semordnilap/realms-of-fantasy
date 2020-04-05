package net.marvy.core.gfx;

import net.marvy.core.GameMath;

public class Misc {

	public static int[] resizeNearestNeighbor(int[] input, int input_width, int input_height, double output_scale) {
		if(input.length != input_width * input_height) return null;
		
		int output_width = (int)(input_width * output_scale);
		int output_height = (int)(input_height * output_scale);
		
		int[] output = new int[output_width * output_height];
		
		double px, py;
		int index;
		for(int i = 0; i < output_width; i++) {
			for(int j = 0; j < output_height; j++) {
				px = Math.floor(i / output_scale);
				py = Math.floor(j / output_scale);
				index = (int)((py*input_width)+px);
				output[j*output_width + i] = input[index];
			}
		}
		
		return output;
	}

	public static void renderBorder(Screen s, int x0, int y0, int x1, int y1, int color, boolean isOpaque) {
		for(int x = x0; x < x1; x++) {
			for(int y = y0; y < y1; y++) {
				if(x == x0 || x == (x1 - 1) || y == y0 || y == (y1 - 1)) {
					if(isOpaque)
						s.setPixel(x, y, color);
					else
						s.addToPixel(x, y, color);
				}
			}
		}
	}
	
	public static int addColors(int bottom_color, int top_color) {
		int ba, br, bg, bb;
		int ta, tr, tg, tb;
		int r, g, b;
		double mult, anmult;
		
		ta = (top_color >> 24) & 0xff;
		tr = (top_color >> 16) & 0xff;
		tg = (top_color >> 8) & 0xff;
		tb = top_color & 0xff;
		
		if(ta == 255) return top_color;
		if(ta == 0) return bottom_color;
		
		ba = (bottom_color >> 24) & 0xff;
		br = (bottom_color >> 16) & 0xff;
		bg = (bottom_color >> 8) & 0xff;
		bb = bottom_color & 0xff;
		
		mult = (double)ta / ba;
		anmult = 1 - mult;
		
		r = GameMath.ensureRange((int)(tr * mult + br * anmult), 0, 255);
		g = GameMath.ensureRange((int)(tg * mult + bg * anmult), 0, 255);
		b = GameMath.ensureRange((int)(tb * mult + bb * anmult), 0, 255);
		
		// TODO also mix alphas?
		
		return 0xff000000 | ((r << 16) & 0x00ff0000) | ((g << 8) & 0x0000ff00) | (b & 0x000000ff);
	}
	
}

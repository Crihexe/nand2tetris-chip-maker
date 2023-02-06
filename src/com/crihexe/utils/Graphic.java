package com.crihexe.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.crihexe.Chip;

public class Graphic {
	
	public static Font CHIP_FONT;
	
	public static void loadFonts() {
		try {
			CHIP_FONT = Font.createFont(Font.TRUETYPE_FONT, Chip.class.getClassLoader().getResourceAsStream("fonts/LiberationMono-Bold.ttf"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// rubato da: https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
	public static void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}
	
	public static void drawCenteredImage(Graphics2D g, BufferedImage img, Rectangle rect) {
		g.drawImage(img, rect.width/2 - img.getWidth()/2, rect.height/2 - img.getHeight()/2, null);
	}
	
}

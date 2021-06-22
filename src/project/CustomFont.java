package project;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
	private Font Pixelony;
	
	CustomFont() {
		try {
		    // create the font to use
		    Pixelony = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Pixelony.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    // register the font
		    ge.registerFont(Pixelony);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
	}
}
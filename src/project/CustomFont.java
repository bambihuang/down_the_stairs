package project;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
	private Font Pixelony;
	private Font NotoSansCJK;
	
	CustomFont() {
		try {
		    // create the font to use
		    Pixelony = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Pixelony.ttf"));
		    NotoSansCJK = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/NotoSansCJK-Regular.ttc"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    // register the font
		    ge.registerFont(Pixelony);
		    ge.registerFont(NotoSansCJK);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
	}
}
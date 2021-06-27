package project;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public class CustomFont {
	private Font Pixelony;
	private Font NotoSansCJK;
	
	CustomFont() {
		try {
			InputStream stream1 = getClass().getResourceAsStream("/Fonts/Pixelony.ttf");
			InputStream stream2 = getClass().getResourceAsStream("/Fonts/NotoSansCJK-Regular.ttc");
		    // create the font to use
		    Pixelony = Font.createFont(Font.TRUETYPE_FONT,stream1);
		    NotoSansCJK = Font.createFont(Font.TRUETYPE_FONT,stream2);
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
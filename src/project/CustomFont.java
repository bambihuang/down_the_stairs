package project;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

public class CustomFont {
	private Font Pixelony;
	private Font NotoSansCJK;
	
	CustomFont() {
		try {
			URL url_pixelony = getClass().getResource("/Fonts/Pixelony.ttf");
			String pixelonyPath = URLDecoder.decode(url_pixelony.getFile(), "UTF-8");
			URL url_notosanscjk = getClass().getResource("/Fonts/Pixelony.ttf");
			String notosanscjkPath = URLDecoder.decode(url_notosanscjk.getFile(), "UTF-8");
		    // create the font to use
		    Pixelony = Font.createFont(Font.TRUETYPE_FONT, new File(pixelonyPath));
		    NotoSansCJK = Font.createFont(Font.TRUETYPE_FONT, new File(notosanscjkPath));
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
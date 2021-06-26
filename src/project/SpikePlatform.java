package project;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class SpikePlatform extends Platform{
	public SpikePlatform() {
		Random rnd = new Random();
		imgPlatform = new ImageIcon("img/spike_platform.png");
		width = imgPlatform.getIconWidth();
		height = imgPlatform.getIconHeight();
		xPos = rnd.nextInt(605 - width);
		yPos = 530;
		type = "spike";
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(xPos, yPos, width, 5);
	}

}
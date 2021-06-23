package project;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.ImageIcon;

public class BakGround {
	private ImageIcon background = new ImageIcon("img/bg.jpg");//bg.jpg
	private int width, height;
	
	public BakGround() {
		this.width = background.getIconWidth();
		this.height = background.getIconHeight();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(this.background.getImage(), 0, 14, null);
	}
	public Rectangle getRect() {
		return new Rectangle(0, 0, width, height);
	}
}



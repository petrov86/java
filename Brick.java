package project.game;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106372808401735999L;

	String bricks[] = { "images/brick-orange2.png", "images/brick-yellow2.png",
			"images/brick-red2.png", "images/brick-blue2.png",
			"images/brick-green2.png" };	
	
	public Brick(int x, int y) {
		Random random = new Random();
		int rand = random.nextInt(bricks.length);
		this.x = x;
		this.y = y;

		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(
					bricks[rand]));
			image = ii.getImage();
			width = image.getWidth(null);
			heigth = image.getHeight(null);

		} catch (Exception e) {
			System.out.println("Error loading brick image!");
		}
	}

	Rectangle getYRect(int direction) {
		if (direction == 1) {
			return new Rectangle(x, y, 1, heigth);
		} else if (direction == -1) {
			return new Rectangle(x + width, y + heigth, 1, heigth);
		} else {
			return null;
		}

	}
	
	Rectangle getXRect(int direction) {
		if (direction == 1) {
			return new Rectangle(x, y, width, 1);
		} else if (direction == -1) {
			return new Rectangle(x, y + heigth, width, 1);
		} else {
			return null;
		}

	}

}

package project.game;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106372808401735999L;

	private String bricks[] = { "images/brick-green2.png",
			"images/brick-yellow2.png", "images/brick-orange2.png",
			"images/brick-red2.png", "images/brick-blue2.png" };

	private boolean haveFallingRock;
	private int brickColor;

	public Brick(int x, int y) {
		Random random = new Random();
		int rand = random.nextInt(bricks.length);
		brickColor = rand;
		this.x = x;
		this.y = y;
		if (generateRock()) {
			haveFallingRock = true;
		} else {
			haveFallingRock = false;
		}

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

	public int getBrickColor() {
		return brickColor;
	}

	public void changeBrick() {
		if (brickColor > 0) {
			brickColor--;
			haveFallingRock = false;
			try {
				ImageIcon ii = new ImageIcon(this.getClass().getResource(
						bricks[brickColor]));
				image = ii.getImage();
				width = image.getWidth(null);
				heigth = image.getHeight(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (generateRock()) {
				haveFallingRock = true;
			} else {
				haveFallingRock = false;
			}

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

	public boolean getHaveFallingRock() {
		return haveFallingRock;
	}

	private boolean generateRock() {

		Random random = new Random();
		int rand = random.nextInt(10);
		if (rand == 1) {
			return true;
		}

		return false;
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

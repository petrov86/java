package project.game;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class FallingObject extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String arr[] = { "images/rock_11.png", "images/rock_12.png",
			"images/rock_13.png", "images/rock_14.png", "images/rock_15.png" };

	int speed = 0;
	private boolean isVisible = false;
	private int function = 0;

	public FallingObject(int x, int y) {
		Random random = new Random();
		int rand = random.nextInt(arr.length);
		this.x = x;
		this.y = y;
		function = rand;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(arr[rand]));
			image = ii.getImage();
			width = image.getWidth(null);
			heigth = image.getHeight(null);
			isVisible = true;

		} catch (Exception e) {
			System.out.println("Error loading rock image - " + arr[rand]);
		}

		speed = random.nextInt(2);
		speed += 1;
	}

	public void move() {
		y += speed;
	}

	public int getFunction() {
		return function;
	}

	@Override
	Rectangle getRect() {
		return new Rectangle(x, y, width, heigth);
	}

	public boolean getVisibility() {
		return isVisible;
	}

	public void setVisibility(boolean visibility) {
		isVisible = visibility;
	}

}

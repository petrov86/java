package project.game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5251546542863186464L;
	private String player = Constants.PLAYER_FILE;
	int dx;
	int life;

	public Player() {

		// this.player = player;
		System.out.println("==================================");
		System.out.println("PLAYER CREATED");
		System.out.println("==================================");
		life = 3;
		setPlayerImage();
		resetState();

	}

	public void setPlayerImage() {
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
			image = ii.getImage();
			width = image.getWidth(null);
			heigth = image.getHeight(null);
		} catch (Exception e) {
			System.out.println("Error loading player image!");
			System.out.println(player);
		}

	}

	public void makePlayerBigger() {
		if (this.width < 150) {
			System.out.println("==================================");
			System.out.println("PLAYER IMAGE RESIZED");
			System.out.println("==================================");
			this.width += 50;

		}
	}

	public void makePlayerSmaller() {
		if (this.width >= 100) {
			System.out.println("==================================");
			System.out.println("PLAYER IMAGE RESIZED");
			System.out.println("==================================");
			this.width -= 50;
		}
	}

	public void move() {
		x += dx;
		if (x <= 0)
			x = 0;
		if (x >= Constants.VISIBLE_WIDTH - this.width)
			x = Constants.VISIBLE_WIDTH - this.width;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;

		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

	public void resetState() {
		x = (Constants.VISIBLE_WIDTH - this.width) / 2;
		y = Constants.BOTTOM - getHeight() - 40;
	}

	@Override
	Rectangle getRect() {
		return new Rectangle(x, y, width, 1);
	}

	Rectangle getRect(int direction) {
		if (direction == 1) {
			return new Rectangle(x, y, 1, heigth);
		} else if (direction == -1) {
			return new Rectangle(x + width, y + heigth, 1, heigth);
		} else {
			return null;
		}

	}
}

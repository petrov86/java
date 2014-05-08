package project.game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5251546542863186464L;
	int dx;
	String player = "images/player.png";

	public Player() {

		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
			image = ii.getImage();
			width = image.getWidth(null);
			heigth = image.getHeight(null);
		} catch (Exception e) {
			System.out.println("Error loading player image!");
		}

		resetState();

	}

	public void move() {
		x += dx;
		if (x <= 0)
			x = 0;
		if (x >= Constants.PLAYER_RIGHT)
			x = Constants.PLAYER_RIGHT;
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
		x = Constants.PLAYER_RIGHT / 2;
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

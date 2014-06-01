package project.game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5251546542863186464L;
	private String bigPlayer = Constants.PLAYER_FILES[0];
	private String smallPlayer = Constants.PLAYER_FILES[1];
	private String currentPlayer = null;
	int dx;
	int life;

	public Player(String file) {

		// this.player = player;
		System.out.println("==================================");
		System.out.println("PLAYER CREATED");
		System.out.println("==================================");
		life = 3;
		setPlayerImage(file);
		resetState();

	}

	public void setPlayerImage(String file) {
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(file));
			image = ii.getImage();
			width = image.getWidth(null);
			heigth = image.getHeight(null);
			currentPlayer = file;
		} catch (Exception e) {
			System.out.println("Error loading player image!");
			System.out.println(file);
		}

	}

	public void makePlayerBig() {
		if (!currentPlayer.equals(bigPlayer)) {
			System.out.println("==================================");
			System.out.println("PLAYER IMAGE CHANGED ");
			System.out.println("==================================");
			setPlayerImage(bigPlayer);
			currentPlayer = bigPlayer;

		}
	}

	public void makePlayerSmall() {
		if (!currentPlayer.equals(smallPlayer)) {
			System.out.println("==================================");
			System.out.println("PLAYER IMAGE CHANGED ");
			System.out.println("==================================");
			setPlayerImage(smallPlayer);
			currentPlayer = smallPlayer;
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

package project.game;

import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8031780375878264030L;
	private int xdir;
	private int ydir;
	private int tempXDir;
	private int tempYDir;
	private boolean isBallStopped = false;
	private final int X_DIR_MOVE_SPEED = 2;
	private final int Y_DIR_MOVE_SPEED = 2;

	public Ball(int x, int y) {
		tempXDir = 0;
		tempYDir = 0;
		// set random X direction
		Random random = new Random();
		int r = random.nextInt(100);
		if (r < 50) {
			xdir = -1;
		} else {
			xdir = 1;
		}

		ydir = -1;
		width = Constants.BALL_RADIUS;
		heigth = Constants.BALL_RADIUS;
		resetState(x, y);
	}

	public void move() {

		x += (X_DIR_MOVE_SPEED * xdir);
		y += (Y_DIR_MOVE_SPEED * ydir);
		if (x <= 0) {
			setXDir(1);
		}

		if (x >= Constants.VISIBLE_WIDTH - width) {
			changeXDir();
		}

		if (y <= 0) {
			changeYDir();
		}

	}

	
	public void stopMove() {
		if (!isBallStopped) {
			tempXDir = getXDir();
			tempYDir = getYDir();
			setXDir(0);
			setYDir(0);
			isBallStopped = true;
		}
	}

	public void resumeMove() {

		if (isBallStopped) {
			setXDir(tempXDir);
			setYDir(tempYDir);
			isBallStopped = false;
		}
	}

	public void resetState(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setXDir(int x) {
		this.xdir = x;
	}

	public int getXDir() {
		return this.xdir;
	}

	public void changeXDir() {
		if (this.xdir == 0) {
			return;
		} else if (this.xdir == 1) {
			setXDir(-1);
		} else {
			setXDir(1);
		}
	}

	public void setYDir(int y) {
		this.ydir = y;
	}

	public int getYDir() {
		return this.ydir;
	}

	public void changeYDir() {
		if (this.ydir == 0) {
			return;
		} else if (this.ydir == 1) {
			setYDir(-1);
		} else {
			setYDir(1);
		}
	}

	@Override
	Rectangle getRect() {
		return new Rectangle(x, y, width, heigth);
	}

}

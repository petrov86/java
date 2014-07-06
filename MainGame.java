package project.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

public class MainGame extends JPanel {

	private static final long serialVersionUID = -6482705247828045324L;

	Player player = null;
	Background background = null;
	MoveThread moveObjects = null;
	MovePlayerThread movePlayerThread = null;
	PaintThread paintThread = null;
	KeyListener myListenner = null;
	Ball ball = null;
	ArrayList<Brick> bricks = null;
	ArrayList<FallingObject> rocks = null;
	String msg = null;
	boolean isGameActive = false;
	boolean isGamePaused = false;
	boolean isGameStarted;
	boolean isLevelPassed = false;
	boolean isGameEnd = false;
	int speed[] = new int[1]; // The Level class initialize the value
	int tempSpeed = 0;
	int level[] = new int[1]; // The Level class initialize the value
	int score = 0;

	MainGame() {
		level[0] = 0;
		myListenner = new MyAdapter();
		addKeyListener(myListenner);
		setFocusable(true);
		setDoubleBuffered(true);
		bricks = new ArrayList<Brick>();
		rocks = new ArrayList<FallingObject>();
		init();
		msg = "Press Enter to Start!";

	}

	public void init() {
		isGameStarted = true;
		background = new Background();
		Level.setLevel(level, speed, bricks);
		tempSpeed = speed[0];
		player = new Player();
		ball = new Ball(player.getX() + player.getWidth() / 2
				- Constants.BALL_RADIUS / 2, player.getY()
				- Constants.BALL_RADIUS);

		paintThread = new PaintThread();
		paintThread.start();
		paintThread.setPriority(3);
		System.out.println("Paint Thread Priority is : "
				+ paintThread.getPriority());
		movePlayerThread = new MovePlayerThread();
		movePlayerThread.start();
	}

	public void paint(Graphics g) {

		super.paint(g);
		g.drawImage(background.getImage(), background.getX(),
				background.getY(), background.getWidth(),
				background.getHeight(), this);

		g.drawImage(player.getImage(), player.getX(), player.getY(),
				player.getWidth(), player.getHeight(), this);
		g.setColor(Color.white);
		g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

		Iterator<Brick> it = bricks.iterator();
		while (it.hasNext()) {

			try {
				Brick br = it.next();
				g.drawImage(br.getImage(), br.getX(), br.getY(), br.getWidth(),
						br.getHeight(), this);
			} catch (Exception e) {
				System.out.println("Exception");
				e.printStackTrace();
				break;
			}

		}

		if (!rocks.isEmpty()) {
			Iterator<FallingObject> i = rocks.iterator();
			while (i.hasNext()) {
				FallingObject r = i.next();
				if (r.getVisibility()) {
					g.drawImage(r.getImage(), r.getX(), r.getY(), r.getWidth(),
							r.getHeight(), this);
				}
			}
		}

		if (!isGameActive || isGamePaused) {
			g.setFont(new Font("Bold", Font.BOLD, 36));
			g.setColor(Color.cyan);
			int stringLen = (int) g.getFontMetrics().getStringBounds(msg, g)
					.getWidth();
			int centerXPosition = Constants.WIDTH / 2 - stringLen / 2;
			int centerYPosition = Constants.GAME_PANEL_HEIGTH * 2 / 3;
			g.drawString(msg, centerXPosition, centerYPosition);
		}

		// Paint Score
		g.setFont(new Font("Bold", Font.BOLD, 16));
		g.setColor(Color.magenta);
		g.drawString("Score: " + score, 20, 30);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private class MyAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_ENTER) {
				if (!isGamePaused && isGameActive) {
					pauseGame("PAUSE");
					paintThread.suspendThread(50);

				} else if (isGamePaused && isGameActive) {
					resumeGame();
				} else {
					startGame();
				}

			} else if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else if (key == KeyEvent.VK_Q) {
				bricks.clear();
			} else if (key == KeyEvent.VK_A) {
				ball.stopMove();
			} else if (key == KeyEvent.VK_Z) {
				ball.resumeMove();
			} else if (key == KeyEvent.VK_W) {
				player.makePlayerSmaller();
			} else if (key == KeyEvent.VK_E) {
				player.makePlayerBigger();
			} else if (key == KeyEvent.VK_R) {
				rocks.add(new FallingObject(ball.getX(), ball.getY()));
				// Check if the falling rock will not go out from the frame
				// right border
				if ((ball.getX() + rocks.get(rocks.size() - 1).width) > Constants.VISIBLE_WIDTH) {

					rocks.get(rocks.size() - 1).setX(
							Constants.VISIBLE_WIDTH
									- rocks.get(rocks.size() - 1).width);

				}
			} else {
				player.keyReleased(e);
			}
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

	}

	class PaintThread extends Thread {

		boolean isPainted = false;

		@Override
		public void run() {
			while (true) {

				try {
					repaint();
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				synchronized (this) {
					while (isPainted) {

						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		public void suspendThread() {
			isPainted = true;
			System.out.println("Suspend paint thread");
		}

		public void suspendThread(long time) {

			try {
				Thread.sleep(time);
				isPainted = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Suspend paint thread after " + time);
		}

		public synchronized void resumeThread() {
			isPainted = false;
			System.out.println("Resume paint thread");
			notify();
		}

	}

	class MoveThread extends Thread {

		boolean suspended = false;

		@Override
		public void run() {

			while (true) {

				try {

					ball.move();
					player.move();
					collisionCheck();
					if (!rocks.isEmpty()) {
						Iterator<FallingObject> i = rocks.iterator();
						while (i.hasNext()) {
							FallingObject r = i.next();
							r.move();
						}
					}

					paintThread.resumeThread();
					Thread.sleep(tempSpeed);

					synchronized (this) {
						while (suspended) {
							System.out.println("Suspend move thread");
							wait();
						}
					}
					paintThread.suspendThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		public void suspendThread() {
			suspended = true;
		}

		public synchronized void resumeThread() {
			suspended = false;
			System.out.println("Resume move thread");
			notify();
		}
	}

	class MovePlayerThread extends Thread {

		boolean startFlag = true;

		@Override
		public void run() {

			while (startFlag) {
				player.move();
				ball.setX(player.getX() + (player.getWidth() / 2)
						- (ball.getWidth() / 2));
				try {
					Thread.sleep(tempSpeed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		public void stopThread() {
			startFlag = false;
		}

	}

	public void collisionCheck() {

		if (ball.getY() >= Constants.GAME_PANEL_HEIGTH - 2 * ball.getHeight()) {
			if (--player.life > 0) {
				resetBallAndPlayer();
				pauseGame("Lives left: " + player.life);
				movePlayerThread = new MovePlayerThread();
				movePlayerThread.start();

			} else {
				stopGame("GAME OVER");
				score = 0;
				isGameStarted = false;
			}

		}

		if (ball.getRect().intersects(player.getRect())) {

			ball.setYDir(-1);

			// =================================================================
			// change X coordinate of the ball, if the player touch it with
			// the
			// end of the stick
			if (ball.getXDir() == 1
					&& ball.getX() < (player.getX() + player.getWidth() / 5)) {
				ball.changeXDir();

			}
			if (ball.getXDir() == -1
					&& ball.getX() > (player.getX() + player.getWidth() * 4 / 5)) {
				ball.changeXDir();

			}
			// =================================================================

		}
		// check is the ball paused
		try {
			if (ball.getRect().intersects(player.getRect(ball.getXDir()))) {
				ball.changeXDir();
			}
		} catch (NullPointerException ex) {
			if (ball.getXDir() == 0)
				return;
			else
				ex.printStackTrace();
		}

		Iterator<Brick> it = bricks.iterator();
		int counter = 0;
		while (it.hasNext()) {
			Brick br = it.next();
			if (ball.getRect().intersects(br.getRect())) {

				// Check if the brick keep falling rock object and remove the
				// brick after that
				if (bricks.get(counter).haveFallingRock) {
					// Add new falling rock in the List
					rocks.add(new FallingObject(ball.getX(), ball.getY()));
					// Check if the falling rock will not go out from the frame
					// right border
					if ((ball.getX() + rocks.get(rocks.size() - 1).width) > Constants.VISIBLE_WIDTH) {
						rocks.get(rocks.size() - 1).setX(
								Constants.VISIBLE_WIDTH
										- rocks.get(rocks.size() - 1).width);
					}
				}
				bricks.remove(counter);
				score += 5;
				// manage the Y movement
				if (ball.getRect().intersects(br.getXRect(ball.getYDir()))) {
					ball.changeYDir();
				}

				// manage the X movement
				if (ball.getRect().intersects(br.getYRect(ball.getXDir()))) {
					ball.changeXDir();

				}
				break;

			}
			counter++;
		}

		if (bricks.size() == 0) {

			if (level[0] < Constants.END_LEVEL) {
				level[0]++;
				stopGame("Press Enter for next Level!");
			} else {
				isGameEnd = true;
				stopGame("WIN!");
			}
		}

		// check for falling symbols
		if (rocks.size() > 0) {
			Iterator<FallingObject> i = rocks.iterator();
			counter = 0;
			while (i.hasNext()) {
				FallingObject r = i.next();
				if (r.getRect().intersects(player.getRect())
						|| r.getY() > Constants.GAME_PANEL_HEIGTH) {
					if (r.getRect().intersects(player.getRect())) {
						r.setVisibility(false);
						setFunction(r);
					}
					rocks.remove(counter);
					score += 10;
					break;
				}
				counter++;
			}
		}

	}

	public void stopGame(String msg) {
		isGameActive = false;
		this.msg = msg;
		moveObjects.suspendThread();
		paintThread.suspendThread();
	}

	public void startGame() {
		if (this.player == null || this.ball == null || this.bricks.size() == 0) {
			init();
		} else if (!isGameActive && !isGameStarted) {
			init();
		} else {
			// this.player.resetState();
			// this.ball.resetState(player.getX() + player.getWidth() / 2
			// - Constants.BALL_RADIUS / 2, player.getY()
			// - Constants.BALL_RADIUS);
			System.out.println("What TODO");
		}
		moveObjects = new MoveThread();
		moveObjects.start();
		isGameActive = true;
		movePlayerThread.stopThread();
		try {
			movePlayerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pauseGame(String msg) {

		moveObjects.suspendThread();
		isGamePaused = true;
		this.msg = msg;

	}

	public void resumeGame() {
		moveObjects.resumeThread();
		isGamePaused = false;
		movePlayerThread.stopThread();
		try {
			movePlayerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetBallAndPlayer() {
		player.setPlayerImage();
		player.resetState();
		rocks.clear();
		tempSpeed = speed[0];
		ball.resetState(player.getX() + player.getWidth() / 2
				- Constants.BALL_RADIUS / 2, player.getY()
				- Constants.BALL_RADIUS);
	}

	public void setFunction(FallingObject fo) {
		if (fo != null) {
			System.out.println(fo.getFunction());
			switch (fo.getFunction()) {

			case 0: {
				// make player small
				if (player != null)
					player.makePlayerSmaller();
				break;
			}
			case 1: {
				// make player big
				if (player != null)
					player.makePlayerBigger();
				break;
			}
			case 2: {
				// give player one life
				if (player != null)
					player.life++;
				msg = "+1 Life";
				pauseGame(msg);
				// Draw the msg for 500ms
				paintThread.resumeThread();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resumeGame();
				break;
			}
			case 3: {
				// speed down the ball
				ball.slowDown();
				break;
			}
			case 4: {
				// speed up the ball
				ball.accelerate();
				break;
			}
			default: {
				break;
			}
			}
		}
	}

}
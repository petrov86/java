package project.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class MainGame extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = -6482705247828045324L;

	Player player = null;
	Timer timer = null;
	Timer paintTimer = null;
	ScheduleTask myScheduleTask = null;
	PaintTask paintTask = null;
	Ball ball = null;
	ArrayList<Brick> bricks = null;
	String msg = null;
	boolean isGameActive = false;
	boolean isGamePaused = false;
	boolean isGameStarted;
	boolean isLevelPassed = false;
	boolean isGameEnd = false;
	int speed;
	int level;

	MainGame() {
		level = 0;
		addKeyListener(new MyAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		bricks = new ArrayList<Brick>();
		init();
		msg = "Press Enter to Start!";

	}

	public void init() {
		isGameStarted = true;
		player = new Player();
		ball = new Ball(player.getX() + player.getWidth() / 2
				- Constants.BALL_RADIUS / 2, player.getY()
				- Constants.BALL_RADIUS);
		setLevel();

	}

	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(player.getImage(), player.getX(), player.getY(),
				player.getWidth(), player.getHeight(), this);

		g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

		Iterator<Brick> it = bricks.iterator();
		while (it.hasNext()) {
			Brick br = it.next();
			g.drawImage(br.getImage(), br.getX(), br.getY(), br.getWidth(),
					br.getHeight(), this);

		}

		if (!isGameActive || isGamePaused) {
			g.setFont(new Font("Bold", Font.BOLD, 40));
			int stringLen = (int) g.getFontMetrics().getStringBounds(msg, g)
					.getWidth();
			int centerXPosition = Constants.WIDTH / 2 - stringLen / 2;
			int centerYPosition = Constants.GAME_PANEL_HEIGTH * 2 / 3;
			g.drawString(msg, centerXPosition, centerYPosition);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private class MyAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_ENTER) {
				if (!isGamePaused && isGameActive) {
					pauseGame("PAUSE");
				} else if (isGamePaused && isGameActive) {
					resumeGame();
				} else {
					startGame();
				}

			} else if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else if (key == KeyEvent.VK_Q) {
				bricks.clear();
			} else {
				player.keyReleased(e);
			}
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

	}

	class ScheduleTask extends TimerTask {

		@Override
		public void run() {
			ball.move();
			player.move();
			collisionCheck();
			// repaint();
		}
	}

	class PaintTask extends TimerTask {

		@Override
		public void run() {
			repaint();
		}
	}

	public void collisionCheck() {

		if (ball.getY() == Constants.GAME_PANEL_HEIGTH - 2 * ball.getHeight()) {
			stopGame("GAME OVER");
			isGameStarted = false;
		}

		if (ball.getRect().intersects(player.getRect())) {

			ball.setYDir(-1);

			// =================================================================
			// change X coordinate of the ball, if the player touch it with the
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

		if (ball.getRect().intersects(player.getRect(ball.getXDir()))) {
			ball.changeXDir();
		}

		Iterator<Brick> it = bricks.iterator();
		int counter = 0;
		while (it.hasNext()) {
			Brick br = it.next();
			if (ball.getRect().intersects(br.getRect())) {
				bricks.remove(counter);
				// manage the Y movement
				// if (ball.getYDir() == -1) {
				// ball.setYDir(1);
				// } else {
				// ball.setYDir(-1);
				// }
				if (ball.getRect().intersects(br.getXRect(ball.getYDir()))) {
					ball.changeYDir();
					System.out.println("change y");
				}

				// mangage the X movement
				if (ball.getRect().intersects(br.getYRect(ball.getXDir()))) {
					ball.changeXDir();
					System.out.println("change x");
				}
				// repaint();
				break;

			}
			counter++;
		}

		if (bricks.size() == 0) {

			if (level < Constants.END_LEVEL) {
				level++;
				stopGame("Press Enter for next Level!");
			} else {
				isGameEnd = true;
				stopGame("WIN!");
			}
		}
	}

	public void stopGame(String msg) {

		myScheduleTask.cancel();
		timer.cancel();
		isGameActive = false;
		this.msg = msg;
		// repaint();
	}

	public void startGame() {
		if (this.player == null || this.ball == null || this.bricks.size() == 0) {
			init();
		} else if (!isGameActive && !isGameStarted) {
			init();
		} else {
			this.player.resetState();
			this.ball.resetState(player.getX() + player.getWidth() / 2
					- Constants.BALL_RADIUS / 2, player.getY()
					- Constants.BALL_RADIUS);
		}
		this.timer = new Timer();
		this.myScheduleTask = new ScheduleTask();
		this.timer.scheduleAtFixedRate(this.myScheduleTask, 100, speed);
		//this.paintTimer = new Timer();
		//this.paintTask = new PaintTask();
		//this.paintTimer.scheduleAtFixedRate(this.paintTask, 100, 2 * speed);
		
		new Thread(new Runnable() {
			public void run() {
				while(true){
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		isGameActive = true;

	}

	public void pauseGame(String msg) {
		myScheduleTask.cancel();
		timer.cancel();
		isGamePaused = true;
		this.msg = msg;
		// repaint();

	}

	public void resumeGame() {
		this.timer = new Timer();
		this.myScheduleTask = new ScheduleTask();
		this.timer.scheduleAtFixedRate(this.myScheduleTask, 100, speed);
		isGamePaused = false;
	}

	public void setLevel() {
		// initialize the BRICKS
		int x = Constants.OFFSET_SIDE;
		int y = Constants.OFFSET_TOP;
		int offset = 2;
		int bricksCount = 0;
		int figure = -1;
		Constants.LEVELS.get(level);

		for (String key : Constants.LEVELS.get(level).keySet()) {

			if (key.equals("speed")) {
				this.speed = Constants.LEVELS.get(level).get(key);
			}

			if (key.equals("bricks")) {
				bricksCount = Constants.LEVELS.get(level).get(key);
			}

			if (key.equals("figure")) {
				figure = Constants.LEVELS.get(level).get(key);
			}
		}

		bricks.clear();

		int count = bricksCount;
		int cols = 0;
		int rowCount = 0;
		int tempOffset = 0;

		if (Constants.FIGURES[figure].equals("rectangle")
				|| Constants.FIGURES[figure].equals("square")) {

			for (int i = 0; i < bricksCount / 10; i++) {
				for (int j = 0; j < 10; j++) {
					bricks.add(new Brick(x, y));
					x += (bricks.get(bricks.size() - 1).getWidth() + offset);
				}
				x = Constants.OFFSET_SIDE;
				y += (bricks.get(bricks.size() - 1).getHeight() + offset);
			}
		} else if (Constants.FIGURES[figure].equals("triangle")) {

			cols = 10;
			tempOffset = Constants.OFFSET_SIDE;
			rowCount = 0;
			while (count > 0) {
				for (int i = 0; i < cols; i++) {
					bricks.add(new Brick(x, y));
					x += (bricks.get(bricks.size() - 1).getWidth() + offset);
					count--;
				}
				y += (bricks.get(bricks.size() - 1).getHeight() + offset);
				rowCount++;

				if (rowCount % 2 == 0) {
					cols--;
					tempOffset += bricks.get(bricks.size() - 1).getWidth() / 2
							+ offset / 2;
					x = tempOffset;
				} else {
					x = tempOffset;
				}

			}
		} else if (Constants.FIGURES[figure].equals("rhombus")) {

			cols = 2;
			tempOffset = Constants.WIDTH / 2 - Constants.BRICK_WIDTH * 2;
			x = tempOffset;
			rowCount = 0;
			while (count > 0) {
				System.out.println("temp offset = " + tempOffset);
				for (int i = 0; i < cols; i++) {
					bricks.add(new Brick(x, y));
					x += (bricks.get(bricks.size() - 1).getWidth() + offset);
					count--;
				}
				if (rowCount < 4) {
					cols += 2;
					tempOffset -= (Constants.BRICK_WIDTH + (offset / 2)) * 2;
					x = tempOffset;

				} else if (rowCount > 6) {
					cols -= 2;
					tempOffset += (Constants.BRICK_WIDTH + (offset / 2)) * 2;
					x = tempOffset;
				} else {
					x = tempOffset;
				}
				rowCount++;
				y += (bricks.get(bricks.size() - 1).getHeight() + offset);

			}

		} else if (Constants.FIGURES[figure].equals("hexagon")) {
			// TODO
			cols = 4;
			tempOffset = (Constants.WIDTH / 2) - (2 * Constants.BRICK_WIDTH)
					- (3 * offset) - Constants.OFFSET_SIDE;
			x = tempOffset;
			rowCount = 0;
			while (count > 0) {
				for (int i = 0; i < cols; i++) {
					bricks.add(new Brick(x, y));
					x += (bricks.get(bricks.size() - 1).getWidth() + offset);
					count--;
				}

				if (rowCount < 3) {
					cols += 2;
					tempOffset -= (Constants.BRICK_WIDTH + (offset / 2)) * 2;
					x = tempOffset;

				} else if (rowCount > 7) {
					cols -= 2;
					tempOffset += (Constants.BRICK_WIDTH + (offset / 2)) * 2;
					x = tempOffset;
				} else {
					x = tempOffset;
				}
				rowCount++;
				y += (bricks.get(bricks.size() - 1).getHeight() + offset);

			}
		}

	}
}
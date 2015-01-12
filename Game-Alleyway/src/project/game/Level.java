package project.game;

import java.util.ArrayList;

public class Level {

	int bricks;
	int speed;
	String figure;
	String player;

	public Level(int bricks, int speed, String figure, String player) {
		this.bricks = bricks;
		this.speed = speed;
		this.figure = figure;
		this.player = player;

	}

	public static void setLevel(int level[], int speed[],
			ArrayList<Brick> bricks) {

		int x = Constants.OFFSET_SIDE;
		int y = Constants.OFFSET_TOP;
		int offset = 2;
		int bricksCount = 0;
		String figure = null;

		speed[0] = Constants.LEVELS_LIST.get(level[0]).speed;
		bricksCount = Constants.LEVELS_LIST.get(level[0]).bricks;
		figure = Constants.LEVELS_LIST.get(level[0]).figure;
		bricks.clear();

		int count = bricksCount;
		int cols = 0;
		int rowCount = 0;
		int tempOffset = 0;

		if (figure.equals("rectangle") || figure.equals("square")) {

			for (int i = 0; i < bricksCount / 10; i++) {
				for (int j = 0; j < 10; j++) {
					bricks.add(new Brick(x, y));
					x += (bricks.get(bricks.size() - 1).getWidth() + offset);
				}
				x = Constants.OFFSET_SIDE;
				y += (bricks.get(bricks.size() - 1).getHeight() + offset);
			}
		} else if (figure.equals("triangle")) {

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
		} else if (figure.equals("rhombus")) {

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

		} else if (figure.equals("hexagon")) {
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

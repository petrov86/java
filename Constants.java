package project.game;

import java.util.ArrayList;

public class Constants {

	public static final int WIDTH = 500;
	public static final int VISIBLE_WIDTH = WIDTH - 6; //6 is the side of borders
	public static final int HEIGTH = 600;
	public static final int BRICK_WIDTH = 20;
	public static final int NAVIGATION_PANEL_HEIGHT = 23;
	public static final int GAME_PANEL_HEIGTH = HEIGTH
			- NAVIGATION_PANEL_HEIGHT;
	public static final int BOTTOM = GAME_PANEL_HEIGTH - 10;
	public static final int BALL_RADIUS = 15;
	public static final int OFFSET_SIDE = 40;
	public static final int OFFSET_TOP = 70;
	public static final int END_LEVEL = 4; //Levels start counting from zero 0!!! 
	//public static final String[] PLAYER_FILES = {"images/player2.png", "images/player-small.png"};
	public static final String PLAYER_FILE = "images/player2.png"; 
	public static final String[] FIGURES = { "rectangle", "square", "triangle",
			"rhombus", "hexagon" };

	public static final ArrayList<Level> LEVELS_LIST = new ArrayList<>();
	static{
		LEVELS_LIST.add(new Level(50, 9, FIGURES[0], PLAYER_FILE));
		LEVELS_LIST.add(new Level(100, 8, FIGURES[1], PLAYER_FILE));
		LEVELS_LIST.add(new Level(80, 7, FIGURES[2], PLAYER_FILE));
		LEVELS_LIST.add(new Level(80, 6, FIGURES[3], PLAYER_FILE));
		LEVELS_LIST.add(new Level(96, 5, FIGURES[4], PLAYER_FILE));
	}
	
}

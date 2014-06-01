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
	public static final String[] PLAYER_FILES = {"images/player2.png", "images/player-small.png"};
	public static final String[] FIGURES = { "rectangle", "square", "triangle",
			"rhombus", "hexagon" };
	/*
	public static final List<Map<String, Integer>> LEVELS = new ArrayList<Map<String, Integer>>();
	static {
		HashMap<String, Integer> LEVEL_1;
		{
			LEVEL_1 = new HashMap<String, Integer>();
			LEVEL_1.put("bricks", 50);
			LEVEL_1.put("figure", 0);
			LEVEL_1.put("speed", 9);
			LEVELS.add(LEVEL_1);
		}

		HashMap<String, Integer> LEVEL_2;
		{
			LEVEL_2 = new HashMap<String, Integer>();
			LEVEL_2.put("bricks", 100);
			LEVEL_2.put("figure", 1);
			LEVEL_2.put("speed", 8);
			LEVELS.add(LEVEL_2);
		}

		HashMap<String, Integer> LEVEL_3;
		{
			LEVEL_3 = new HashMap<String, Integer>();
			LEVEL_3.put("bricks", 80);
			LEVEL_3.put("figure", 2);
			LEVEL_3.put("speed", 7);
			LEVELS.add(LEVEL_3);
		}

		HashMap<String, Integer> LEVEL_4;
		{
			LEVEL_4 = new HashMap<String, Integer>();
			LEVEL_4.put("bricks", 80);
			LEVEL_4.put("figure", 3);
			LEVEL_4.put("speed", 6);
			LEVELS.add(LEVEL_4);
		}

		HashMap<String, Integer> LEVEL_5;
		{
			LEVEL_5 = new HashMap<String, Integer>();
			LEVEL_5.put("bricks", 96);
			LEVEL_5.put("figure", 4);
			LEVEL_5.put("speed", 5);
			LEVELS.add(LEVEL_5);
		}
	}
	*/
	public static final ArrayList<Level> LEVELS_LIST = new ArrayList<>();
	static{
		LEVELS_LIST.add(new Level(50, 9, FIGURES[0], PLAYER_FILES[0]));
		LEVELS_LIST.add(new Level(100, 8, FIGURES[1], PLAYER_FILES[0]));
		LEVELS_LIST.add(new Level(80, 7, FIGURES[2], PLAYER_FILES[0]));
		LEVELS_LIST.add(new Level(80, 6, FIGURES[3], PLAYER_FILES[0]));
		LEVELS_LIST.add(new Level(96, 5, FIGURES[4], PLAYER_FILES[1]));
	}
	
}
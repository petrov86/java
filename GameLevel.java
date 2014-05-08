package project.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLevel {

	public static final String[] FIGURES = { "rectangle", "square", "triangle",
			"rhombus", "hexagon" };
	public static List<Map<String, Integer>> LEVELS = new ArrayList<Map<String, Integer>>();  
	
	public static final HashMap<String, Integer> LEVEL_1;
	static {
		LEVEL_1 = new HashMap<String, Integer>();
		LEVEL_1.put("bricks", 54);
		LEVEL_1.put("figure", 0);
		LEVEL_1.put("speed", 9);
		LEVELS.add(LEVEL_1);
	}

	public static final HashMap<String, Integer> LEVEL_2;
	static {
		LEVEL_2 = new HashMap<String, Integer>();
		LEVEL_2.put("bricks", 54);
		LEVEL_2.put("figure", 1);
		LEVEL_2.put("speed", 8);
		LEVELS.add(LEVEL_2);
	}
	

	public static final HashMap<String, Integer> LEVEL_3;
	static {
		LEVEL_3 = new HashMap<String, Integer>();
		LEVEL_3.put("bricks", 54);
		LEVEL_3.put("figure", 2);
		LEVEL_3.put("speed", 7);
		LEVELS.add(LEVEL_3);
	}

	public static final HashMap<String, Integer> LEVEL_4;
	static {
		LEVEL_4 = new HashMap<String, Integer>();
		LEVEL_4.put("bricks", 54);
		LEVEL_4.put("figure", 3);
		LEVEL_4.put("speed", 6);
		LEVELS.add(LEVEL_4);
	}

	public static final HashMap<String, Integer> LEVEL_5;
	static {
		LEVEL_5 = new HashMap<String, Integer>();
		LEVEL_5.put("bricks", 54);
		LEVEL_5.put("figure", 4);
		LEVEL_5.put("speed", 5);
		LEVELS.add(LEVEL_5);
	}
	
	

} 

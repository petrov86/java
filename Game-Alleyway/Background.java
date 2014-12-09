package project.game;

import javax.swing.ImageIcon;

public class Background extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String BACKGROUND = "images/background.png";

	public Background() {
		x = 0;
		y = 0;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(BACKGROUND));
			image = ii.getImage();
			width = Constants.WIDTH;
			heigth = Constants.GAME_PANEL_HEIGTH;

		} catch (Exception e) {
			System.out.println("Error loading backgroung image!");
		}
	}
}

package project.game;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EtchedBorder;

public class Alleyway extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7472294058252417426L;

	public MainGame myGame;
	public NavigationPanel myNavigationTopPanel;

	public Alleyway() {
		JFrame frame = new JFrame("Alleyway");

		frame.setSize(Constants.WIDTH, Constants.HEIGTH);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	
		
		myGame = new MainGame();
		myNavigationTopPanel = new NavigationPanel();
		myNavigationTopPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(myNavigationTopPanel, BorderLayout.NORTH);
		content.add(myGame, BorderLayout.CENTER);
		frame.add(content);
	
		frame.setContentPane(content);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}


	public static void main(String[] args) {
		new Alleyway();
	}

}

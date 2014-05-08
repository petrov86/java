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
//		myNavigationTopPanel.setBorder( BorderFactory.createRaisedBevelBorder());
//		myNavigationTopPanel.setBorder( BorderFactory.createLoweredBevelBorder());
		
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(myNavigationTopPanel, BorderLayout.NORTH);
		content.add(myGame, BorderLayout.CENTER);
		//myGame.addMouseListener(new MyMouseListener());
		frame.add(content);
	
		frame.setContentPane(content);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

//	public class MyMouseListener implements MouseListener {
//
//		@Override
//		public void mouseClicked(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("click");
//			myGame.requestFocusInWindow();
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("entered");
//			myGame.requestFocusInWindow();
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("exit");
//			myGame.requestFocusInWindow();
//
//		}
//
//		@Override
//		public void mousePressed(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("pressed");
//			myGame.requestFocusInWindow();
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("released");
//			myGame.requestFocusInWindow();
//
//		}
//
//	}

	public static void main(String[] args) {
		new Alleyway();
	}

}

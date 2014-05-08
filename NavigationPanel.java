package project.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class NavigationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5248309967727811665L;

	public NavigationPanel() {
		init();
	}

	private void init() {

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JMenuBar menubar = new JMenuBar();

		// File menu
		JMenu main = new JMenu("Game");
		main.setMnemonic(KeyEvent.VK_F);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		main.add(exitItem);

		// Help Menu
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_F);

		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic(KeyEvent.VK_E);
		// aboutItem.setToolTipText("About");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				final JDialog about = new JDialog();
				about.setBounds(400, 200, 100, 100);
				about.setLayout(new BorderLayout());
				about.setTitle("About");
				JButton close = new JButton("Close!");
				close.setVisible(true);
				close.setSize(40, 25);
				close.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						about.dispose();
					}
				});
				JPanel south = new JPanel();
				south.add(close);

				about.add(south, BorderLayout.SOUTH);
				TextArea text = new TextArea(
						"Alleyway Version 1.0  \nCopyright 2014 by T. Petrov  \nFreeware. All Rights Reserved  \n",
						5, 40);
				text.setEditable(false);
				about.add(text, BorderLayout.CENTER);
				about.setVisible(true);
				about.pack();

			}
		});

		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.setMnemonic(KeyEvent.VK_E);
		helpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				final JDialog help = new JDialog();
				help.setBounds(400, 200, 100, 100);
				help.setLayout(new BorderLayout());
				help.setTitle("Help");
				JButton close = new JButton("Close!");
				close.setVisible(true);
				close.setSize(40, 25);
				close.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						help.dispose();
					}
				});
				JPanel south = new JPanel();
				south.add(close);

				help.add(south, BorderLayout.SOUTH);
				TextArea helpText = new TextArea(
						"Buttons:  \nLeft Arrow - Move Left  \nRight Arrow - Move Right  \nEnter - Pause Game / Unpause / Start New Game \nEsc - Exit  \n");
				helpText.setEditable(false);
				
				help.add(helpText, BorderLayout.CENTER);
				help.setVisible(true);
				help.pack();
			}
		});
		help.add(helpItem);
		help.add(aboutItem);

		menubar.add(main);
		menubar.add(help);
		menubar.setVisible(true);
		add(menubar);

	}

}

package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Model.BreakoutGame;
import Model.Items.Utilities;

public class GameFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameFrame() {
		
		setTitle("Breakout");
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	/*	setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setUndecorated(true);
		setVisible(true);
		*/
	}
	
	
	

}

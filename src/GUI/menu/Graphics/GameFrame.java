package GUI.menu.Graphics;

import java.awt.Dimension;
import javax.swing.JFrame;
import Utility.Utilities;

public class GameFrame extends JFrame{

	/**
	 * FINESTRA PRINCIPALE
	 * verrà utilizzata sia dagli screen 
	 * e verrà usata per caricarci i pannelli
	 */
	private static final long serialVersionUID = 1L;
	
	public GameFrame() {
		
		setTitle("Breakout");
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);
        setLocationRelativeTo(null);
		setResizable(false);
		inzialize();
		
	}
	
	public void inzialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}

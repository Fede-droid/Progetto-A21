package Model;

import java.awt.Dimension;

import javax.swing.JFrame;

import Model.Items.Utilities;

public class BreakoutGame {
	
	// dimensione finestra di gioco
		
	
	
	public static void main(String[] args) {
		
		// creazione finestra di gioco
		JFrame GameFrame = new JFrame("Breakout");
		GameFrame.setVisible(true);
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		GameFrame.setPreferredSize(dimension_screen);
		GameFrame.setMaximumSize(dimension_screen);
		GameFrame.setResizable(true);
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// creazione gioco 
		
		Screen screen1 = new Screen();
		GameFrame.add(screen1);
		
		GameFrame.addKeyListener(screen1);
		
		GameFrame.pack();
		GameFrame.setVisible(true);

		Thread gameThread = new Thread(screen1);
		gameThread.start();
		
	
	}


	
}

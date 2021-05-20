package Model;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import GUI.GameFrame;
import GUI.menu.MainMenu;
import Model.Items.Utilities;

public class BreakoutGame {
	
	// dimensione finestra di gioco
	
	
	public static void main(String[] args) {
		
		
		//creo un giocatore
		Player p = new Player();
				
		// creazione finestra di gioco
		GameFrame gameFrame = new GameFrame();
		
		
		// creazione gioco 
		Screen screen1 = new Screen();
		gameFrame.add(screen1);
		
		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p);
		screen1.newPlayer(p);
		
		gameFrame.pack();
		gameFrame.setVisible(true);

		// avvio ciclo di gioco
		Thread gameThread = new Thread(screen1);
		gameThread.start();
		
		
		
		/*
		MainMenu m = new MainMenu();
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		*/
		
	}
	
	
}

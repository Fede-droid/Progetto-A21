package Model;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import GUI.GameFrame;
import GUI.menu.Graphics.MainMenu;
import GUI.menu.Graphics.PauseMenu;
import Model.Core.Screen;
import Model.Items.Utilities;
import Model.Logic.Player;

public class BreakoutGame {
	
	private GameFrame gameFrame;
	private Screen screen1;
	
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		
	}

	public void start() {
		
		// creazione gioco 
		this.screen1 = new Screen();
		MainMenu m = new MainMenu(this);
		
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}
	
	public void gameSetup() {
		
		// creo un giocatore
		Player p = new Player();
				
		
		screen1.newPlayer(p);
				
		gameFrame.add(screen1);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p.getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		Thread gameThread = new Thread(screen1);
		gameThread.start();
	}
	
	
	public void gameWin(boolean win) {
		
		screen1.setVisible(false);
		PauseMenu pause = new PauseMenu(this, win);
		gameFrame.add(pause);
		gameFrame.pack(); 
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	public void setSound(boolean on) {
		
		screen1.setMusic(on);
	}
}

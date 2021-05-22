package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import GUI.GameFrame;
import GUI.menu.Graphics.MainMenu;
import GUI.menu.Graphics.PauseMenu;
import Model.Core.Screen;
import Model.Items.Utilities;
import Model.Logic.Player;

public class BreakoutGame {
	
	private GameFrame gameFrame;
	private Screen screen;
	private List<Player> players;
	private Thread gameThread, gameThread2;
	private Boolean music;
	private Player p;
	
	
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
	}

	public void start() {
		
		// creazione gioco 
		this.screen = new Screen(this);
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
		this.p = new Player();
		players.add(p);
				
		screen.newPlayer(p);
				
		gameFrame.add(screen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p.getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		this.gameThread = new Thread(screen);
		this.gameThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void playAgain() {
		
		gameThread.stop();
		
		screen.reset();
		
		gameFrame.requestFocusInWindow();
		gameFrame.pack();
		
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		this.gameThread2 = new Thread(screen);
		gameThread2.start();
	
		
	}
	
	
	public void gameWin(boolean win) {
		
		screen.setVisible(false);
		PauseMenu pause = new PauseMenu(this, win);
		gameFrame.add(pause);
		gameFrame.pack(); 
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	public void setSound(boolean bool) {
		
		this.music = bool;
		screen.setMusic(bool);
	}
}

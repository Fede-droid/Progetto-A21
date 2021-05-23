package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.MainMenu;
import GUI.menu.Graphics.PauseMenu;
import Model.Core.Screen;
import Model.Items.Utilities;
import Model.Logic.Player;

public class BreakoutGame {
	
	// controller tra la logica e la gui
	
	private GameFrame gameFrame; //creazione nuova finestra
	private Screen screen; 
	private List<Player> players; // definizione dei giocatori
	private Thread gameThread, gameThread2; // thread di gioco
	private Boolean music; // setup musica
	private Player p; 
	
	// creazione del controller
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
	}

	// avvio menu principale e creazione gioco
	public void start() {
		
		// creazione gioco 
		this.screen = new Screen(this);
		MainMenu m = new MainMenu(this);
		
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}

	
	// inizializzazione gioco e giocatori a sceonda delle scelte dell'utente
	public void gameSetup() {
		
		// creo un giocatore
		this.p = new Player();
		players.add(p);
				
		screen.newPlayer(p);
		
		screen.start();
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
	
	// ripetere il livello/partita
	@SuppressWarnings("deprecation")
	public void playAgain() {
		
		gameThread.stop();
		screen.reset();
		gameFrame.requestFocusInWindow();
		gameFrame.pack();
		gameFrame.setVisible(true);
		this.gameThread2 = new Thread(screen);
		gameThread2.start();
	
		
	}
	
	// menu vittoria/sconfitta
	public void gameWin(boolean win) {
		
		
		screen.setVisible(false);
		PauseMenu pause = new PauseMenu(this, win);
		gameFrame.add(pause);
		gameFrame.pack(); 
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	// ritorna al menu 
	
	
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	
	public void setSound(boolean bool) {
		
		this.music = bool;
		screen.setMusic(bool);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayers(List<Player> players) {
		this.players = players;
	}
	
	
}

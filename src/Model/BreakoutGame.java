package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.MainMenu;
import GUI.menu.Graphics.MultiplayerPanel;
import GUI.menu.Graphics.PauseMenu;
import GUI.menu.Graphics.YouWin;
import Model.Core.Levels;
import Model.Core.MultiplayerScreen;
import Model.Core.Screen;
import Model.Core.TypeLevels;
import Model.Core.Multiplayer.Client;

import Model.Items.Utilities;
import Model.Logic.Player;
import Model.Logic.ScoreAdvisor;

public class BreakoutGame {
	
	// controller tra la logica e la gui
	
	private GameFrame gameFrame; //creazione nuova finestra
	private Screen screen; 
	private List<Player> players; // definizione dei giocatori
	private Thread gameThread, gameThread2; // thread di gioco
	private Boolean music; // setup musica
	private Player p; 
	private ScoreAdvisor score;
	private MainMenu m;
	private TypeLevels lv;
	private MultiplayerScreen multiplayerScreen;
	private MultiplayerPanel multiplayerPanel;
	private boolean isHost;
	private String gameCode;
	private String playerName;
	private int playerNumber;
	private Client client;
	
	
	// creazione del controller
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
		this.lv = TypeLevels.LEVEL1;
		
	}

	// avvio menu principale e creazione gioco
	public void start() {
		
		this.screen = new Screen(this); //creazione schermo di gioco
		this.score = new ScoreAdvisor(screen); 
		
		this.m = new MainMenu(this);
		
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}

	
	// inizializzazione gioco con un giocaore offline
	public void gameSetupSinglePlayer() {
	
	
		// creo un giocatore
		p = new Player();
		players.add(p);
		
		screen.newPlayer(p);
		
		screen.start();
		screen.setLevel(lv);
		
		gameFrame.add(screen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p.getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(screen).start();
		screen.setVisible(true);
	}
	
	//***// GESTIONE MULTIPLAYER
	
	public void inizializeMultiplayer() {
		
		this.client = new Client(); //inzializzo connessione con il server
		multiplayerPanel =  new MultiplayerPanel(this);
		gameFrame.add(multiplayerPanel);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	// dati giocatore 
	public void setPlayerData(boolean isHost, String name, String code, int number) {
		
		this.isHost = isHost;
		playerName = name;
		gameCode = code;
		playerNumber = number;
		sendRequest();
		
	}
	// invio al server i dati del giocatore
	public void sendRequest() {
		
		client.join(isHost, gameCode, playerName, playerNumber);
		startGame();
	}
	
	
	// inzio partita in multigiocatore
	public void startGame() {
		
		multiplayerPanel.setVisible(false);
		multiplayerPanel.removeAll();
		
		gameSetupMultiplayer();
		client.startThread(multiplayerScreen);
		
	}

	
	// inzializzazione gioco multiplayer 
	public void gameSetupMultiplayer() {
		
		multiplayerScreen = new MultiplayerScreen(this); 
		
		// creo un giocatore
		p = new Player();
		players.add(p); // aggiungo player alla partita
		
		multiplayerScreen.newPlayer(p); //creazione schermo di gioco multiplayer
		
		multiplayerScreen.start();
		multiplayerScreen.setLevel(lv);
		
		gameFrame.add(multiplayerScreen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p.getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(multiplayerScreen).start();
		multiplayerScreen.setVisible(true);
	}
	
	
	// ripetere il livello/partita
	//@SuppressWarnings("deprecation")
	public void playAgain() {
		
		//gameThread.stop();
		
		screen.reset();
		
		gameFrame.add(screen);
		
		gameFrame.requestFocusInWindow();
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		
		this.gameThread2 = new Thread(screen);
		gameThread2.start();
		screen.setVisible(true);
	
		
	}
	
	// menu vittoria/sconfitta
	public void gameWin(boolean win) {
		
		screen.reset();
		screen.setVisible(false);
		
		PauseMenu pause = new PauseMenu(this, win);
		gameFrame.add(pause);
		gameFrame.pack(); 
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	// ritorna al menu 

	public void showMain() {
		
		screen.reset();
		
		gameFrame.add(new MainMenu(this));
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	public void showMainFromWin() {
		
		
		
	}
	
	// non funziona
	public void nextLevel() {

		screen.reset();
		screen.start();
		
		gameFrame.add(screen);
		
		gameFrame.requestFocusInWindow();
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		
		this.gameThread2 = new Thread(screen);
		gameThread2.start();
		screen.setVisible(true);
		
	}
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
	
	public ScoreAdvisor getScoreAdvisor() {
		
		return score;
	}
	
	
	public void setLevel(TypeLevels level) {
		
		this.lv = level;
		
	}
	
	public void reset() {	
		gameFrame.invalidate();
		gameFrame.validate();
		gameFrame.repaint();
		
		showMain();	
	}
	
	

	
	public Screen getScreen() {
		
		return this.screen;
	}
	
	
	
	
}

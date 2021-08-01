package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.IntroPanel;
import GUI.menu.Graphics.MainMenu;
import GUI.menu.Graphics.MultiplayerPanel;
import GUI.menu.Graphics.PauseMenu;
import GUI.menu.Graphics.WaitingForPlayerPanel;
import GUI.menu.Graphics.YouWin;
import Model.Core.MultiplayerScreen;
import Model.Core.Screen;
import Model.Core.Levels.Levels;
import Model.Core.Multiplayer.Client;

import Model.Items.Utilities;
import Model.Logic.Player;

public class BreakoutGame {
	
	// controller tra la logica e la gui
	
	private GameFrame gameFrame; //creazione nuova finestra
	private Screen screen; 
	private ArrayList<Player> players; // definizione dei giocatori
	private Thread gameThread, gameThread2; // thread di gioco
	private Boolean music; // setup musica
	private Player p; 
	private MainMenu m;
	private MultiplayerScreen multiplayerScreen;
	private MultiplayerPanel multiplayerPanel;
	private boolean isHost;
	private String playerName, gameCode;
	private int playerNumber, playerIndex, numberOfMissingPlayer;
	private Client client;
	private WaitingForPlayerPanel waitingPanel;
	private boolean botMode;
	private int level, totLevels;
	
	// creazione del controller
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
		this.music = true;
		
		this.level = 1;
	}

	// avvio menu principale e creazione gioco
	public void start() throws InterruptedException {		
		
		IntroPanel intro = new IntroPanel();
		gameFrame.add(intro);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		TimeUnit.SECONDS.sleep(9);
		
		intro.setVisible(false);
		gameFrame.repaint();

		
		this.m = new MainMenu(this);
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		this.screen = new Screen(this); //creazione schermo di gioco
		
	}

	
	// inizializzazione gioco con un giocaore offline
	public void gameSetupSinglePlayer(boolean botMode) {
		
		this.botMode = botMode;
		
		int n;
		
		if(botMode) n = 2; 
		else n = 1;
				
		screen.setNumberOfPlayers(n);
		for (int i=0; i<n; i++) {
			players.add(new Player());
		}
		
		screen.addPlayers(players);
		screen.start();
		screen.setLevel(level);
		screen.setMusic(music);
		
		gameFrame.add(screen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(players.get(0).getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(screen).start();
		screen.setVisible(true);
	}
	
	public int getNumberOfLevels() {
		
		screen.start();
		return screen.getNumberOfLevels();
		
	}
	//***************************** INZIO GESTIONE MULTIPLAYER ****************************//
	
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
		
		client.join(this, isHost, gameCode, playerName, playerNumber);
		
	}
	
	
	// inzio partita in multigiocatore
	public void startGame() {
		
		multiplayerPanel.setVisible(false);
		multiplayerPanel.removeAll();
		
		gameSetupMultiplayer();
		
		
	}

	
	// inzializzazione gioco multiplayer 
	public void gameSetupMultiplayer() {
		
		multiplayerScreen = new MultiplayerScreen(this, playerNumber, playerIndex ); 
		
		// creo un giocatore
		for (int i=0; i<playerNumber; i++) {
			players.add(new Player());

		}	
		
		multiplayerScreen.addPlayers(players); //creazione schermo di gioco multiplayer
		
		multiplayerScreen.setMusic(music);
		
		multiplayerScreen.start();
		
		
		client.startThread(multiplayerScreen);
		
		gameFrame.add(multiplayerScreen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(players.get(playerIndex).getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(multiplayerScreen).start();
		multiplayerScreen.setVisible(true);
	}
	
	public void waitingMissingPlayer() {
		
		waitingPanel = new WaitingForPlayerPanel(this); 
		gameFrame.add(waitingPanel);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		
	}
	
	public void updateMissingPlayer() {
		waitingPanel.updateMissingPlayerText();
	}
	
	public void multiplayerError() {
		multiplayerPanel.showError();
	}
	
	public int getNumberOfPlayer() {
		return playerNumber;
	}
	
	public void setNumberOfPlayer(int n) {
		playerNumber = n;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	public void setPlayerIndex(int pi) {
		playerIndex = pi;
	}
	
	public void setNumberOfMissingPlayer(int nof) {
		numberOfMissingPlayer = nof;
	}
	
	public int getNumberOfMissingPlayer() {
		return numberOfMissingPlayer;
	}
	
	//***************************** FINE GESTIONE MULTIPLAYER ****************************//
	
	// ripetere il livello/partita
	//@SuppressWarnings("deprecation")
	public void playAgain() {
		
		screen.reset();
		
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
	
	// incremento livello
	public void nextLevel() {

		this.level++;
		screen.reset();
		
	}
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	
	public void setSound(boolean bool) {
		
		this.music = bool;
		
	}

	public List<Player> getPlayers() {
		
		return players;
	}

	public void addPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	
	public void setLevel(int level) {
		
		this.level = level;
		
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
	
	public int getLastScore() {
		return screen.getLastScore();
	}
	
	public boolean getBotMode() {
		return this.botMode;
	}
	
	
	
}

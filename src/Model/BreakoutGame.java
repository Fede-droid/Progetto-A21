package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.IntroPanel;
import GUI.menu.Graphics.*;

import GUI.menu.Graphics.MultiplayerPanel;
import GUI.menu.Graphics.PauseMenu;
import GUI.menu.Graphics.WaitingForPlayerPanel;
import Model.Core.MultiplayerScreen;
import Model.Core.Screen;
import Model.Core.Multiplayer.Client;
import Model.Logic.Player;

public class BreakoutGame {
	
	// PATTERN CONTROLLER
	
	private GameFrame gameFrame; 
	private Screen screen; 
	private ArrayList<Player> players; // definizione dei giocatori
	private Boolean music; // setup musica
	private MainMenu m;
	private MultiplayerScreen multiplayerScreen;
	private MultiplayerPanel multiplayerPanel;
	private boolean isHost;
	private String playerName, gameCode;
	private int playerNumber, playerIndex, numberOfMissingPlayer;
	private Client client;
	private WaitingForPlayerPanel waitingPanel;
	private boolean botMode, entered;
	private int level, lastScore;
	
	/**
	 * creazione del controller
	 */
	
	public BreakoutGame() {
		
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
		this.music = true;
		
		this.level = 1;
	}

	/*
	 * avvio del gioco a partire dalla intro
	 */
	public void start() throws InterruptedException {		
		
		IntroPanel intro = new IntroPanel(this);
		gameFrame.add(intro);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
		TimeUnit.SECONDS.sleep(9);
		
		intro.setVisible(false);
		skipIntro();
		
	}
	
	/**
	 *  salta l'introduzione
	 */
	public void skipIntro(){
		
		if(!entered) {
			entered = true;
			gameFrame.repaint();
	
		
			LoginMain login = new LoginMain(this);
			
			gameFrame.add(login);
			gameFrame.pack();
			gameFrame.setVisible(true);
			gameFrame.repaint();
			
			
	}
	}
	
	/**
	 * apertura main
	 */
	public void openMain() {
		
		this.m = new MainMenu(this);
		
		gameFrame.add(m);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}

	
	/**
	 *  inizializzazione gioco con un giocaore offline
	 * @param botMode
	 */
	public void gameSetupSinglePlayer(boolean botMode) {
		this.screen = new Screen(this); //creazione schermo di gioco
		this.botMode = botMode;
		
		int n;
		
		if(botMode) n = 2; 
		else n = 1;
				
		screen.setNumberOfPlayers(n);
		for (int i=0; i<n; i++) {
			players.add(new Player());
		}
		
		screen.addPlayers(players);
		//screen.start();
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
	
	
	
	//***************************** INZIO GESTIONE MULTIPLAYER ****************************//
	/**
	 *  menu multiplayer 
	 */
	
	public void inizializeMultiplayer() {
		
		this.client = new Client(); //inzializzo connessione con il server
		multiplayerPanel =  new MultiplayerPanel(this);
		m.setVisible(false);
		gameFrame.add(multiplayerPanel);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	/**
	 * 
	 */
	public void inizializeMultiplayerAP() {
		client.stopConnection();
		multiplayerPanel =  new MultiplayerPanel(this);
		multiplayerScreen.setVisible(false);
		gameFrame.add(multiplayerPanel);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	/**
	 * dati giocatore 
	 * @param isHost
	 * @param code
	 * @param number
	 */
	public void setPlayerData(boolean isHost, String code, int number) {
		
		this.isHost = isHost;
		
		gameCode = code;
		playerNumber = number;
		sendRequest();
		
	}
	
	/**
	 * invio al server i dati del giocatore
	 */
	public void sendRequest() {
		
		client.join(this, isHost, gameCode, playerName, playerNumber);
		
	}
	
	
	/*
	 *  inzio partita in multigiocatore
	 */
	public void startGame() {
		
		multiplayerPanel.setVisible(false);
		if(waitingPanel != null) waitingPanel.setVisible(false);
		multiplayerPanel.removeAll();
		
		gameSetupMultiplayer();
		
		
	}

	
	/*
	 * inzializzazione gioco multiplayer 
	 */
	public void gameSetupMultiplayer() {
		
		multiplayerScreen = new MultiplayerScreen(this, playerNumber, playerIndex ); 
		
		// creo giocatore
		for (int i=0; i<playerNumber; i++) {
			players.add(new Player());

		}	
		multiplayerScreen.addPlayers(players); //creazione schermo di gioco multiplayer
		
		multiplayerScreen.setMusic(music);
		
		multiplayerScreen.setLevel(level);
		
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
	
	/**
	 *  finestra di attesa giocatori
	 */
	public void waitingMissingPlayer() {

		this.waitingPanel = new WaitingForPlayerPanel(this);
		gameFrame.add(waitingPanel);
		multiplayerPanel.setVisible(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	/**
	 * 
	 */
	public void multiplayerError() {
		multiplayerPanel.showError();
	}
	
	/**
	 * 
	 * @return numeroplayer
	 */
	public int getNumberOfPlayer() {
		return playerNumber;
	}
	
	/**
	 * 
	 * @param numero player
	 */
	public void setNumberOfPlayer(int n) {
		playerNumber = n;
	}
	
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/**
	 * 
	 * @param player index
	 */
	public void setPlayerIndex(int pi) {
		playerIndex = pi;
	}
	
	/**
	 * 
	 * @param nof
	 */
	public void setNumberOfMissingPlayer(int nof) {
		numberOfMissingPlayer = nof;
	}
	
	/**
	 * 
	 * @param numero livello
	 */
	public void setNumberLevel(int nl) {
		level = nl;
	}
	
	/**
	 * 
	 * @return numberOfMissingPlayer
	 */
	public int getNumberOfMissingPlayer() {
		return numberOfMissingPlayer;
	}
	
	/**
	 * aggiornamento giocatori rimanenti
	 */
	public void updateMissing() {
		
		waitingPanel.updateMissingPlayerText();
		waitingPanel.repaint();
	}
	
	
	//***************************** FINE GESTIONE MULTIPLAYER ****************************//
	
	
	// gioca allo stesso livello 
	public void playAgain() {
		
		screen.reset();
		
	}
	
	/**
	 *  menu intermedio tra i livelli
	 * @param win yes or not
	 */
	public void gameWin(boolean win) {
		
		lastScore = screen.getLastScore();
		screen.reset();
		screen.setVisible(false);
		
		PauseMenu pause = new PauseMenu(this, !win);
		gameFrame.add(pause);
		gameFrame.pack(); 
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	/**
	 *  ritorna al menu 
	 */

	public void showMain() {
		
		screen.reset();
		
		
		gameFrame.add(new MainMenu(this));
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
		
	}
	
	
	/**
	 * incremento livello
	 */
	public void nextLevel() {

		this.level++;
		screen.reset();
		
	}
	
	/**
	 * 
	 * @return gameFrame
	 */
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	
	/**
	 * 
	 * @param bool
	 */
	public void setSound(boolean bool) {
		
		this.music = bool;
		
	}

	/**
	 * 
	 * @return list players
	 */
	public List<Player> getPlayers() {
		
		return players;
	}
	
	/**
	 *  aggiunta giocatori alla partita
	 * @param players
	 */
	public void addPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	/**
	 * settaggio livello di gioco
	 * @param level
	 */
	public void setLevel(int level) {
		
		this.level = level;
		
	}
	
	/**
	 *  reset del gioco
	 */
	public void reset() {	
		gameFrame.invalidate();
		gameFrame.validate();
		gameFrame.repaint();
		
		showMain();	
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setPlayerName(String name) {
		
		this.playerName = name;
	}
	
	/**
	 * 
	 * @return screen
	 */
	public Screen getScreen() {
		
		return this.screen;
	}
	
	/**
	 * 
	 * @return last score
	 */
	public int getLastScore() {
		return lastScore;
	}
	
	/**
	 * 
	 * @return is bot mode
	 */
	public boolean getBotMode() {
		return this.botMode;
	}
	
	/**
	 * 
	 * @return playerName
	 */
	public String getPlayerName() {
		return playerName;
		
		
	}
	
}
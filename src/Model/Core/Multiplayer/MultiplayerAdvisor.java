package Model.Core.Multiplayer;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.MultiplayerPanel;
import Model.BreakoutGame;

public class MultiplayerAdvisor {
	
	private boolean isHost;
	private String gameCode;
	private String playerName;
	private int playerNumber;
	private Client client;
	private BreakoutGame mainGame;
	private GameFrame gameFrame;
	private MultiplayerPanel multiplayerPanel;
	
	public MultiplayerAdvisor(BreakoutGame mainGame) {
		
		this.client = new Client();
		this.mainGame = mainGame;
		this.gameFrame = mainGame.getGameFrame();
		
		inizialize();
	
	}
	
	public void inizialize() {
		
		this.multiplayerPanel =  new MultiplayerPanel(this);
		gameFrame.add(multiplayerPanel);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	public void setPlayerData(boolean isHost, String name, String code, int number) {
		
		this.isHost = isHost;
		this.playerName = name;
		this.gameCode = code;
		this.playerNumber = number;
		sendRequest();
		
	}
	
	public void sendRequest() {
		
		client.join(isHost, gameCode, playerName, playerNumber);
		startGame();
	}
	
	public void startGame() {
		
		multiplayerPanel.setVisible(false);
		multiplayerPanel.removeAll();
		
		mainGame.gameSetup();
		mainGame.start();
		client.startThread();
		
	}

	
}

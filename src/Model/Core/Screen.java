package Model.Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;

import Database.PersistenceFacade;
import Model.BreakoutGame;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.BrickPowerUp;
import Model.Items.Item;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.PowerUp.PowerUp;
import Model.Logic.CollisionAdvisor;
import Model.Logic.Drawer;
import Model.Logic.LifeAdvisor;
import Model.Logic.Player;
import Model.Logic.PowerUpListComparator;
import Model.Logic.ScoreAdvisor;
import Model.Logic.ScreenItemFactory;
import Model.Logic.Levels.Levels;
import Music.Music;
import Music.MusicTypes;
import Utility.Utilities;

public class Screen extends Canvas implements Runnable{
	
	// Classe pirncipale per la gestione di una partita in single player
	
    protected BreakoutGame game;
    protected boolean gameStatus = false;
	protected boolean gameOver = false;
	protected boolean gameWin = false;
	protected Ball objBall;
	protected ArrayList<Brick> objBricks;
	protected HashMap<PowerUp, ScreenItem> objPowerUp;
	protected ArrayList<Paddle> objPaddles;
	protected ScreenItem objSfondo, objHit, objBox, objSpeedUpLogo, objSwitchLogo, objLongerLogo, objShorterLogo, objWin, objLose;
	protected ScreenItem[] objLife;
	protected ScreenItem[] objOn;
	Clip win,hit;
	protected boolean isMusicOn;
	protected Graphics g;
	CollisionAdvisor advisor;
	protected Music mainMusic;
	protected int score;
	protected Levels levels;
	protected ArrayList<Player> players;
	protected LifeAdvisor lifeAdvisor;
	protected int lastScore, numberOfPlayers, currentLevel;
	protected Drawer drawer;
	private ScoreAdvisor scoreAdvisor;
	private PersistenceFacade db;

	/*
	 * Costruttore della classe screen, istanzia gli oggetti dello screen e il drawer per disegnare
	 */
	public Screen(BreakoutGame game) {
		this.game = game;
		objBricks = new ArrayList<Brick>();
		objPowerUp = new HashMap<PowerUp, ScreenItem>();
		objPaddles = new ArrayList<Paddle>();
		players = new ArrayList<Player>();
		drawer = new Drawer();
		this.mainMusic = new Music();
		score=0;
		this.db = new PersistenceFacade(this);
	}
	
	/*
	 * Setta il numero di giocatori
	 */
	public void setNumberOfPlayers(int n) {
		
		this.numberOfPlayers = n;
	}
	
	/*
	 * Metodo run che definisce il thread screen, qui viene gestito il ciclo di gioco: attraverso il pattern game cycle
	 * vengono chiamati update() e render(), per rispettivamente, aggiornare il model con le nuove posizioni e disegnare gli oggetti.
	 * La variabile fps Stabilisce i frame per secondo.
	 */
	@Override
	public void run() {
		
		double previous = System.nanoTime(); 
		double delta = 0.0;
		double fps = 100.0;
		double ns = 1e9/fps; // numero di nano secondi per fps
		gameStatus = true;
		
		while (gameStatus) {
			double current = System.nanoTime();
			
			double elapsed = current - previous;
			previous = current;
			delta += elapsed;

				while (delta >= ns) {
				   update();	
				   delta -= ns;
				}
			render();
		}
		
		game.inizializeMultiplayerAP();
	}
	
	/*
	 *  inzializzazione della partita: creo gli oggetti ScreenItem che poi verranno aggiornati e disegnati.
	 */
	public void setLevel(int lv) {
		this.scoreAdvisor = new ScoreAdvisor();
		scoreAdvisor.start();
		currentLevel = lv;
		this.score = 0;
	
		objBall = (Ball)ScreenItemFactory.getInstance().getScreenItem(Item.BALL);
		
		levels = new Levels(objBall, objPaddles);
		levels.setLevel(lv);
		objBricks = levels.getBricksDesposition(lv);
		//levels.setPlayersPosition(numberOfPlayers);
		
		ArrayList<PowerUp> tempList = new ArrayList<PowerUp>();
		for(Brick tempBrick : objBricks) {
			try {
				if(!((BrickPowerUp) tempBrick == null)) {
					tempList.add(((BrickPowerUp)tempBrick).getPowerUp());
				}
			} catch(ClassCastException e) {	}
		}
		
		advisor = new CollisionAdvisor(objBall, mainMusic);
		
		lifeAdvisor = new LifeAdvisor(mainMusic, advisor, objBall);
		objLife = ScreenItemFactory.getInstance().getScreenItem(Item.LIFE, Utilities.NUMBER_LIFE);
		
		objSpeedUpLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SPEED_UP);
		objSwitchLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SWITCH);
		objSfondo = ScreenItemFactory.getInstance().getScreenItem(Item.SFONDO);
		objBox = ScreenItemFactory.getInstance().getScreenItem(Item.BOX);
		objHit = ScreenItemFactory.getInstance().getScreenItem(Item.HIT);
		objWin = ScreenItemFactory.getInstance().getScreenItem(Item.WIN);
		objLose = ScreenItemFactory.getInstance().getScreenItem(Item.LOSE);
		objLongerLogo = ScreenItemFactory.getInstance().getScreenItem(Item.LONG_UP);
		objShorterLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SHORT_UP);
		
		objOn = ScreenItemFactory.getInstance().getScreenItem(Item.ON, tempList.size());
		PowerUpListComparator c = new PowerUpListComparator();
		tempList.sort(c);
		for(int i=0; i < tempList.size(); i++) {
			if(!objPowerUp.containsKey(tempList.get(i))) objPowerUp.put(tempList.get(i), objOn[i]);
		}
	}
	
		/*
		 *  Game cycle: update(), aggiorno il ciclo di gioco.
		 *  Controllo le collisioni e gestisco cambiamenti nel model dovuto ad esse
		 */
		synchronized public void update() {
			
		    objBall.move();
		    gameOver = lifeAdvisor.checkLife(numberOfPlayers);
		    gameStatus = advisor.checkBorderCollision(numberOfPlayers);
		    
		    for (Paddle tempPaddle: objPaddles) {
		    	advisor.checkCollisionLato(tempPaddle);
		    	advisor.checkCollision(tempPaddle);
		    }

		    advisor.checkCollisionLato(objBox);
			
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					if(advisor.checkCollisionLato(tempBrick) || advisor.checkCollision(tempBrick)) {
						score++;
					}
					if(tempBrick.isDestroyed()) {
						tempBrick.activatePowerUP();
					}
				}
			}
			
			objPaddles.get(0).move();
			if (objPaddles.size()>1) objPaddles.get(1).move(objBall.getXPosition(), objBall.getYPosition(),objBall.getImageWidth());// bot
		}		
		
		/*
		 * Game cycle: render(), renderizzo gli screenItem.
		 * Ogni screen item � definito drawable e, attraverso la classe drawer, viene disegnato su un oggetto Canvas.
		 * Ogni oggetto per essere disegnato effettivamente utilizza la classe graphics con il quale bufferizzo tutte le coponenti di
		 * un frame. Solo una volta composto il frame disegno. 
		 */
		synchronized public void render() {
			
			BufferStrategy buffer = this.getBufferStrategy();
			if(buffer == null) {
				this.createBufferStrategy(2);
				return;	
			}
			g = buffer.getDrawGraphics();
			
			drawer.loadGraphics(g);
			
			drawer.draw(objSfondo);
			drawer.draw(objBall);
			drawer.draw(objBox);
			drawer.draw(objHit);
			drawer.draw(objSpeedUpLogo);
			drawer.draw(objSwitchLogo);
			drawer.draw(objLongerLogo);
			drawer.draw(objShorterLogo);
			
			for(int i=0; i < lifeAdvisor.getLife(); i++) drawer.draw(objLife[i]);
			
			for(Paddle tempPaddle: objPaddles) drawer.draw(tempPaddle);
			
			for(Brick tempBrick: objBricks) {
				if(!tempBrick.isDestroyed()) drawer.draw(tempBrick);	
			}
			
			for(PowerUp powerUp: objPowerUp.keySet()) {
				if(powerUp.isActive()) {
					drawer.draw(objPowerUp.get(powerUp));
				}
			}
			
			drawer.draw(String.valueOf((Integer)score).toString(), 517, 60);
			drawer.draw("LV", 505, 15);
			drawer.draw(""+levels.getActualLevel(), 530, 15);
			
			
			
			if(endGame()) {
				drawer.draw(objWin);
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.WIN);
				gameStatus = false;
				gameWin = true;
				endGameWin();
			}
			if(!gameWin) endGameOver();
			if(gameOver) drawer.draw(objLose);
			
			
			g.dispose();
			buffer.show();
		}
		
		private boolean endGame() {
			int win = 0;
			for(Brick tempBrick : objBricks) {
				if(tempBrick.isDestroyed()) {
					win++;
				}
			}
			if(win == objBricks.size()) {
				return true;
			} else return false;
		}

		private void endGameOver() {
			if(!gameStatus) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOSE);

				// ho perso
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				lastScore = scoreAdvisor.getScoreEnd(score);
				game.gameWin(false);	
			}
		}
		
		private void endGameWin() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			lastScore = scoreAdvisor.getScoreEnd(score);
			if(db.getScoreByLVandUser() < score) {
				db.updateScore();
			}
			game.gameWin(true);
		}
		

		//Aggiungo player alla partita
		public void addPlayers(ArrayList<Player> players) {
			this.players = players;
			for(Player tempPlayer : players) {
				objPaddles.add(tempPlayer.getObjPaddle());	
			}
			if(numberOfPlayers != 1) {
				objPaddles.get(1).setPosition(Utilities.INITIAL_POSITION_PADDLE_X, 3);
			}
		}
		
		//modifico musica 
		public void setMusic(Boolean b) {
			mainMusic.setMusic(b);
		}
		
		public void reset() {
			players.removeAll(players);
			objPaddles.removeAll(objPaddles);
		}
		
		public int getNumberOfLevels() {
			
			return levels.getNumberOfLevels();
		}
		
		
		public int getLastScore() {
			return lastScore;
		}
		
		public int getCurrentLevel() {
			return currentLevel;
		}
		
		public String playerName() {
			return game.getPlayerName();
		}
		
		public Graphics getG() {
			return g;
		}
	}	
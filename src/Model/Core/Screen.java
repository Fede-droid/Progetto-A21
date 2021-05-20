package Model.Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.SpecialBrick;
import Model.Items.Utilities;
import Model.Logic.CollisionAdvisor;
import Model.Logic.Player;
import Music.Music;
import Music.MusicTypes;

public class Screen extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage ball, brick, brick1, brick2, brick3, specialBrick, sfondo, youWin, youLose;
	SpecialBrick objSpecialBrick;
	private boolean gameStatus = false;
	private Ball objBall;
	private List<Brick> objBricks;
	private ScreenItem objSfondo;
	private ScreenItem objYouWin;
	private ScreenItem objYouLose;
	private ImagesLoader loader;
	private Paddle objPaddle;
	Clip win,hit;
	boolean isMusicOn;
	Graphics g;
	CollisionAdvisor ball1;
	Music mainMusic;
	private BreakoutGame game;
	
	
	int i = 0;
	
	public Screen(BreakoutGame game) {
		this.game = game;
		this.objBricks = new ArrayList<Brick>();
		uploadImages();
		start();
	}
	
	
	// ciclo di gioco
	@Override
	public void run() {
		
		double previous = System.nanoTime(); 
		double delta = 0.0;
		double fps = 160.0;
		double ns = 1e9/fps; // numero di nano sec per fps
		gameStatus = true;
		//switchare off/on
		if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOOP);
		
		while (gameStatus)
		{
		double current = System.nanoTime();
		
		double elapsed = current - previous;
		previous = current;
		delta += elapsed;

		while (delta >= ns)
		{
		   update();	
		   delta -= ns;
		}
		   render();
		}
	}
	
		// caricamento immagini 
		private void uploadImages() {
			
			this.loader = new ImagesLoader();
			this.ball = loader.uploadImage("../Images/ball.png");
			this.sfondo = loader.uploadImage("/Images/sfondo.jpeg");
			this.brick = loader.uploadImage("/Images/brick.png");
			this.brick1 = loader.uploadImage("/Images/brick1.png");
			this.brick2 = loader.uploadImage("/Images/brick2.png");
			this.brick3 = loader.uploadImage("/Images/brick3.png");
			this.specialBrick = loader.uploadImage("/Images/specialBrick.png");
			youWin = loader.uploadImage("/Images/w3.png");
			youLose = loader.uploadImage("/Images/lose.png");

		}

		// disegno di oggetti grafici a schermo oo
		public void render() {
			
			// creazione di 2 buffer cos� che l'immagine venga aggiornata su uno e mostrata sull'altro 
			// modo ciclico, evita gli scatti.
			
			BufferStrategy buffer = this.getBufferStrategy();
			
			if(buffer == null) {
				createBufferStrategy(2);
				return;	
			}
			
			g = buffer.getDrawGraphics();// oggetto di tipo Canvas su cui si pu� disegnare
			
			objSfondo.render(g, this);
			objBall.render(g);
			//for(Player ps : players) {
			objPaddle.render(g);
			//}
			
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					int hitLevel = tempBrick.getHitLevel();
					switch (hitLevel) {
						case 1:
							tempBrick.setImage(brick3); 
							break;
						case 2:
							tempBrick.setImage(brick2); 
							break;
						case 3:
							tempBrick.setImage(brick1); 
							break;
					    default:
					    	tempBrick.setImage(brick);
					}
					tempBrick.render(g);
				}
				
			endGame();
			}
			
			if (!objSpecialBrick.isDestroyed()) objSpecialBrick.render(g);
			g.dispose();
			buffer.show();
		}
		
		// aggiornamento ciclo di gioco
		public void update() {
			
		    objBall.move();
		    gameStatus = ball1.checkBorderCollision();
			ball1.checkCollisionLato(objPaddle);
			ball1.checkCollision(objPaddle);
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					ball1.checkCollisionLato(tempBrick);
					ball1.checkCollision(tempBrick);
				}
			}
			objPaddle.move();
			if (!objSpecialBrick.isDestroyed()) {
				ball1.checkCollisionLato(objSpecialBrick);
				ball1.checkCollision(objSpecialBrick);
			} else {
				objBall.setImageHeight(10);
				objBall.setImageWidth(10);
			}
			
			if(!gameStatus) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOSE);
				
			}
			if(checkWin()) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.WIN);
				
			}
		}
		
		// inzializzazione partita
		private void start() {
			mainMusic = new Music();
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, posInitSfondo);
			
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];
			posInitBall[0] = (int) (250);  // x
			posInitBall[1] = (int) (550-Math.random()*20);  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
			
			ball1 = new CollisionAdvisor(objBall, mainMusic);


			//creazione e posizionamento dei Bricks
			for(int i = 0; i < 1; i++) { // 5 colonne *
				
				for (int j = 0; j < 1; j++) { // 6 righe = 30 Bricks
					
					int[] posInitBrick = new int[2];

					// posizione di partenza dei Brick
					posInitBrick[0] = i * 70 + 80;  //nell'asse x
					posInitBrick[1] = j * 50 + 80; //nell'asse y
			
					// creo i Bricks
					objBricks.add(new Brick(brick, 65, 25, posInitBrick));
				}
			}
			
			int[] posSpecialBrick = {213,15};
			objSpecialBrick = new SpecialBrick(specialBrick, 65, 25, posSpecialBrick);
		}
		
		/*
		 * controlla la winn condition
		 * ritorna true se tutti i brick sono distrutti, quindi ho vinto
		 * ritorna false se cisono ancora brick da distruggere
		 */
		private boolean checkWin() {
			//return objSpecialBrick.isDestroyed();  // Vittoria per distruzione del SPECIAL brick
			//Vittoria per Distruzione di tutti i Brick
			int n = 0;
			for(Brick tempBrick : objBricks) {
				if(!tempBrick.isDestroyed()) {
					n++;
				}
			}
			if(n!=0) return false;
			else return true;
			
		}
		/*
		 * metodo che viene chiamato alla fine del game
		 */
		private void endGame() {
			int[] centralPosition = new int[2];
			centralPosition[0] = Utilities.SCREEN_WIDTH/2 - 250;
			centralPosition[1] = Utilities.SCREEN_HEIGHT/2 - 250;
			if(!gameStatus) {
				// ho perso
				objYouLose = new ScreenItem(youLose, 500, 500, centralPosition);
				objYouLose.render(g);
				game.gameWin(false);
				gameStatus = false;
				
			}
			if(checkWin()) {
				// ho vinto
				objYouWin = new ScreenItem(youWin, 500, 500, centralPosition);
				g.drawImage(youWin, 100, 150, 300, 80, null);
				g.dispose();
				objYouWin.render(g);
				gameStatus = false;
			}
		}

		//Aggiungo player alla partita
		public void newPlayer(Player p) {
			this.objPaddle = p.getObjPaddle();
		}
		
		//modifico musica 
		public void setMusic(Boolean b) {
			
			mainMusic.setMusic(b);
		}
		
	}
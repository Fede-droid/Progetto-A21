package Model.Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;
import Model.Logic.CollisionAdvisor;
import Model.Logic.Player;
import Music.Music;
import Music.MusicTypes;

public class Screen extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage ball, brick, brick1, brick2, brick3, fastBrick, flipBrick, sfondo, youWin, youLose;
	//SpecialBrick objFlip, objFast;
	private boolean gameStatus = false;
	private Ball objBall;
	private List<Brick> objBricks;
	//private List<SpecialBrick> objSpecialBricks;
	private ScreenItem objSfondo, objYouWin, objYouLose;
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
		objBricks = new ArrayList<Brick>();
		//objSpecialBricks = new ArrayList<SpecialBrick>();
		uploadImages();
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
		//if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOOP);
		
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
			
			loader = new ImagesLoader();
			ball = loader.uploadImage("../Images/ball.png");
			sfondo = loader.uploadImage("/Images/sfondo.jpeg");
			brick = loader.uploadImage("/Images/brick.png");
			brick1 = loader.uploadImage("/Images/brick1.png");
			brick2 = loader.uploadImage("/Images/brick2.png");
			brick3 = loader.uploadImage("/Images/brick3.png");
			fastBrick = loader.uploadImage("/Images/fast.png");
			flipBrick = loader.uploadImage("/Images/flip.png");
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
					if(!tempBrick.getHasPoerUp()) {
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
					}
					tempBrick.render(g);
				}
				
			}
			endGame();
			
			//if (!objFast.isDestroyed()) objFast.render(g);
			//if (!objFlip.isDestroyed()) objFlip.render(g);
			
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
					if(tempBrick.isDestroyed()) tempBrick.activatePowerUP();
				}
			}
			objPaddle.move();			
			if(!gameStatus) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOSE);
				
			}
			if(checkWin()) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.WIN);
				
			}
		}
		
		// inzializzazione partita
		public void start() {
			mainMusic = new Music();
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, posInitSfondo);
			
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];

			posInitBall[0] = Utilities.INTIAL_POSITION_BALL_X;  // x
			posInitBall[1] = Utilities.INITIAL_POSITION_BALL_Y;  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
			
			ball1 = new CollisionAdvisor(objBall, mainMusic);
			
			//creazione e posizionamento dei Bricks

			/*
			for(int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) { 
					
					int[] posInitBrick = new int[2];

					// posizione di partenza dei Brick
					posInitBrick[0] = i * 110 + 50;  //nell'asse x
					posInitBrick[1] = j * 60 + 150; //nell'asse y
			
					// creo i Bricks
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
			}
			*/
			
			for (int i = 0; i < 3; i++) {
				int[] posInitBrick = new int[2];
				posInitBrick[0] = i * 165 + 50;  //nell'asse x
				posInitBrick[1] = 90; //nell'asse y
				objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
			}
			
			for (int i = 0; i < 4; i++) {
				int[] posInitBrick = new int[2];
				posInitBrick[0] = i * 110 + 50;  //nell'asse x
				posInitBrick[1] = 30; //nell'asse y
				objBricks.add(new Brick(brick, 65, 25, posInitBrick,4));
			}
					
			int[] posFastBrick = {150,85};
			PowerUp speedUp = new BallSpeedUp(objBall);
			objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));
			
			int[] posFlipBrick = {315,85};
			PowerUp flipUp = new SwitchPaddleDirection(objPaddle);
			objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, flipUp));
			
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
			
				
			}
			if(checkWin()) {
				// ho vinto
				objYouWin = new ScreenItem(youWin, 500, 500, centralPosition);
				g.drawImage(youWin, 100, 150, 300, 80, null);
				g.dispose();
				objYouWin.render(g);
				game.gameWin(true);
				
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
		
		public void reset() {
			for(Brick tempBrick : objBricks) {
				tempBrick.refresh();
				if(tempBrick.getHasPoerUp()) tempBrick.disactivatePowerUp();
			}
			objBall.refresh();
			objPaddle.setPosition(Utilities.INITIAL_POSITION_PADDLE_X, Utilities.INITIAL_POSITION_PADDLE_Y);
		}
		
		/*
		private void addBrickToList(BufferedImage image, int width, int height, int posX, int posY,int hitLevel, PowerUp powerUp) {
			int[] posInitBrick = new int[2];
			posInitBrick[0] = posX;
		    posInitBrick[1] = posY;
		    Brick tempBrick = new Brick(image, width, height, posInitBrick, hitLevel, powerUp);
			objBricks.add(tempBrick);
		}
		*/
	}
package Model;

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
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Music.MusicTypes;

public class Screen extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage ball, brick, brick1, brick2, brick3, sfondo;
	private boolean gameStatus = false;
	private Ball objBall;
	private List<Brick> objBricks;
	private ScreenItem objSfondo;
	private ImagesLoader loader;
	private Paddle objPaddle;
	Clip win,hit;
	boolean isMusicOn;
	
	int i = 0;
	
	public Screen() {
		this.objBricks = new ArrayList<Brick>();
		uploadImages();
		start();
		this.isMusicOn = true;
	}
	
	
	// ciclo di gioco
	@Override
	public void run() {
		
		double previous = System.nanoTime(); 
		double delta = 0.0;
		double fps = 160.0;
		double ns = 1e9/fps; // numero di nano sec per fps
		gameStatus = true;
		
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
		}
		
		private void setMusic(boolean music) {
		    this.isMusicOn = music;
		}
		
		public void playMusic(MusicTypes m) {
			String musicString = null;
			
			switch (m) {
				case HIT: {
					musicString = "./src/Music/hit.wav";
					break;
				}
				case WIN: {
					musicString = "./src/Music/gameover.wav";
					break;
				}
				case LOSE: {
					musicString = "./src/Music/gameover.wav";
					break;
				}
			}
			

			try {
			    AudioInputStream audio = AudioSystem.getAudioInputStream(new File(musicString).getAbsoluteFile());
		        this.hit = AudioSystem.getClip();
		        hit.open(audio);
		        } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	        hit.start();
		}
		
		// disegno di oggetti grafici a schermo oo
		public void render() {
			
			// creazione di 2 buffer così che l'immagine venga aggiornata su uno e mostrata sull'altro 
			// modo ciclico, evita gli scatti.
			
			BufferStrategy buffer = this.getBufferStrategy();
			
			if(buffer == null) {
				createBufferStrategy(2);
				return;	
			}
			
			Graphics g = buffer.getDrawGraphics();// oggetto di tipo Canvas su cui si può disegnare
			
			objSfondo.render(g, this);
			objBall.render(g);
			
			//for(Player ps : players) {
				
			objPaddle.render(g);
			//}
			
			// creazione brick
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
			}
			
			g.dispose();
			buffer.show();
		}
		
		// aggiornamento ciclo di gioco
		public void update() {
			
		    objBall.move();
		    this.gameStatus = objBall.checkBorderCollision();
			checkCollisionLato(objBall, objPaddle);
			checkCollision(objBall, objPaddle);
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					checkCollisionLato(objBall, tempBrick);
					checkCollision(objBall, tempBrick);
				}
			}
			objPaddle.move();
			if(!gameStatus) {
				System.out.println("game over");
				if (isMusicOn) playMusic(MusicTypes.LOSE);
			}
			
		}
		
		// inzializzazione partita
		private void start() {
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, posInitSfondo);
			
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];
			posInitBall[0] = (int) (100 + 50);  // x
			posInitBall[1] = (int) (550);  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);


			for(int i = 0; i < 5; i++) {
				
				for (int j = 1; j <4; j++) {
					
				int[] posInitBrick = new int[2];

				// posizione di partenza dei Brick
				posInitBrick[0] += 107*i;
				posInitBrick[1] += 10+j*50;
			
				// creo i Bricks
				objBricks.add(new Brick(brick, 65, 25, posInitBrick));
				}
			}


		}
		
		// check collisione tra ball e paddle dei giocatori e tra brick e ball
		
		public void checkCollisionLato(Ball ball, ScreenItem item) {
			
			if ((ball.getPosition()[1] + ball.getImageHeight()) >= item.getPosition()[1]  &&  ball.getPosition()[1] <= (item.getPosition()[1]+item.getImageHeight())) {  
				if (ball.getPosition()[0] == (item.getPosition()[0]+item.getImageWidth())) {
					ball.setXdir(1);
					item.hit();
					if (isMusicOn) playMusic(MusicTypes.HIT);
				}
				else if ((ball.getPosition()[0]+ball.getImageWidth()) == item.getPosition()[0]) {
					ball.setXdir(-1);
					item.hit(); 
					if (isMusicOn) playMusic(MusicTypes.HIT);
			    }
			}
		}
		
		public void checkCollision(Ball ball, ScreenItem item) {
			if ((ball.getPosition()[0]+ball.getImageWidth()) >= item.getPosition()[0] && ball.getPosition()[0] <= (item.getPosition()[0] + item.getImageWidth())) {
				if (ball.getPosition()[1] == (item.getPosition()[1]+item.getImageHeight())) {
					ball.setYdir(1);
					item.hit();
					if (isMusicOn) playMusic(MusicTypes.HIT);
				}
				else if ((ball.getPosition()[1] + ball.getImageHeight()) == (item.getPosition()[1])) {
					ball.setYdir(-1);
					item.hit();
					if (isMusicOn) playMusic(MusicTypes.HIT);
				}
			}	
		}

		//Aggiungo player alla partita
		public void newPlayer(Player p) {
			this.objPaddle = p.getObjPaddle();
		}
		
	}
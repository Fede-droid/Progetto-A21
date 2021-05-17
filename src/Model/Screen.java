package Model;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import GUI.ImagesLoader;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;

public class Screen extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage ball, brick, sfondo;
	private boolean gameStatus = false;
	private Ball objBall;
	private List<Brick> objBricks;
	private ScreenItem objSfondo;
	private ImagesLoader loader;
	private Paddle objPaddle;
	
	int i = 0;
	
	public Screen() {
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
		
		while (gameStatus)
		{
		double current = System.nanoTime();
		
		double elapsed = current - previous;
		previous = current;
		delta += elapsed;

		//processInput();

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
			this.ball = loader.uploadImage("/Images/ball.png");
			this.brick = loader.uploadImage("/Images/brick.png");
			this.sfondo = loader.uploadImage("/Images/sfondo.jpeg");
		}
		
		// disegno di oggetti grafici a schermo 
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
				tempBrick.render(g);
			}
			
			g.dispose();
			buffer.show();
		}
		
		// aggiornamento ciclo di gioco
		public void update() {
			
			objBall.move();
			checkCollision();
			
		}
		
		// inzializzazione partita
		private void start() {
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, 1000, 1000, posInitSfondo);
			
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];
			posInitBall[0] = 100;  // x
			posInitBall[1] = 100;  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
			
			for(int i = 0; i < 10; i++) {
				// posizione di partenza dei Brick
				int[] posInitBrick = new int[2];
				posInitBrick[0] += 10;
				posInitBrick[1] += 10;
			
				// creo i Bricks
				objBricks.add(new Brick(brick, 50, 25, posInitBrick));
			
			}
			
		}
		
		// check collisione tra ball e paddle dei giocatori
		public void checkCollision() {
			
			//for(Player ps: players) {
				
				if(objBall.getPosition()[1] == objPaddle.getPosition()[1] && ((objPaddle.getPosition()[0] <= objBall.getPosition()[0] &&  objBall.getPosition()[0] <= (objPaddle.getPosition()[0] + objPaddle.getImageWidth())))) {
				
					objBall.setYdir(-1);
				}
				else if(objBall.getPosition()[1] > Utilities.SCREEN_HEIGHT) {
					gameStatus = false;
					System.out.println("game over");
				}
			//}
		}
		
		//Aggiungo player alla partita
		
		public void newPlayer(Player p) {
			
			this.objPaddle = p.getObjPaddle();
			
		}
		
	}
	


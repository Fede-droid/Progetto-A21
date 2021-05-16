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

public class Screen extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage ball;
	BufferedImage brick;
	BufferedImage paddle;
	BufferedImage sfondo;
	private boolean gameStatus = false;
	private Ball objBall;
	private Paddle objPaddle;
	private List<Brick> objBricks;
	private ScreenItem objSfondo;
	
	int i = 0;
	
	public Screen() {
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
		//update();
			
		delta -= ns;
		}

		render();
		}
	}
	
		
		private void uploadImages() {
			
			ImagesLoader loader = new ImagesLoader();
			this.ball = loader.uploadImage("/Images/ball.png");
			this.brick = loader.uploadImage("/Images/brick.png");
			this.paddle = loader.uploadImage("/Images/paddle.png");	
			this.sfondo = loader.uploadImage("/Images/sfondo.jpeg");	
		}
		
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
			objPaddle.render(g);
			objBall.render(g);
			for (Brick tempBrick : objBricks) {
				tempBrick.render(g);
			}
			g.dispose();
			buffer.show();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			int keycode = e.getKeyCode();
			
			switch(keycode) {
				case KeyEvent.VK_LEFT: objPaddle.moveLeft();
				break;
				
				case KeyEvent.VK_RIGHT: objPaddle.moveRight();
				break;
			
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		
		private void start() {
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, 1000, 1000, posInitSfondo);
			
			// posizione di partenza paddle
			int[] posInitPaddle = new int[2];
			posInitPaddle[0] = 100;  // x
			posInitPaddle[1] = 500;  // y
			
			// creo un paddle 
			objPaddle = new Paddle(paddle, 100, 50, posInitPaddle);
		
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];
			posInitBall[0] = 100;  // x
			posInitBall[1] = 100;  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
			Thread ballThread  = new Thread(objBall);
			ballThread.start();
			
			// posizione di partenza dei Brick
			int[] posInitBrick = new int[2];
			posInitBrick[0] = 20;
			posInitBrick[1] = 20;
			
			// creo i Bricks
			objBricks = new ArrayList<Brick>();
			objBricks.add(new Brick(brick, 50, 25, posInitBrick));
			
			
		}
		
		
	}
	


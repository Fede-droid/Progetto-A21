package Model;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.Ball;
import Model.Items.Paddle;

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
			
			g.drawImage(sfondo, 0, 0, 1000, 1000, this);
			
			objPaddle.render(g);
			objBall.render(g);
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
			
			// posizione di partenza paddle
			int[] posInitPaddle = new int[2];
			posInitPaddle[0] = 100;
			posInitPaddle[1] = 200;
			
			// creo un paddle 
			this.objPaddle = new Paddle(paddle, 150, 75, posInitPaddle);
		
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];
			posInitBall[0] = 100;
			posInitBall[1] = 100;
			
			// faccio partire il thread corrispondente a ball
			this.objBall = new Ball(ball, 20, 20, posInitBall);
			Thread ballThread  = new Thread(objBall);
			ballThread.start();
		}
		
		
	}
	


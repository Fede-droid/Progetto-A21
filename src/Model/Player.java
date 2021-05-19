package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.Paddle;
import Model.Items.Utilities;

public class Player implements KeyListener{

	private Paddle objPaddle;
	private BufferedImage paddle;
	private ImagesLoader loader;

	public Player() {
		
		createImage();
		inizialize();
	}
	
	public void inizialize() {
		
		// posizione di partenza paddle
		int[] posInitPaddle = new int[2];
		posInitPaddle[0] = 100;  // x
		posInitPaddle[1] = 580;  // y
					
		// creo un paddle 
		objPaddle = new Paddle(paddle, 100, 30, posInitPaddle);
	}
	
	public void createImage() {
		
		this.loader = new ImagesLoader();
		this.paddle = loader.uploadImage("/Images/paddle.png");

	}
	
	public void move() {
		objPaddle.moveLeft();
	}
	
	/*
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
	*/
	 @Override
     public void keyReleased(KeyEvent e) {

		 objPaddle.keyReleased(e);
     }

     @Override
     public void keyPressed(KeyEvent e) {

    	 objPaddle.keyPressed(e);
     }

	@Override
	public void keyTyped(KeyEvent e) {}

	public Paddle getObjPaddle() {
		
		return objPaddle;
	}

	

	

}

package Model.Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.Paddle;
import Model.Items.Utilities;

public class Player {

	private Paddle objPaddle;
	private BufferedImage paddle;
	private ImagesLoader loader;
	private InputAdapter inputHandler;


	public Player() {
		
		createImage();
		inizialize();
		this.inputHandler = new InputAdapter(objPaddle);
	}
	
	public void inizialize() {
		
		// posizione di partenza paddle
		int[] posInitPaddle = new int[2];
		posInitPaddle[0] = Utilities.INITIAL_POSITION_PADDLE_X;  // x
		posInitPaddle[1] = Utilities.INITIAL_POSITION_PADDLE_Y;  // y
					
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

	public InputAdapter getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputAdapter inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	/*
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
	*/

	public Paddle getObjPaddle() {	
		return objPaddle;
	}
	

	

	

}

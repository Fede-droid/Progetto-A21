package Model.Logic;


import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.Paddle;
import Utility.Utilities;

public class Player {
	/*
	 * 
	 * classe che gestisce le funzionalità di un player, 
	 * ogni player possiede il suo paddle e lo mantiene 
	 * per tutta la durata del gioco
	 */
	private Paddle objPaddle;
	private BufferedImage paddle;
	private InputAdapter inputHandler;


	public Player() {
	
		inizialize();
		paddle = ImagesLoader.getInstace().uploadImage("/Images/paddle.png");
		this.inputHandler = new InputAdapter(objPaddle);
	}
	
	public void inizialize() {
		
		// posizione di partenza paddle
		int[] posInitPaddle = new int[2];
		posInitPaddle[0] = Utilities.INITIAL_POSITION_PADDLE_X;  // x
		posInitPaddle[1] = Utilities.INITIAL_POSITION_PADDLE_Y;  // y
					
		// creo un paddle 
		objPaddle = new Paddle(100, 30, posInitPaddle);

		
	}

	public InputAdapter getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputAdapter inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	public Paddle getObjPaddle() {	
		return objPaddle;
	}
	

}

package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.Ball;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Utility.Utilities;

public class BallSpeedUp extends PowerUp {
	
	
	/**
	 * power up che permette di aumetare la velocità della ball 
	 * una volta che viene distrutto 
	 */
	
	
	private String path = "/Images/fast.png";
	
	
	public BallSpeedUp(ScreenItem screenItem) {
		affectedScreenItem = screenItem;
		duringTime = 10e9;
	}

	
	/**
	 * attivazione power up
	 * 
	 */
	@Override
	public void activate() {
		((Ball)affectedScreenItem).incrSpeed();
		this.setActive(true);
	}

	/**
	 * disattivazione power up
	 * 
	 */
	@Override
	public void disactivate() {
		((Ball)affectedScreenItem).setSpeed(Utilities.DEFAULT_SPEED);
		this.setActive(false);
	}

	/**
	 * tipo potenza powerUp
	 * @return tipo power up : Fast
	 */
	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FAST;
	}
	
	
	/**
	 * path immagine
	 * 
	 */
	public String getPath() {
		return path;
	}

	
	/**
	 * attivazione multi
	 * 
	 */
	@Override
	public void activateMultiplayer(boolean active, ArrayList<Paddle> paddles) {
			
	}
}

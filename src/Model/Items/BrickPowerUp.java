package Model.Items;

import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.PowerUp.PowerUp;

public class BrickPowerUp extends Brick {
	
	/**
	 *  Brick speciale che implementa le funzioni di un powerUP
	 */
	
	private PowerUp powerUp;

	public BrickPowerUp(int width, int height, int[] position, PowerUp powerUp) {
		super(width, height, position);
		this.powerUp = powerUp;
		this.image = ImagesLoader.getInstace().uploadImage(powerUp.getPath());
		hitLevel = 1;
	}

	/**
	 *  attiva power up
	 */
	public boolean activatePowerUP() {
		if(powerUp != null && !powerUp.isActive()) {
			powerUp.startPowerUp();
			return true;
		}
		return false;
	}
	
	/**
	 * disattiva power uè
	 */
	public void disactivatePowerUp() {
		if(powerUp != null && powerUp.isActive()) powerUp.disactivate();
	}
	
	/**
	 * 
	 * @return active
	 */
	public boolean hasActivePowerUp() {
		return powerUp.isActive();
	}
	
	public PowerUp getPowerUp() {
		return powerUp;
	}
	
	
	@Override
	public BufferedImage getImage() {
    	return ImagesLoader.getInstace().uploadImage(powerUp.getPath());
    }

}

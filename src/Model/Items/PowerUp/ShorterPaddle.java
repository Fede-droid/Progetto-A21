package Model.Items.PowerUp;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class ShorterPaddle extends PowerUp {
	
	/*
	 * Power Up che permette di diminuire le dimensioni di un paddle
	 * una volta che viene distrutto 
	 * 
	 */
    private String path = "/Images/paddle.png";
	
	public ShorterPaddle(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
		duringTime = 20e9;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() - 30);
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() + 30);
		this.setActive(false);
	}
	
	public void activateMultiplayer(boolean active) {
		if(active) {
			((Paddle)affectedScreenItem).setImageWidth(70);
		} else {
			((Paddle)affectedScreenItem).setImageWidth(100);
		}
	}

	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.SHORT;
	}

	@Override
	public String getPath() {
		return path;
	}

}

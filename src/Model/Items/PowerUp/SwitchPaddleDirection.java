package Model.Items.PowerUp;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class SwitchPaddleDirection extends PowerUp {
	
	/*
	 * Power up che permette di cambiare direzione delle frecce
	 * per il movimento del paddle una volta colpito
	 *  
	 */
	private String path = "/Images/flip.png";
	private boolean oneTimeMulti;
	
	public SwitchPaddleDirection(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
		duringTime = 10e9;
		oneTimeMulti = true;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).switchDir();
		this.setActive(true);
	}

	@Override
	public void disactivate() {
			((Paddle)affectedScreenItem).switchDir();
			this.setActive(false);
	}
	
	public void activateMultiplayer(boolean active) {
		if(!active&&!oneTimeMulti) ((Paddle)affectedScreenItem).switchDir();
    	if (oneTimeMulti) {
	    	if (active) {
	    		((Paddle)affectedScreenItem).switchDir();
	    		oneTimeMulti = false;
	    	}
    	}
    	if (!active) oneTimeMulti = true;
	}
	
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FLIP;
	}

	public String getPath() {
		return path;
	}
}

package Model.Items.PowerUp;

import java.util.Timer;

import Model.Items.ScreenItem;

public abstract class PowerUp {
	
	protected boolean active;
	protected ScreenItem affectedScreenItem;
	
	public PowerUp() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active ;
	}
	
	public void startPowerUp() {
		this.activate();
		new Thread(new Runnable(){
			@Override
			public synchronized void run(){
				double timeStart = System.nanoTime();
				while(System.nanoTime() <= timeStart + 10e9);
				disactivate();
			}
		}).start();
		
	}
	
	public abstract void activate();
	
	public abstract void disactivate();
	
	public abstract PowerUpTypes whichPower();
}

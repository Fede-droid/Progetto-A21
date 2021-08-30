package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public abstract class PowerUp {
	
	/*
	 * classe astratta che permette di avere le funzionalità base 
	 * per un power up. Ogni tipo di power up estende questa classe
	 * ed implementa la sua funzione.
	 */
	
	protected boolean active;
	protected ScreenItem affectedScreenItem;
	protected double duringTime;
	private double timeStart;
	
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
				timeStart = System.nanoTime();
				while(System.nanoTime() <= timeStart + duringTime);
				disactivate();
			}
		}).start();
		
	}
	
	public abstract void activate();
	
	public abstract void disactivate();
	
	public abstract PowerUpTypes whichPower();
	
	public abstract String getPath();

	public abstract void activateMultiplayer(boolean active, ArrayList<Paddle> paddles);
}

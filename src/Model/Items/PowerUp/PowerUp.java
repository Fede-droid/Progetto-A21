package Model.Items.PowerUp;

public abstract class PowerUp {
	
	private boolean active;
	
	public PowerUp() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public abstract void activate();
	
	public abstract void disactivate();

}

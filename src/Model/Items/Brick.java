package Model.Items;

import java.awt.image.BufferedImage;

import GUI.ImagesLoader;

public class Brick extends ScreenItem{
	
	private BufferedImage[] images;
	protected int hitLevel = 4;
	private int initialHitLevel;
	protected boolean destroyed;
	
	/**
	 * Classe che definisce le funzionalità di un brick base
	 */
	public Brick(int width, int height, int[] position) { 
		super(width, height, position);
		images = new BufferedImage[hitLevel];
		if(hitLevel == 4) initialize();
		this.destroyed = false;
		initialHitLevel = hitLevel;
	}
	
	
	
	/**
	 * inzializzazione immagini dei vari livelli di brick
	 */
	
	private void initialize() {
		
		images[0] = ImagesLoader.getInstace().uploadImage("/Images/brick.png");
		images[1] = ImagesLoader.getInstace().uploadImage("/Images/brick1.png");
		images[2] = ImagesLoader.getInstace().uploadImage("/Images/brick2.png");
		images[3] = ImagesLoader.getInstace().uploadImage("/Images/brick3.png");
		
		this.image = images[0];
	}

	/**
	 * @return boolean distruzione
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * colpito, si abbassa il livello del brick
	 */
	public void hit() {
		hitLevel -= 1;
		if (hitLevel == 0) {
			destroyed = true;
		} else image = images[4-hitLevel];
	}
	
	public int getHitLevel() {
		return hitLevel;
	}
	
	/**
	 * ivello distruzione del brick, un brick non viene distrutto subito ma dopo qualche colpo
	 * @param hitLevel
	 */
	
	public void setHitLevel(int hitLevel) {
		
		this.hitLevel = hitLevel;
		if (hitLevel != 0) {
			image = images[4-hitLevel];
		} else destroyed = true;
		
	}

	/**
	 *  immagine brick 
	 * @param imageUpdated
	 */
	
	public void setImage(BufferedImage imageUpdated) {
		this.image = imageUpdated;
	}
	
	/**
	 *  ricostruzione brick
	 */
	public void refresh() {
		destroyed = false;
		this.hitLevel = initialHitLevel ;
	}
	
	/**
	 * 
	 * @return se il power up è attivo
	 */
	public boolean activatePowerUP() {
		return false;
	}
}
package Model.Items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GUI.ImagesLoader;

public class Brick extends ScreenItem{
	
	protected int hitLevel;
	protected boolean destroyed;
	
	public Brick(BufferedImage image, int width, int height, int[] position) {
		super(image, width, height, position);
		this.destroyed = false;
		this.hitLevel = 4;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void hit() {
		hitLevel -= 1;
		if (hitLevel == 0) {
			destroyed = true;
		}
	}
	
	public int getHitLevel() {
		return hitLevel;
	}
	
	public void setImage(BufferedImage imageUpdated) {
		this.image = imageUpdated;
	}
	
	public void resfresh() {
		destroyed = false;
		hitLevel = 4;
	}
}
	

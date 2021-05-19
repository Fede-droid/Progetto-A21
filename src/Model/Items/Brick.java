package Model.Items;

import java.awt.image.BufferedImage;

public class Brick extends ScreenItem{
	
	private int hitLevel;
	private boolean destroyed;
	
	public Brick(BufferedImage image, int width, int height, int[] position) {
		super(image, width, height, position);
		this.destroyed = false;
		this.hitLevel = 2;
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
}
	

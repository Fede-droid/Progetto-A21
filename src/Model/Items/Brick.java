package Model.Items;

import java.awt.image.BufferedImage;

public class Brick extends ScreenItem{
	
	private boolean destroyed;
	
	public Brick(BufferedImage image, int width, int height, int[] position) {
		super(image, width, height, position);
		destroyed = false;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean val) {
		destroyed = val;
	}
}
	

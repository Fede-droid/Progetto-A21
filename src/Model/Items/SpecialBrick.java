package Model.Items;

import java.awt.image.BufferedImage;

public class SpecialBrick extends Brick {

	public SpecialBrick(BufferedImage image, int width, int height, int[] position) {
		super(image, width, height, position);
		super.destroyed = false;
		super.hitLevel = 1;
	}
	
	@Override
	public void refresh() {
		destroyed = false;
		hitLevel = 1;
	}
}

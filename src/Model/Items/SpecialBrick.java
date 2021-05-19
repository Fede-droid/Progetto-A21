package Model.Items;

import java.awt.image.BufferedImage;

public class SpecialBrick extends Brick {

	public SpecialBrick(BufferedImage image, int width, int height, int[] position) {
		super(image, width, height, position);
		this.hitLevel = 1;
		this.destroyed = false;
	}
	
	
	
	

}

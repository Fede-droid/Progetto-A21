package Model.Items;

import java.awt.image.BufferedImage;

public interface Drawable {
	
	// interfaccia che definisce i metodi base che un oggetto disegnabile deve avere
	
	public BufferedImage getImage();
	
	public int[] getPosition();
	
	public int getImageWidth();
	
	public int getImageHeight();

}

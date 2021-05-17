package Model.Items;

import java.awt.image.BufferedImage;

public class Paddle extends ScreenItem {
	
	private static final int VELOCITA = 20;

    public Paddle(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    }
    
    
    // Metodi che muovono il paddle a destra o sinistra
    public void moveRight() {
    	
    	// prima del confronto sommo la larghezza dello schermo con la larghezza del paddle, altrimenti esce
    	if((position[0] + imageWidth) < Utilities.SCREEN_WIDTH) position[0]+= VELOCITA;
    	
    }
    
    public void moveLeft() {
    	
    	if((position[0]) > 0) position[0]-= VELOCITA;
    	
    }
    
	

}

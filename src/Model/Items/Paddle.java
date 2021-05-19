package Model.Items;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Paddle extends ScreenItem {
	
	private static final int VELOCITA = 2;
    private int dx;


    public Paddle(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    }
    
    public void move() {

        position[0] += dx;

        if (position[0] <= 0) {

        	position[0] = 0;
        }

        if (position[0] >= Utilities.SCREEN_WIDTH - imageWidth) {

        	position[0] = Utilities.SCREEN_WIDTH - imageWidth;
        }
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

        	dx = -VELOCITA;
        }

        if (key == KeyEvent.VK_RIGHT) {

        	dx = VELOCITA;
        }
    }

    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

        	dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

        	dx = 0;
        }
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

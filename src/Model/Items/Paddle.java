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
    	// piccolo bug dentr
    	if((position[0] + imageWidth) < Utilities.SCREEN_WIDTH) position[0]+= VELOCITA;
    	
    }
    
    public void moveLeft() {
    	
    	if((position[0]) > 0) position[0]-= VELOCITA;
    	
    }
    
    
    /*
    public void move() {

        position[0] += dx;

        if ( position[0] <= 0) {

        	 position[0] = 0;
        }

       // if ( position[0] >= Utilities.WIDTH - imageWidth) {

       // 	 position[0] = Utilities.WIDTH - imageWidth;
        //}
    }

    private void resetState() {

    	 position[0] = Utilities.INIT_PADDLE_X;
    	 position[1] = Utilities.INIT_PADDLE_Y;
    }
*/
	

}

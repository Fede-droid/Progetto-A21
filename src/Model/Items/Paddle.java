package Model.Items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Model.BreakoutGame;


public class Paddle extends ScreenItem {
	

	private int width;
	private int height;
	BufferedImage imgPaddle;
	private int position[];
	private static final int VELOCITA = 20;

    public Paddle(BufferedImage image, int width, int height, int[] position) {

    	this.position = position;
    	this.width = width;
    	this.height = height;
    	this.status = true;
    	this.imgPaddle = image;
    }
    
    
    // Metodi che muovono il paddle a destra o sinistra
    public void moveRight() {
    	
    	// prima del confronto sommo la larghezza dello schermo con la larghezza del paddle, altrimenti esce
    	// piccolo bug dentr
    	if((position[0] + width) < Utilities.SCREEN_WIDTH) position[0]+= VELOCITA;
    	
    }
    
    public void moveLeft() {
    	
    	if((position[0]) > 0) position[0]-= VELOCITA;
    	
    }
    
    
  
    public void render(Graphics g) {
    	
    	// si disegna
    	g.drawImage(imgPaddle, position[0], position[1], width, height, null);
    	
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

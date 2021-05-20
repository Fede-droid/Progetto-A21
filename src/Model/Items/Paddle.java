package Model.Items;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Paddle extends ScreenItem {
	
	// velocità paddle 
	private static final int VELOCITA = 2;
	
	// direzione paddle
    private int dr;

    public Paddle(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    }
    
    // richiamato da update, verifica che non si vada fuori dai bordi dx e sx
    public void move() {

        position[0] += dr;

        if (position[0] <= 0) {

        	position[0] = 0;
        }

        if (position[0] >= Utilities.SCREEN_WIDTH - imageWidth) {

        	position[0] = Utilities.SCREEN_WIDTH - imageWidth;
        }
    }
    
    // appena si preme il paddle si aggiorna ad ogni frame in base alla velocità impostata
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

        	dr = -VELOCITA;
        }

        if (key == KeyEvent.VK_RIGHT) {

        	dr = VELOCITA;
        }
    }

    // al rilascio del stato il paddle si ferma, controllo da tastiera utente
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

        	dr = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

        	dr = 0;
        }
    }
    
    
    /*
     * METODI INUTILI DA RIMUOVERE IN FUTURO
     */
    // Metodi che muovono il paddle a destra o sinistra, per ora INUTILI
    public void moveRight() {
    	
    	// prima del confronto sommo la larghezza dello schermo con la larghezza del paddle, altrimenti esce
    	if((position[0] + imageWidth) < Utilities.SCREEN_WIDTH) position[0]+= VELOCITA;
    	
    }
    
    public void moveLeft() {
    	
    	if((position[0]) > 0) position[0]-= VELOCITA;
    	
    }
    
	

}

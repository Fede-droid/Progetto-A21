package Model.Items;

import java.awt.image.BufferedImage;


public class Ball extends ScreenItem{
	
	private boolean active;
	private double xdir;
	private double ydir;
	private double velocita;
	
    public Ball(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    	this.active = true;
    	this.velocita = 1;
    	
        xdir = 1;
        ydir = -1;
        
    }
    
    public void move() {

        position[0] += xdir * velocita;
        position[1] += ydir * velocita;

        if ( position[0] <= 0) {

            xdir = 1;
        }

        if ( position[0] >= Utilities.SCREEN_WIDTH - imageWidth) {

            xdir = -1;
        }

        if (position[1] <= 0) {

            ydir = 1;
        }
    }
	    

	public double getXdir() {
		return xdir;
	}



	public void setXdir(double xdir) {
		this.xdir = xdir;
	}



	public double getYdir() {
		return ydir;
	}



	public void setYdir(double ydir) {
		this.ydir = ydir;
	}
	
	
	public void setVelocita(double val) {
		
		velocita = val;
		
	}
	
  
	
}

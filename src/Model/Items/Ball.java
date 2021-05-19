package Model.Items;

import java.awt.image.BufferedImage;

public class Ball extends ScreenItem{
	
	private boolean active;
	private int direction;
	private double xdir;
	private double ydir;

    public Ball(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    	this.active = true;
    	this.direction = 1;
        xdir = 1;
        ydir = -1;
    }
    

    public void move() {
    	
		position[0] += xdir;
	    position[1] += ydir;
	}
	    

	public double getXdir() {
		return xdir;
	}



	public void setXdir(double xdir) {
		this.xdir *= xdir;
	}



	public double getYdir() {
		return ydir;
	}



	public void setYdir(double ydir) {
		this.ydir *= ydir;
	}
	
	
	public void setVelocitaX(double val) {
		
		xdir+=val;
		
	}
	
	public void setVelocitaY(double val) {
		
		ydir+=val;
		
	}
    
  
	
}

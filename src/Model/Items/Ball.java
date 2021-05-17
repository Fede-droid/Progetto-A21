package Model.Items;

import java.awt.image.BufferedImage;

public class Ball extends ScreenItem{
	
	private boolean active;
	private int direction;
	private int xdir;
	private int ydir;

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
	
	    if (position[0] == 0) {
	
	    	xdir = 1;
	    }
	
	    if (position[0] == Utilities.SCREEN_WIDTH - imageWidth) {
	
	        System.out.println(imageWidth);
	        xdir = -1;
	    }
	
	    if (position[1] == 0) {
	
	    	ydir = 1;
	    }
	    }
	    
    public void move2() {
    	
    	if(direction == 1 && position[0] > 0 && (position[0] + imageWidth) < Utilities.SCREEN_WIDTH) {
    		
    		position[0]++;
    		
    		}
    	
    	else if (position[0] < 0) {
    		position[0]++;
    		direction = 1;
    	}
    	
    	
    	else if ((position[0] + imageWidth) > Utilities.SCREEN_WIDTH){
    		direction = -1; 
    		position[0] -= 1;
    		}
    	
    	
    }



	public int getXdir() {
		return xdir;
	}



	public void setXdir(int xdir) {
		this.xdir = xdir;
	}



	public int getYdir() {
		return ydir;
	}



	public void setYdir(int ydir) {
		this.ydir = ydir;
	}
    
    
	/*
	private int xdir;
	private int ydir;
	
	public Ball() {
	
	    initBall();
	}
	
	private void initBall() {
	
	    xdir = 1;
	    ydir = -1;
	
	    loadImage();
	   // getImageDimensions();
	    resetState();
	}
	
	private void loadImage() {
	
	    Object ii = new ImageIcon("src/Images/ball.png");
	    image = ((ImageIcon) ii).getImage();
	}
	
	void move() {
	
		position[0] += xdir;
	    position[1] += ydir;
	
	    if (position[0] == 0) {
	
	    	setDirectionX(1);
	    }
	
	    if (position[0] == Utilities.WIDTH - imageWidth) {
	
	        System.out.println(imageWidth);
	        setDirectionX(-1);
	    }
	
	    if (position[1] == 0) {
	
	    	setDirectionY(1);
	    }
	}
	
	private void resetState() {
	
	    position[0] = Utilities.INIT_BALL_X;
	    position[1]= Utilities.INIT_BALL_Y;
	}
	
	void setDirectionX(int x) {
	
	    xdir = x;
	}
	
	void setDirectionY(int y) {
	
	    ydir = y;
	}
	
	int getYDir() {
	
	    return ydir;
	}
		
		*/
	
}

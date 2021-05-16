package Model.Items;

import java.awt.image.BufferedImage;

public class Ball extends ScreenItem implements Runnable{
	
	private boolean active;

    public Ball(BufferedImage image, int width, int height, int[] position) {
    	super(image, width, height, position);
    	this.active = true;
    }
    

    @Override
	public void run() {
    	active = true;
    	while(active) {
		update(); 
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	}
	}

    private void update() {
    	
    	position[0]++;
    	position[1]++;
    	
    
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

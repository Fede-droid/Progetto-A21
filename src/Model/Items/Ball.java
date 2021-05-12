package Model.Items;

import javax.swing.ImageIcon;

public class Ball extends ScreenItem{
	
	private int xdir;
	private int ydir;
	
	public Ball() {
	
	    initBall();
	}
	
	private void initBall() {
	
	    xdir = 1;
	    ydir = -1;
	
	    loadImage();
	    getImageDimensions();
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
	
	        setXDir(1);
	    }
	
	    if (position[0] == Utilities.WIDTH - imageWidth) {
	
	        System.out.println(imageWidth);
	        setXDir(-1);
	    }
	
	    if (position[1] == 0) {
	
	        setYDir(1);
	    }
	}
	
	private void resetState() {
	
	    position[0] = Utilities.INIT_BALL_X;
	    position[1]= Utilities.INIT_BALL_Y;
	}
	
	void setXDir(int x) {
	
	    xdir = x;
	}
	
	void setYDir(int y) {
	
	    ydir = y;
	}
	
	int getYDir() {
	
	    return ydir;
	}
		
		
	
}

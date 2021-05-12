package Model.Items;

import javax.swing.ImageIcon;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;

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
	
	    Object ii = new ImageIcon("src/resources/ball.png");
	    image = ((ImageIcon) ii).getImage();
	}
	
	void move() {
	
	    x += xdir;
	    y += ydir;
	
	    if (x == 0) {
	
	        setXDir(1);
	    }
	
	    if (x == Commons.WIDTH - imageWidth) {
	
	        System.out.println(imageWidth);
	        setXDir(-1);
	    }
	
	    if (y == 0) {
	
	        setYDir(1);
	    }
	}
	
	private void resetState() {
	
	    x = Commons.INIT_BALL_X;
	    y = Commons.INIT_BALL_Y;
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

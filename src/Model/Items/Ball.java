package Model.Items;

import java.awt.image.BufferedImage;

public class Ball extends ScreenItem{

    private boolean active;
    private int direction;
    private int xdir;
    private int ydir;
    private int xdir1;
    private int ydir1;


    public Ball(BufferedImage image, int width, int height, int[] position) {
        super(image, width, height, position);
        this.active = true;
        this.direction = 1;
        xdir = 1;
        xdir1 = 1;
        ydir = -1;
        ydir1 = -1;
    }

    public void move() {
        position[0] += xdir1;
        position[1] += ydir1;
    }

    public int getXdir() {
        return xdir1;
    }
    
    public int getYdir() {
        return ydir1;
    }
    
    //i metodi setxdir e setydir vengono chiamati quando la pallina colpisce il padle o il mattone
    public void setXdir(int xdir) {
        this.xdir = xdir;
        if (this.xdir*xdir1<0) xdir1*=-1;
    }

    public void setYdir(int ydir) {
        this.ydir = ydir;
        if (this.ydir*ydir1<0) ydir1*=-1;
    }
    
    public int getSpeed() {
    	int vel = xdir1;
    	if (xdir1<0) vel*=-1;
    	return vel;
    }
    
    public void incrSpeed() {
    	if (xdir1==-1) xdir1 -= 1;
    	else if(xdir1==1) xdir1 += 1;
    	if (ydir1==-1) ydir1 -= 1;
    	else if (ydir1==1) ydir1 += 1;   		
    }
}
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
        xdir = Utilities.INITIAL_DIRECTION_BALL_X;
        ydir = Utilities.INITIAL_DIRECTION_BALL_Y;
    }


    public void move() {

        position[0] += xdir;
        position[1] += ydir;

    }

    public int getXdir() {
        return xdir;
    }
    
    //i metodi setxdir e setydir vengono chiamati quando la pallina colpisce il padle o il mattone
    public void setXdir(int xdir) {
        this.xdir = xdir;
    }

    public int getYdir() {
        return ydir;
    }

    public void setYdir(int ydir) {
        this.ydir = ydir;
    }



}
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

    }

    public boolean checkBorderCollision() {
         if ((position[0] + this.imageWidth) == Utilities.SCREEN_WIDTH) {
             setXdir(-1);
         }

         if (position[0] == 0) {
             setXdir(1);
         }

         if (position[1] == 0) {
             setYdir(1);
         }
         
         if((position[1] + this.imageHeight) > Utilities.SCREEN_HEIGHT) {
                return false;
         }
         else return true;
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
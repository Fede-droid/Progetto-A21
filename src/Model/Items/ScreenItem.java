package Model.Items;

import java.awt.*;

public class ScreenItem {
	
    protected int imageWidth;
    protected int imageHeight;
    protected Image image;
    protected int position[]; // position[0] = x, position[1] = y;
    
   
	protected void setPosition(int x, int y) {
    	
    	this.position[0] = x;
    	this.position[1] = y;
    }
    
    protected int[] getPosition() {
    	
    	return position;
    }
    

    int getImageWidth() {

        return imageWidth;
    }

    int getImageHeight() {

        return imageHeight;
    }

    protected Image getImage() {

        return image;
    }
 
    Rectangle getRect() {

        return new Rectangle(position[0], position[1], image.getWidth(null), image.getHeight(null));
    }

    void getImageDimensions() {

        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
	

}

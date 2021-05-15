package Model.Items;

import java.awt.*;

// classe astratta per metodi e varibili comuni fra gli oggetti
public abstract class ScreenItem {
	
    protected int imageWidth;
    protected int imageHeight;
    protected Image image;
    protected int position[]; // position[0] = x, position[1] = y;
    protected boolean status;
   
	public void setPosition(int x, int y) {
    	
    	this.position[0] = x;
    	this.position[1] = y;
    }
    
    public int[] getPosition() {
    	
    	return position;
    }
    

    public int getImageWidth() {

        return imageWidth;
    }

    public int getImageHeight() {

        return imageHeight;
    }

    public Image getImage() {

        return image;
    }
 
 
    public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	Rectangle getRect() {

        return new Rectangle(position[0], position[1], image.getWidth(null), image.getHeight(null));
    }
	
	
	

}

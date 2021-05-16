package Model.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

// classe astratta per metodi e varibili comuni fra gli oggetti
public class ScreenItem {
	
	protected BufferedImage image;
    protected int imageWidth;
    protected int imageHeight;
    protected int position[]; // position[0] = x, position[1] = y;
    
    public ScreenItem(BufferedImage image, int width, int height, int[] position) {
    	this.image = image;
    	this.imageWidth = width;
    	this.imageHeight = height;
    	this.position = position;
    }
    
    
    public void render(Graphics g, Canvas c) {
    	
    	// si disegna
    	g.drawImage(image, position[0], position[1], imageWidth, imageHeight, c);
    	
    }
   
    
    
    public void render(Graphics g) {
    	
    	// si disegna
    	g.drawImage(image, position[0], position[1], imageWidth, imageHeight, null);
    	
    }
    
    public int[] getPosition() {
    	
    	return position;
    }
    
	public void setPosition(int x, int y) {
    	
    	this.position[0] = x;
    	this.position[1] = y;
    }
    
	/*
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
	
	
	*/
	
	

}

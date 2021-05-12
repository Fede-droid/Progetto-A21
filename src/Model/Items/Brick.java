package Model.Items;

import javax.swing.ImageIcon;

public class Brick extends ScreenItem{
	
	private boolean destroyed;
	
	public Brick(int x, int y) {
		
		initBrick(x, y);
	}

	    private void initBrick(int x, int y) {

	        this.x = x;
	        this.y = y;

	        destroyed = false;

	        loadImage();
	        getImageDimensions();
	    }

	    private void loadImage() {

	        Object ii = new ImageIcon("src/Images/brick.png");
	        image = ((ImageIcon) ii).getImage();
	    }

	    boolean isDestroyed() {

	        return destroyed;
	    }

	    void setDestroyed(boolean val) {

	        destroyed = val;
	    }
	}
	

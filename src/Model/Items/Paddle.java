package Model.Items;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Paddle extends ScreenItem{
	
	private int dx;

    public Paddle() {

        initPaddle();
    }

    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }

    private void loadImage() {

        var ii = new ImageIcon("src/Images/paddle.png");
        image = ii.getImage();
    }

    public void move() {

        position[0] += dx;

        if ( position[0] <= 0) {

        	 position[0] = 0;
        }

        if ( position[0] >= Utilities.WIDTH - imageWidth) {

        	 position[0] = Utilities.WIDTH - imageWidth;
        }
    }

    private void resetState() {

    	 position[0] = Utilities.INIT_PADDLE_X;
    	 position[1] = Utilities.INIT_PADDLE_Y;
    }

}

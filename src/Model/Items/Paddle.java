package Model.Items;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Paddle {
	
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

        Object ii = new ImageIcon("src/Images/paddle.png");
        image = ((ImageIcon) ii).getImage();
    }

    void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        if (x >= Utilities.WIDTH - imageWidth) {

            x = Utilities.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    private void resetState() {

        x = Utilities.INIT_PADDLE_X;
        y = Utilities.INIT_PADDLE_Y;
    }

}

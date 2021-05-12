package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class Panel extends JPanel{
	
	static final int SCREEN_WIDTH =1300;
	static final int SCREEN_HEIGHT =750;
	
	public Panel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        
		
		
		
	}

}

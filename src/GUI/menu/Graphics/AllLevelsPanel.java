package GUI.menu.Graphics;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class AllLevelsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, submit;
	ImagesLoader loader;
	
	public AllLevelsPanel(BreakoutGame game) {
		
		this.loader = ImagesLoader.getInstace();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/background.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		
		backgroundlabel.setLayout(new GridLayout(3,2));
		 
		for(int i = 1; i < game.getNumberOfLevels(); i++ ) {
			
			backgroundlabel.add(new JButton(""+i));
		}
		
	}
}

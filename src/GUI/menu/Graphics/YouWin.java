package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import Utility.Utilities;

	
public class YouWin extends JPanel{
	
	private static final long serialVersionUID = 2L;
	BufferedImage background, background2; 
	ImagesLoader loader;
	private BufferedImage button1;
	
	public YouWin() {
		
		this.loader = ImagesLoader.getInstace();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/background.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		
	
		// bottone 1 pplayer
		this.button1 = loader.uploadImage("menu/menuImages/youWin.png");
		ImageIcon button1Img = new ImageIcon(button1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(button1Img);
		backgroundlabel.add(button);
		
		
	}


}

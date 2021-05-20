package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GameFrame;
import GUI.ImagesLoader;
import GUI.menu.listeners.RepeatListener;
import GUI.menu.listeners.SinglePlayerListener;
import Model.BreakoutGame;
import Model.Items.Utilities;
import java.awt.image.BufferedImage;

public class PauseMenu extends JPanel  {
	
	BufferedImage background, button1, button2, button3, button4;
	ImagesLoader loader;
	private boolean win;
	
	public PauseMenu(BreakoutGame game, boolean win) {
		
		this.loader = new ImagesLoader();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/mainMenupng.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		// bottone 1 
		this.button1 = loader.uploadImage("menu/menuImages/playAgain.png");
		ImageIcon button1Img = new ImageIcon(button1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(button1Img);
		backgroundlabel.add(button);
		RepeatListener a1 = new RepeatListener(game, this);
		//button.addActionListener(a1);
		
		// bottone 3
		this.button3 = loader.uploadImage("menu/menuImages/nextLV.png");
		ImageIcon button3Img = new ImageIcon(button3);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button3 = new JButton();
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setIcon(button3Img);
		backgroundlabel.add(button3);
		RepeatListener a2 = new RepeatListener(game, this);
		//button.addActionListener(a1);
		
		
	}

}

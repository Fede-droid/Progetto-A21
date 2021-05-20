package GUI.menu;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GameFrame;
import GUI.ImagesLoader;
import GUI.menu.listeners.SinglePlayerListener;
import Model.Items.Utilities;

public class MainMenu extends JPanel {
	

	BufferedImage background, button1, button2, button3, button4;
	ImagesLoader loader;
	
	public MainMenu(GameFrame f) {
		
		this.loader = new ImagesLoader();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/background.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		// bottone 1 pplayer
		this.button1 = loader.uploadImage("menu/menuImages/button1.png");
		ImageIcon button1Img = new ImageIcon(button1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(button1Img);
		backgroundlabel.add(button);
		SinglePlayerListener a1 = new SinglePlayerListener(f, this);
		button.addActionListener(a1);
		
		// bottone 2 multiplayer
		this.button2 = loader.uploadImage("menu/menuImages/button2.png");
		ImageIcon button2Img = new ImageIcon(button2);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button2 = new JButton();
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setIcon(button2Img);
		backgroundlabel.add(button2);
		
		// bottone 3 impostazioni
		this.button3 = loader.uploadImage("menu/menuImages/button3.png");
		ImageIcon button3Img = new ImageIcon(button3);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button3 = new JButton();
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setIcon(button3Img);
		backgroundlabel.add(button3);
		
		// bottone 4 sounds
		this.button4 = loader.uploadImage("menu/menuImages/button4ON.png");
		ImageIcon button4Img = new ImageIcon(button4);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button4 = new JButton();
		button4.setOpaque(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button4.setIcon(button4Img);
		backgroundlabel.add(button4);
		//change 
		
	}
	
	
	
	
	
	
	
}

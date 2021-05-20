package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GameFrame;
import GUI.ImagesLoader;
import GUI.menu.listeners.SinglePlayerListener;
import GUI.menu.listeners.SoundListener;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class MainMenu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, button3, button4, button5;
	ImagesLoader loader;
	private JButton musicButtonON, musicButtonOFF;
	
	public MainMenu(BreakoutGame c) {
		
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
		SinglePlayerListener a1 = new SinglePlayerListener(c, this);
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
		
		// bottone 4 ON
		this.button4 = loader.uploadImage("menu/menuImages/button4ON.png");
		ImageIcon button4Img = new ImageIcon(button4);
		backgroundlabel.setLayout(new FlowLayout() );
		this.musicButtonON = new JButton();
		musicButtonON.setOpaque(false);
		musicButtonON.setContentAreaFilled(false);
		musicButtonON.setBorderPainted(false);
		musicButtonON.setIcon(button4Img);
		backgroundlabel.add(musicButtonON);
		SoundListener a4 = new SoundListener(c, this, false);
		musicButtonON.addActionListener(a4);
		
		// bottone  5 OFF
		this.button5 = loader.uploadImage("menu/menuImages/button4OFF.png");
		ImageIcon button5Img = new ImageIcon(button5);
		backgroundlabel.setLayout(new FlowLayout() );
		this.musicButtonOFF = new JButton();
		musicButtonOFF.setOpaque(false);
		musicButtonOFF.setContentAreaFilled(false);
		musicButtonOFF.setBorderPainted(false);
		musicButtonOFF.setIcon(button5Img);
		backgroundlabel.add(musicButtonOFF);
		SoundListener a5 = new SoundListener(c, this, true);
		musicButtonOFF.addActionListener(a5);
		setMusicButton(false);
		
	}
	
	public void setMusicButton(boolean bool) {
		
		musicButtonON.setVisible(!bool);
		musicButtonOFF.setVisible(bool);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import GUI.menu.listeners.MultiplayerListener;
import GUI.menu.listeners.SoundListener;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class MainMenu extends JPanel{

	/**
	 * MENU PRINCIPALE DOPO L'INTRO
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	BufferedImage background, button1, button2, button3, button4, button5, button6, button7, switch1, switch2, bot;
	ImagesLoader loader;
	private JButton musicButtonON, musicButtonOFF, allLevels;
	private boolean singleSwitch;
	
	
	public MainMenu(BreakoutGame c) {
		
		this.loader = ImagesLoader.getInstace();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/background.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
	
		
		// bottone bot mode off
		this.switch1 = loader.uploadImage("menu/menuImages/switch1.png");
		ImageIcon switch1Img = new ImageIcon(switch1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton switch1 = new JButton();
		inizializeButton(switch1, switch1Img);
		backgroundlabel.add(switch1);
		switch1.setVisible(!singleSwitch);

		
		// bottone  bot mode on
		this.switch2 = loader.uploadImage("menu/menuImages/switch2.png");
		ImageIcon switch2Img = new ImageIcon(switch2);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton switch2 = new JButton();
		inizializeButton(switch2, switch2Img);
		backgroundlabel.add(switch2);
		switch2.setVisible(singleSwitch);

		ActionListener setVisibileSwitch1 = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	switch1.setVisible(singleSwitch);
		    	switch2.setVisible(!singleSwitch);
		    	singleSwitch = !singleSwitch;
		    	repaint();
		    }
		};
		
		switch1.addActionListener(setVisibileSwitch1);
		switch2.addActionListener(setVisibileSwitch1);
		
		
		
		// bottone 1 pplayer
		this.button1 = loader.uploadImage("menu/menuImages/button1.png");
		ImageIcon button1Img = new ImageIcon(button1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button = new JButton();
		inizializeButton(button, button1Img);
		backgroundlabel.add(button);
		
		
		ActionListener singlePlayerListener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	removeAll();
		    	setVisible(false);
				c.gameSetupSinglePlayer(singleSwitch);
				
		    }
		};
		
		button.addActionListener(singlePlayerListener);
		
		
		// bottone 2 multiplayer
		this.button2 = loader.uploadImage("menu/menuImages/button2.png");
		ImageIcon button2Img = new ImageIcon(button2);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button2 = new JButton();
		inizializeButton(button2, button2Img);
		backgroundlabel.add(button2);
		MultiplayerListener a2 = new MultiplayerListener(c, this);
		button2.addActionListener(a2);
		
		
		// bottone 4 ON
		this.button4 = loader.uploadImage("menu/menuImages/button4ON.png");
		ImageIcon button4Img = new ImageIcon(button4);
		backgroundlabel.setLayout(new FlowLayout() );
		this.musicButtonON = new JButton();
		inizializeButton(musicButtonON, button4Img);
		backgroundlabel.add(musicButtonON);
		SoundListener a4 = new SoundListener(c, this, false);
		musicButtonON.addActionListener(a4);
		
		// bottone  5 OFF
		this.button5 = loader.uploadImage("menu/menuImages/button4OFF.png");
		ImageIcon button5Img = new ImageIcon(button5);
		backgroundlabel.setLayout(new FlowLayout() );
		this.musicButtonOFF = new JButton();
		inizializeButton(musicButtonOFF, button5Img);
		backgroundlabel.add(musicButtonOFF);
		SoundListener a5 = new SoundListener(c, this, true);
		musicButtonOFF.addActionListener(a5);
		setMusicButton(false);
		
		
		
	}
	
	// gestione musica accesa o spenta
	public void setMusicButton(boolean bool) {
		
		musicButtonON.setVisible(!bool);
		musicButtonOFF.setVisible(bool);
		
	}
	
	// setta caratteristiche bottone 
	
	public void inizializeButton(JButton butt, ImageIcon imag) {
		
		butt.setOpaque(false);
		butt.setContentAreaFilled(false);
		butt.setBorderPainted(false);
		butt.setIcon(imag);
	}
	
	
	
	
	
	
	
	
	
}

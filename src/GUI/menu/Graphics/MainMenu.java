package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import GUI.menu.listeners.MultiplayerListener;
import GUI.menu.listeners.SetLevel;
import GUI.menu.listeners.SinglePlayerListener;
import GUI.menu.listeners.SoundListener;
import Model.BreakoutGame;
import Model.Core.TypeLevels;
import Model.Items.Utilities;

public class MainMenu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, button3, button4, button5, button6, button7, switch1, switch2, bot;
	
	ImagesLoader loader;
	private JButton musicButtonON, musicButtonOFF, level1Button, level2Button;
	private boolean singleSwitch;
	
	
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
		
		// bottone switch1
		this.switch1 = loader.uploadImage("menu/menuImages/switch1.png");
		ImageIcon switch1Img = new ImageIcon(switch1);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton switch1 = new JButton();
		switch1.setOpaque(false);
		switch1.setContentAreaFilled(false);
		switch1.setBorderPainted(false);
		switch1.setIcon(switch1Img);
		backgroundlabel.add(switch1);
		switch1.setVisible(!singleSwitch);

		
		// bottone switch2
		this.switch2 = loader.uploadImage("menu/menuImages/switch2.png");
		ImageIcon switch2Img = new ImageIcon(switch2);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton switch2 = new JButton();
		switch2.setOpaque(false);
		switch2.setContentAreaFilled(false);
		switch2.setBorderPainted(false);
		switch2.setIcon(switch2Img);
		backgroundlabel.add(switch2);
		switch2.setVisible(singleSwitch);
		

	
		ActionListener setVisibileSwitch1 = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	singleSwitch = !singleSwitch;
		    	switch1.setVisible(singleSwitch);
		    	switch2.setVisible(!singleSwitch);
		    	repaint();
		    }
		};
		
		switch1.addActionListener(setVisibileSwitch1);
		switch2.addActionListener(setVisibileSwitch1);
		
		
		
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
		MultiplayerListener a2 = new MultiplayerListener(c, this);
		button2.addActionListener(a2);
		
		
		
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
		
		
		// bottone 4 level 1
		this.button6 = loader.uploadImage("menu/menuImages/level2.png");
		ImageIcon button6Img = new ImageIcon(button6);
		backgroundlabel.setLayout(new FlowLayout() );
		this.level1Button = new JButton();
		level1Button.setOpaque(false);
		level1Button.setContentAreaFilled(false);
		level1Button.setBorderPainted(false);
		level1Button.setIcon(button6Img);
		backgroundlabel.add(level1Button);
		SetLevel l1 = new SetLevel(c, this, TypeLevels.LEVEL1);
		level1Button.addActionListener(l1);
		
		// bottone  5 level 2
		this.button7 = loader.uploadImage("menu/menuImages/level1.png");
		ImageIcon button7Img = new ImageIcon(button7);
		backgroundlabel.setLayout(new FlowLayout() );
		this.level2Button = new JButton();
		level2Button.setOpaque(false);
		level2Button.setContentAreaFilled(false);
		level2Button.setBorderPainted(false);
		level2Button.setIcon(button7Img);
		backgroundlabel.add(level2Button);
		SetLevel l2 = new SetLevel(c, this, TypeLevels.LEVEL2);
		level2Button.addActionListener(l2);
		//level2Button.setVisible(false);
		
		setLevelButton(TypeLevels.LEVEL1);
	}
	
	public void setMusicButton(boolean bool) {
		
		musicButtonON.setVisible(!bool);
		musicButtonOFF.setVisible(bool);
		
	}
	
	public void setLevelButton(TypeLevels lv) {
		
		switch(lv){
		
		case LEVEL1: level1Button.setVisible(false);
					 level2Button.setVisible(true);
					 break;
		case LEVEL2: level1Button.setVisible(true);
		 			 level2Button.setVisible(false);
		 			 break;
		}
		repaint();
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

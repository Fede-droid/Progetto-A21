package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import GUI.menu.listeners.NextLevelListener;
import GUI.menu.listeners.RepeatListener;
import Model.BreakoutGame;
import Model.Items.Utilities;
import java.awt.image.BufferedImage;

public class PauseMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, button3, button4;
	ImagesLoader loader;
	
	private boolean win;
	
	public PauseMenu(BreakoutGame game, boolean win) {
		
		this.loader = new ImagesLoader();
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/background.jpg");
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
		//RepeatListener a1 = new RepeatListener(game, this);
		//button.addActionListener(a1);
		button.setVisible(!win);
		
		ActionListener playAgainListener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	removeAll();
		    	setVisible(false);
		    	
		    	game.gameSetupSinglePlayer(game.getBotMode());
		    	repaint();
		    }
		};
		
		button.addActionListener(playAgainListener);
		
		
		// mega bug 
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
		button3.setVisible(false);
		NextLevelListener a2 = new NextLevelListener(game, this);
		button3.addActionListener(a2);
		
		//
		// bottone 4
		this.button4 = loader.uploadImage("menu/menuImages/mainMenupng.png");
		ImageIcon button4Img = new ImageIcon(button4);
		backgroundlabel.setLayout(new FlowLayout() );
		JButton button4 = new JButton();
		button4.setOpaque(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button4.setIcon(button4Img);
		backgroundlabel.add(button4);
		

		ActionListener return2main = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	removeAll();
		    	setVisible(false);
		    	game.reset();
		    	
		    	repaint();
		    }
		};
		button4.addActionListener(return2main);
	
	
		
		
		JLabel label1 = new JLabel();
		label1.setFont(new Font("Courier", Font.BOLD, 50)); 
		label1.setText("LAST SCORE: " + game.getLastScore());
		//label1.setBounds(0, 0, 200, 50);
		label1.setLocation(100, 600);
		label1.setVisible(true);
		backgroundlabel.add(label1);
		
	}

}

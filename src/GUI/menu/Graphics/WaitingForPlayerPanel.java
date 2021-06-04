package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
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

public class WaitingForPlayerPanel extends JPanel{
	
	 
		private static final long serialVersionUID = 1L;
		BufferedImage background, button1, button2, button3, button4;
		ImagesLoader loader;
		private BreakoutGame game;
		private JLabel missingPlayer;
	
	public WaitingForPlayerPanel(BreakoutGame game) {
		
		this.game = game;
		
		// inizializzaizone caricatore di immagini
		this.loader = new ImagesLoader();
		
	
		
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/sfondoMultiplayer.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(new ImageIcon(background).getImage().getScaledInstance(Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT,  java.awt.Image.SCALE_SMOOTH));
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		/*
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
		button.addActionListener(a1);
		button.setVisible(true);
		
		*/
		
		
		
		
		//label1.setBounds(0, 0, 200, 50);
		
		//creazione label per testo
		this.missingPlayer = new JLabel();
		missingPlayer.setFont(new Font("Courier", Font.BOLD, 35)); 
		missingPlayer.setLocation(100, 400);
		missingPlayer.setOpaque(false);
		missingPlayer.setText("Giocatori mancanti: " + game.getNumberOfMissingPlayer());
		backgroundlabel.setLayout(new FlowLayout());
		backgroundlabel.add(missingPlayer);
		missingPlayer.setVisible(true);

		
	}
	
	
	public void updateMissingPlayerText() {
		
		missingPlayer.setText("Giocatori mancanti: " + game.getNumberOfMissingPlayer());
		repaint();
	}
	

}

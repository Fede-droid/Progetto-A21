package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Utility.Utilities;

public class WaitingForPlayerPanel extends JPanel{
	
	 
		private static final long serialVersionUID = 1L;
		BufferedImage background, button1, button2, button3, button4, wait;
		ImagesLoader loader;
		private BreakoutGame game;
		private JLabel missingPlayer;
		private int nMissingPlayer;
	
	public WaitingForPlayerPanel(BreakoutGame game) {
		
		this.game = game;
		
		// inizializzaizone caricatore di immagini
		this.loader = ImagesLoader.getInstace();
		
	
		// caricamento sfondo
		this.background = loader.uploadImage("menu/menuImages/sfondoMultiplayer.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(new ImageIcon(background).getImage().getScaledInstance(Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT,  java.awt.Image.SCALE_SMOOTH));
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);

		

		//label1.setBounds(0, 0, 200, 50);
		
		//creazione label per testo
		this.missingPlayer = new JLabel("Giocatori rimanenti: " + game.getNumberOfMissingPlayer());
		missingPlayer.setFont(new Font("Courier", Font.BOLD, 35)); 
		missingPlayer.setLocation(100, 1000);
		missingPlayer.setOpaque(false);
		backgroundlabel.setLayout(new FlowLayout());
	
		
		backgroundlabel.add(missingPlayer);
		
		Image image2 = new ImageIcon(this.getClass().getResource("waiting3.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon imageI2 = new ImageIcon(image2);
		JLabel waitLabel = new JLabel(imageI2);
		
		
		backgroundlabel.add(waitLabel);
		
		backgroundlabel.setLayout(new FlowLayout());


		
	}
	
	public void updateMissingPlayerText() {
		this.nMissingPlayer = game.getNumberOfMissingPlayer();
		missingPlayer.setText("Giocatori rimanenti: " + nMissingPlayer);
		
		repaint();
	}
	
	// solo per il test
	public int getnMissingPlayer() {
		
		return nMissingPlayer;
	}
	

}

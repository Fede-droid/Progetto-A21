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
		missingPlayer.setFont(new Font("Courier", Font.BOLD, 30)); 
		missingPlayer.setLocation(100, 1000);
		missingPlayer.setOpaque(false);
		backgroundlabel.setLayout(new FlowLayout());
		
		backgroundlabel.add(missingPlayer);

		
	}
	
	public void updateMissingPlayerText() {
		
		missingPlayer.setText("Giocatori rimanenti: " + game.getNumberOfMissingPlayer());
		
		repaint();
	}
	
	

}

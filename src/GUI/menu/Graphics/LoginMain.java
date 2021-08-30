package GUI.menu.Graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Utility.Utilities;

public class LoginMain extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginMain(BreakoutGame game) {
		
		
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);
		
		
		// caricamento sfondo
		BufferedImage background = ImagesLoader.getInstace().uploadImage("menu/menuImages/background.jpg");
		JLabel backgroundlabel;
		ImageIcon image = new ImageIcon(background);
		backgroundlabel = new JLabel("", image, JLabel.CENTER);
		backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(backgroundlabel);
		
		
		JTextField playerNamel = new JTextField("nome player");
		
		
		BufferedImage submit = ImagesLoader.getInstace().uploadImage("menu/menuImages/submit.png");
		ImageIcon submitImg = new ImageIcon(submit);
		JButton submitButton = new JButton();
		submitButton.setOpaque(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setBorderPainted(false);
		submitButton.setIcon(submitImg);
		
		
		// scelta fra host e joiner
		ActionListener setVisibile = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(playerNamel.getText().isEmpty()) return;
		    	
		    	if(controlChar(playerNamel.getText()) && controlText(playerNamel.getText())) {
			    	
		    		game.setPlayerName(playerNamel.getText());
			    	game.openMain();
			    	setVisible(false);
			    
		    	}
		    	
		    	
		    }
		};
		
		submitButton.addActionListener(setVisibile);
		
		
	
		
		Font f = new Font("Helvetica", Font.BOLD, 25);
		
		// posizione "nome giocatore"
		JLabel numP = new JLabel("Inserire nome giocatore");
		numP.setSize(500, 100);
		numP.setFont(f);
		numP.setLocation(Utilities.SCREEN_WIDTH/2 - 140, 130);
		
		// posizione bottoni 
		backgroundlabel.setLayout(null);
		
		
		// nome player
		playerNamel.setSize(300,50);
		playerNamel.setLocation(Utilities.SCREEN_WIDTH/2 - 150, 270);
		playerNamel.setFont(f);
		
		// submit
		submitButton.setSize(400, 200);
		submitButton.setLocation(Utilities.SCREEN_WIDTH/2 - 200, 360);
		
		
		
		backgroundlabel.add(submitButton);
		backgroundlabel.add(playerNamel);
		backgroundlabel.add(numP);
		
		
	}
	
	
	public void showErrorCaracter() {
		
		JOptionPane.showMessageDialog(this,
			    "Numero massimo di caratteri per il nome e' 6",
			    "ATTENZIONE",
			    JOptionPane.ERROR_MESSAGE);
	
	}
	
	public void showErrorSpace() {
	
		JOptionPane.showMessageDialog(this,
			    "Nome utente e/o game code non devono contenere spazi!",
			    "ATTENZIONE",
			    JOptionPane.ERROR_MESSAGE);
	
	}
	
	public boolean controlChar(String text) {
		
		if(text.length() > 6 ) {
			showErrorCaracter();
			return false;
		}
		
		return true;
	}
	
	public boolean controlText(String text) {
		
		if(text.contains(" ") ) {
			showErrorSpace();
			return false;
		}
		
		return true;
		
		}
}

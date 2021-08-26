package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class MultiplayerPanel extends JPanel{ 
	
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, submit;
	ImagesLoader loader;
	private Boolean buttonHostVisible = true;
	private BreakoutGame c;
	private JButton submitButton;
	
	public MultiplayerPanel(BreakoutGame c) {
		
		this.c = c;
		this.loader = ImagesLoader.getInstace();
		
			// caricamento sfondo
			this.background = loader.uploadImage("menu/menuImages/background.jpg");
			JLabel backgroundlabel;
			ImageIcon image = new ImageIcon(background);
			backgroundlabel = new JLabel("", image, JLabel.CENTER);
			backgroundlabel.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
			add(backgroundlabel);
			
			
			// bottone 1 HOST
			this.button1 = loader.uploadImage("menu/menuImages/buttonHost.png");
			ImageIcon button1Img = new ImageIcon(button1);
			backgroundlabel.setLayout(new FlowLayout() );
			JButton button = new JButton();
			inizializeButton(button,button1Img);
			backgroundlabel.add(button);
			button.setVisible(buttonHostVisible);
			
			
			// bottone 2 JOINER
			this.button2 = loader.uploadImage("menu/menuImages/buttonJOINER.png");
			ImageIcon button2Img = new ImageIcon(button2);
			backgroundlabel.setLayout(new FlowLayout() );
			JButton button2 = new JButton();
			inizializeButton(button2,button2Img);
			backgroundlabel.add(button2);
			button2.setVisible(!buttonHostVisible);
			
			
			// scelta fra host e joiner
			ActionListener setVisibile = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	changeHostVisible(false);
			    	button.setVisible(buttonHostVisible);
			    	button2.setVisible(!buttonHostVisible);
			    	
			    	repaint();
			    }
			};
			
			button.addActionListener(setVisibile);
			
			ActionListener setVisibile2 = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	changeHostVisible(true);
			    	button.setVisible(buttonHostVisible);
			    	button2.setVisible(!buttonHostVisible);
			    	repaint();
			    }
			};
			
			button2.addActionListener(setVisibile2);
		
			
	        // label nome giocatore

			JTextField playerNamel = new JTextField("NomePlayer");
	        // label gameCode
	        JTextField gameCodel = new JTextField("GameCode");
	     
	        
	      String[] nPlayers = {"2", "3", "4"};

	      //ComboBox per scelta numero player
	      
	      JComboBox<Object> nPl = new JComboBox<Object>(nPlayers);

	      
	    
			this.submit = loader.uploadImage("menu/menuImages/submit.png");
			ImageIcon submitImg = new ImageIcon(submit);
			submitButton = new JButton();
			inizializeButton(submitButton, submitImg);
			submitButton.setIcon(submitImg);
			
			ActionListener submitAction = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	
			    	String playerName = playerNamel.getText();
			    	String gameCode = gameCodel.getText();
			    	
			    	if(controlText(playerName) && controlText(gameCode) && controlChar(playerName)) {
			    	
			    		int nPlay = nPl.getSelectedIndex() + 2;
			    	
			    		c.setPlayerData(buttonHostVisible, playerName, gameCode, nPlay);
				    
			    	}

			    }
			};
			
			submitButton.addActionListener(submitAction);
			
			
			
			// posizione elementi su schermo
			
			backgroundlabel.setLayout(null);

			// posizione bottone Host/Joiner
			button.setSize(400, 100);
			button2.setSize(400, 100);
			button.setLocation(Utilities.SCREEN_WIDTH/2 - 200, 30);
			button2.setLocation(Utilities.SCREEN_WIDTH/2 - 200, 30);

			
			// posizione "numero giocatori"
			JLabel numP = new JLabel("Seleziona il numero di giocatori");
			numP.setSize(500, 100);
			Font f = new Font("Helvetica", Font.BOLD, 25);
			numP.setFont(f);
			numP.setLocation(SwingConstants.CENTER + 100, 130);
			
			// numero giocatori
			nPl.setSize(200, 60);
			
			nPl.setLocation(Utilities.SCREEN_WIDTH/2 - 100, 200);
			Font f1 = new Font("Helvetica", Font.BOLD, 18);
			nPl.setFont(f1);
			
			
			// nome player
			playerNamel.setSize(160,30);
			playerNamel.setLocation(Utilities.SCREEN_WIDTH/2 - 80, 270);
			playerNamel.setFont(f);
			
			// game code
			gameCodel.setSize(160,30);
			gameCodel.setLocation(Utilities.SCREEN_WIDTH/2 - 80, 330);
			gameCodel.setFont(f);
			
			// submit
			submitButton.setSize(400, 200);
			submitButton.setLocation(Utilities.SCREEN_WIDTH/2 - 200, 360);
			
			
			backgroundlabel.add(button);
			backgroundlabel.add(button2);
			backgroundlabel.add(nPl);
			backgroundlabel.add(numP);
			backgroundlabel.add(playerNamel);
			backgroundlabel.add(gameCodel);
			backgroundlabel.add(submitButton);

			
	    			
	}
	
	
	public void changeHostVisible(boolean visible) {
		
		this.buttonHostVisible = visible;
		
	}
	
	public void showError() {
		
		JOptionPane.showMessageDialog(this,
			    "Partita non trovata e/o codice non valido!",
			    "ATTENZIONE",
			    JOptionPane.ERROR_MESSAGE);
		
		submitButton.removeAll();
		repaint();
		
	
	}
	
	public void showErrorSpace() {
		
		JOptionPane.showMessageDialog(this,
			    "Nome utente e/o game code non devono contenere spazi!",
			    "ATTENZIONE",
			    JOptionPane.ERROR_MESSAGE);
	
	}
	
	public void showErrorCaracter() {
		
		JOptionPane.showMessageDialog(this,
			    "Numero massimo di caratteri per il nome è 5",
			    "ATTENZIONE",
			    JOptionPane.ERROR_MESSAGE);
	
	}
	
	
	
	// setta caratteristiche bottone 
	
		public void inizializeButton(JButton butt, ImageIcon imag) {
			
			butt.setOpaque(false);
			butt.setContentAreaFilled(false);
			butt.setBorderPainted(false);
			butt.setIcon(imag);
		}

	
	
	public boolean controlText(String text) {
		
		if(text.contains(" ") ) {
			showErrorSpace();
			return false;
		}
		
		return true;
		
	}
	
	
	public boolean controlChar(String text) {
		
		if(text.length() > 5 ) {
			showErrorCaracter();
			return false;
		}
		
		return true;
		
	}
		
	

}

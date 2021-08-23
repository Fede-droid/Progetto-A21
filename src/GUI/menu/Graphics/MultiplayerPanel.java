package GUI.menu.Graphics;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import GUI.ImagesLoader;
import GUI.menu.listeners.MultiplayerListener;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class MultiplayerPanel extends JPanel{ 
	
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, submit;
	ImagesLoader loader;
	private Boolean buttonHostVisible = true;
	private int playerNumber;
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
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setIcon(button1Img);
			backgroundlabel.add(button);
			button.setVisible(buttonHostVisible);
			
			
			// bottone 2 JOINER
			this.button2 = loader.uploadImage("menu/menuImages/buttonJOINER.png");
			ImageIcon button2Img = new ImageIcon(button2);
			backgroundlabel.setLayout(new FlowLayout() );
			JButton button2 = new JButton();
			button2.setOpaque(false);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			button2.setIcon(button2Img);
			backgroundlabel.add(button2);
			button2.setVisible(!buttonHostVisible);
			
			
			
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
			//backgroundlabel.setLayout(new FlowLayout() );
	        JTextField playerNamel = new JTextField("nome player");
	       
	       
	        // label gameCode
	        JTextField gameCodel = new JTextField("game code");
	     
	        
	      String[] nPlayers = { "1", "2", "3", "4"};

	      //Create the combo box, select item at index 4.
	      JComboBox<Object> nPl = new JComboBox<Object>(nPlayers);
	      nPl.setSelectedIndex(3);
	     
	        
	    
			this.submit = loader.uploadImage("menu/menuImages/submit.png");
			ImageIcon submitImg = new ImageIcon(submit);
			submitButton = new JButton();
			submitButton.setOpaque(false);
			submitButton.setContentAreaFilled(false);
			submitButton.setBorderPainted(false);
			submitButton.setIcon(submitImg);
			
			ActionListener submitAction = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	String playerName = playerNamel.getText();
			    	String gameCode = gameCodel.getText();
			    
				    c.setPlayerData(buttonHostVisible, playerName, gameCode, playerNumber);
				    
			    
				    playerNumber = Integer. parseInt(nPl.getSelectedItem().toString());
			    	
			    	
			    }
			};
			
			submitButton.addActionListener(submitAction);
			
			
			
			// posizione bottoni 
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
	




	
		
	
		
		
	

}

package GUI.menu.Graphics;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.ImagesLoader;
import GUI.menu.listeners.MultiplayerListener;
import GUI.menu.listeners.SinglePlayerListener;
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
		this.loader = new ImagesLoader();
		
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
			backgroundlabel.setLayout(new FlowLayout() );
	        JTextField playerNamel = new JTextField("nome player");
	        backgroundlabel.add(playerNamel);
	       
	       
	        // label gameCode
	        backgroundlabel.setLayout(new FlowLayout());
	        JTextField gameCodel = new JTextField("game code");
	        backgroundlabel.add(gameCodel);
	     
	        
	        
	        // numero di giocatori
	        JCheckBox checkbox1 = new JCheckBox("1");
	        JCheckBox checkbox2 = new JCheckBox("2");
	        JCheckBox checkbox3 = new JCheckBox("3");
	        JCheckBox checkbox4 = new JCheckBox("4");
	        
	       
	        backgroundlabel.add(checkbox1);
	        backgroundlabel.add(checkbox2);
	        backgroundlabel.add(checkbox3);
	        backgroundlabel.add(checkbox4);
	        
	      
	        
	        
			this.submit = loader.uploadImage("menu/menuImages/submit.png");
			ImageIcon submitImg = new ImageIcon(submit);
			backgroundlabel.setLayout(new FlowLayout() );
			submitButton = new JButton();
			submitButton.setOpaque(false);
			submitButton.setContentAreaFilled(false);
			submitButton.setBorderPainted(false);
			submitButton.setIcon(submitImg);
			backgroundlabel.add(submitButton);
			
			ActionListener submitAction = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	String playerName = playerNamel.getText();
			    	String gameCode = gameCodel.getText();
			    	if(checkbox1.isSelected()) playerNumber = 1;
				    if(checkbox2.isSelected()) playerNumber = 2;
				    if(checkbox3.isSelected()) playerNumber = 3;
				    if(checkbox4.isSelected()) playerNumber = 4;
				    c.setPlayerData(buttonHostVisible, playerName, gameCode, playerNumber);
				    
			    
			    	
			    	
			    	
			    }
			};
			
			submitButton.addActionListener(submitAction);
			
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

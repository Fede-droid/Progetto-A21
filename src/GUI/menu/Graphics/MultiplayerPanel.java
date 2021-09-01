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
import Utility.Utilities;

public class MultiplayerPanel extends JPanel{ 
	
	private static final long serialVersionUID = 1L;
	BufferedImage background, button1, button2, submit, random, back;
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
			
			// caricamento sfondo
			this.back = loader.uploadImage("menu/menuImages/back.png");
			ImageIcon imageBack = new ImageIcon(back);
			JButton buttonBack = new JButton();
			inizializeButton(buttonBack,imageBack);


			// ritorno al main
			ActionListener returnBack = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	
			    	setVisible(false);
			    	c.openMain();
			    	repaint();
			    }
			};
			
			buttonBack.addActionListener(returnBack);
			
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
			    	
			    	
			    	String gameCode = gameCodel.getText();
			    	
			    	if(controlText(gameCode)) {
			    	
			    		int nPlay = nPl.getSelectedIndex() + 2;
			    	
			    		c.setPlayerData(buttonHostVisible, gameCode, nPlay);
				    
			    	}

			    }
			};
			
			submitButton.addActionListener(submitAction);
			
			// bottone PARTITA RANDOM
			this.random = loader.uploadImage("menu/menuImages/random.png");
			ImageIcon buttonRImg = new ImageIcon(random);
			backgroundlabel.setLayout(new FlowLayout() );
			JButton random = new JButton();
			inizializeButton(random,buttonRImg);
			backgroundlabel.add(random);

			ActionListener randomClick = new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	gameCodel.setText("RANDOM");
			    	repaint();
			    }
			};
			
			random.addActionListener(randomClick);
			
			// posizione elementi su schermo
			
			backgroundlabel.setLayout(null);

			
			
			//posizione backButton
			buttonBack.setSize(imageBack.getIconWidth(), imageBack.getIconHeight());
			buttonBack.setLocation(10, 10);
			
			
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
			
			
			
			// game code
			gameCodel.setSize(160,30);
			gameCodel.setLocation(Utilities.SCREEN_WIDTH/2 - 80, 330);
			gameCodel.setFont(f);
			
		
			// submit button
			submitButton.setSize(250, 75);
			submitButton.setLocation(Utilities.SCREEN_WIDTH/2 - 125, 400);
			
			// random button
			random.setSize(200, 50);
			random.setLocation(Utilities.SCREEN_WIDTH/2 - 100, 270);
			
		
			
			
			backgroundlabel.add(button);
			backgroundlabel.add(buttonBack);

			backgroundlabel.add(button2);
			backgroundlabel.add(nPl);
			backgroundlabel.add(numP);
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
	
	
	
	/**
	 *  setta caratteristiche bottone 
	 * @param butt
	 * @param imag
	 */
	
		public void inizializeButton(JButton butt, ImageIcon imag) {
			
			butt.setOpaque(false);
			butt.setContentAreaFilled(false);
			butt.setBorderPainted(false);
			butt.setIcon(imag);
		}

	
	/**
	 * 
	 * @param text
	 * @return false se non rispetta le regole
	 */
		public boolean controlText(String text) {
		
		if(text.contains(" ") ) {
			showErrorSpace();
			return false;
		}
		
		return true;
		
		}
	
	
	
		
	

}

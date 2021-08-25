package GUI.menu.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Items.Utilities;

public class IntroPanel extends JPanel {

	/**
	 * 	INTRO AL GIOCO
	 */
	private static final long serialVersionUID = 1L;
	private Clip hit;
	
	// Pannello di intro al gioco
	public IntroPanel(BreakoutGame c) throws InterruptedException {
		
 
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);

		Icon imgIcon = new ImageIcon(this.getClass().getResource("intro-2.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setSize(Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		
		label.setBounds(0, 0, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		add(label);
		setBackground(Color.BLACK);

		String musicString = "./src/GUI/menu/menuImages/intro.wav";
		try {
		    AudioInputStream audio = AudioSystem.getAudioInputStream(new File(musicString).getAbsoluteFile());
	        this.hit = AudioSystem.getClip();
	        hit.open(audio);
	        hit.start();
	        } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	
        setVisible(true);
        
  
        // bottone "skip" per saltare la intro ed accedere direttamente al menu iniziale
     		label.setLayout(new FlowLayout() );
     		JButton skip = new JButton("SKIP");     
     		skip.setLocation(Utilities.SCREEN_WIDTH - skip.getWidth(), 100);
     		label.add(skip);

     		ActionListener setVisibileSwitch1 = new ActionListener() {
     		    @Override
     		    public void actionPerformed(ActionEvent e) {
     		    
     		    	c.skipIntro();
     		    	hit.stop();
     		    	setVisible(false);
     		    	repaint();
     		    }
     		};
     		
     		skip.addActionListener(setVisibileSwitch1);
        
		
	}
	
	
}

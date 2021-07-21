package GUI.menu.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ImagesLoader;
import Model.Items.Utilities;

public class IntroPanel extends JPanel {

	
	public IntroPanel() throws InterruptedException {
		
		ImagesLoader loader = new ImagesLoader();
 
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
	        Clip hit = AudioSystem.getClip();
	        hit.open(audio);
	        hit.start();
	        } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	
        setVisible(true);
		
	}
	
	
}

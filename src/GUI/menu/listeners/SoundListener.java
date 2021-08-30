package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import GUI.menu.Graphics.MainMenu;
import Model.BreakoutGame;

public class SoundListener extends Listener implements ActionListener{

	private MainMenu main;
	private Boolean musicBool;
	
	/**
	 * 
	 * @param game
	 * @param m
	 * @param musicBool
	 */
	public SoundListener(BreakoutGame game, JPanel m, boolean musicBool) {
		super(game, m);
		
		this.main = (MainMenu)m;
		this.musicBool = musicBool;
	}

	/**
	 * rimozione pannello
	 */
	@Override
	public void removeOldPanel() {
		m.removeAll();
		m.setVisible(false);
	}

	/**
	 * cambia immagine bottone musica e setto in controller se musica è on o off
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(musicBool == true) {
			game.setSound(true);
			main.setMusicButton(false);
			main.repaint();
		}
		
		else {
			
			game.setSound(false);
			main.setMusicButton(true);
			main.repaint();
		}
	
	}

}

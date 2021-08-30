package GUI.menu.listeners;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.menu.Graphics.GameFrame;
import Model.BreakoutGame;

public abstract class Listener implements ActionListener {

	protected BreakoutGame game;
	protected JPanel m;
	
	/**
	 * 
	 * @param game
	 * @param m pannello
	 */
	public Listener(BreakoutGame game, JPanel m) {
		
		this.game = game;
		this.m = m;
		
	}
	
	/**
	 * rimuovi pannello
	 */
	public  void removeOldPanel() {
		
		m.removeAll();
		m.setVisible(false);
	}
	
}

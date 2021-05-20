package GUI.menu.listeners;

import javax.swing.JPanel;

import GUI.GameFrame;
import Model.BreakoutGame;

public abstract class Listener {

	protected BreakoutGame game;
	protected JPanel m;
	
	public Listener(BreakoutGame game, JPanel m) {
		
		this.game = game;
		this.m = m;
		
	}
	
	public abstract void removeOldPanel();
	
}

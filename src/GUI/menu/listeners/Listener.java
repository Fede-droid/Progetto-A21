package GUI.menu.listeners;

import javax.swing.JPanel;

import GUI.GameFrame;

public abstract class Listener {

	protected GameFrame f;
	protected JPanel m;
	
	public Listener(GameFrame f, JPanel m) {
		
		this.f = f;
		this.m = m;
		
	}
	
	public abstract void removeOldPanel();
	
}

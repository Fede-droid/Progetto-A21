package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import Model.BreakoutGame;


public class SinglePlayerListener implements ActionListener{

	private BreakoutGame game;
	private JPanel m;
	
	public SinglePlayerListener(BreakoutGame game, JPanel m) {
		
		this.game = game;
		this.m = m;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		removeOldPanel();
		game.gameSetup();
	}

	public void removeOldPanel() {
		
		m.removeAll();
		m.setVisible(false);
		
	}


	
	
}

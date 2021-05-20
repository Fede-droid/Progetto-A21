package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.GameFrame;
import Model.BreakoutGame;

public class RepeatListener implements ActionListener {

	private BreakoutGame game;
	private JPanel m;
	
	public RepeatListener(BreakoutGame game, JPanel m) {
		
		this.game = game;
		this.m = m;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	public void removeOldPanel() {
		m.removeAll();
		m.setVisible(false);
	}

}

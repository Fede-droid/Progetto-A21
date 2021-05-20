package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
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

		removeOldPanel();
		game.playAgain();
		
	}

	public void removeOldPanel() {
		m.removeAll();
		m.setVisible(false);
	}

}

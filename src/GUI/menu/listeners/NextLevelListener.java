package GUI.menu.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import Model.BreakoutGame;

public class NextLevelListener extends Listener {

	public NextLevelListener(BreakoutGame game, JPanel m) {
		super(game, m);
		
	}

	
	/**
	 * prossimo livello chiamato in controller
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		removeOldPanel();
		game.nextLevel();
		
	}

}

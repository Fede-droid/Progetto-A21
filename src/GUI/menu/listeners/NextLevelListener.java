package GUI.menu.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import Model.BreakoutGame;
import Model.Core.Levels.TypeLevels;

public class NextLevelListener extends Listener {

	public NextLevelListener(BreakoutGame game, JPanel m) {
		super(game, m);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		removeOldPanel();
		game.setLevel(TypeLevels.LEVEL2);
		game.nextLevel();
		
	}

}

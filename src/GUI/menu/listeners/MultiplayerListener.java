package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Model.BreakoutGame;

public class MultiplayerListener extends Listener implements ActionListener {


	public MultiplayerListener(BreakoutGame game, JPanel m) {
		super(game, m);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		m.removeAll();
		m.setVisible(false);
		m.setEnabled(false);
		m.invalidate();
		game.inizializeMultiplayer();
		
		
	}

		

}

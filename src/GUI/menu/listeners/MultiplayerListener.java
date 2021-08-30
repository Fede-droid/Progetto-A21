package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Model.BreakoutGame;

public class MultiplayerListener extends Listener implements ActionListener {

	/**
	 * 
	 * @param game
	 * @param m pannello 
	 */
	public MultiplayerListener(BreakoutGame game, JPanel m) {
		super(game, m);
		
	}

	/**
	 * inzializzazione multiplayer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		m.setVisible(false);
		game.inizializeMultiplayer();
		
		
	}

		

}

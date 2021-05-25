package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Model.BreakoutGame;

public class Return2Main extends Listener implements ActionListener{

	private boolean win;
	
	public Return2Main(BreakoutGame game, JPanel m, boolean win) {
		super(game, m);
		this.win = win;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		m.removeAll();
		m.setVisible(false);
		m.invalidate();
		
		if(!win) game.showMain();
		else {
			game.reset(); 
			new BreakoutGame();
			}
		
		
		
	}


}

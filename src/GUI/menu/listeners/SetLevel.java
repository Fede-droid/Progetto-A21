package GUI.menu.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import GUI.menu.Graphics.MainMenu;
import Model.BreakoutGame;
import Model.Core.TypeLevels;

public class SetLevel extends Listener {

	TypeLevels lv;
	MainMenu main;
	
	public SetLevel(BreakoutGame game, JPanel m, TypeLevels lv) {
		super(game, m);
		this.lv = lv;
		this.main = (MainMenu)m;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		game.setLevel(lv);
		main.setLevelButton(lv);
		
		main.repaint();
	
	}


}

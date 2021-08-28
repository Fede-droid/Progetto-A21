package GUI.menu.Graphics;

import Model.BreakoutGame;

public class TesterG {

	public static void main(String[] args) {
		
		BreakoutGame g = new BreakoutGame();
		
		GameFrame f = new GameFrame();
		
		WaitingForPlayerPanel p = new WaitingForPlayerPanel(g);
	
		
		f.add(p);
		
		f.pack();
		f.setVisible(true);
		f.repaint();
	}

}

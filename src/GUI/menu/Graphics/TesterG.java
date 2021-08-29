package GUI.menu.Graphics;

import Model.BreakoutGame;

public class TesterG {

	public static void main(String[] args) {
		
		GameFrame f = new GameFrame();
		f.setVisible(true);
		
		ScoreListPanel s = new ScoreListPanel();
		f.add(s);
		
		f.pack();
		f.setVisible(true);
		f.repaint();
	}

}

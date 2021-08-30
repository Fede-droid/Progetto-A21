package Model;

import GUI.menu.Graphics.MainMenu;

public class Tester {

public static void main(String[] args) {
	
		BreakoutGame game = new BreakoutGame();
		try {
			game.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
}

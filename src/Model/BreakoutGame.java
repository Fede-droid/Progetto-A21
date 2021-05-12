package Model;

import javax.swing.JFrame;

//class 

public class BreakoutGame {
	
	public static void main(String[] args) {
		Screen screen1 = new Screen();
		//screen1.playGame();
		
		//Window settings.
		JFrame frame = new JFrame();
		frame.setTitle("Breakout");
		//frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
				
		frame.setVisible(true);
	}

}

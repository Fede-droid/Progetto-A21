package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GameFrame;
import GUI.menu.MainMenu;
import Model.Player;
import Model.Screen;

public class SinglePlayerListener implements ActionListener{

	private GameFrame f;
	private MainMenu m;
	
	public SinglePlayerListener(GameFrame f, MainMenu menu) {
		
		this.f = f;
		this.m = menu;
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		m.setVisible(false);
		
		
		// creo un giocatore
		Player p = new Player();
		
		
		// creazione gioco 
		Screen screen1 = new Screen();
		screen1.newPlayer(p);
		
		f.add(screen1);
		f.requestFocusInWindow();

		
			
		// aggiungo controllo da tastiera
		f.addKeyListener(p.getInputHandler());
		f.pack();
		f.setVisible(true);
		

		// avvio ciclo di gioco
		Thread gameThread = new Thread(screen1);
		gameThread.start();
		
		
	}

	
	
}

package Testers;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUI.menu.Graphics.GameFrame;
import GUI.menu.Graphics.WaitingForPlayerPanel;
import Model.BreakoutGame;


// CLASSE DI TEST CHE SIMULA UNA LOBBY NEL MULTIPLAYER

// permette di controllare se il numero di giocatori mancanti viene aggiornato in maniera corretta passando dal controllore di gioco.

class BreakoutGameTest {
	
	public  BreakoutGame g ;
	WaitingForPlayerPanel p;
	public int i;
	
	@BeforeEach
	public  void setUpClass() {
		
		this.g = new BreakoutGame();
		
		GameFrame f = new GameFrame();
		
		this.p = new WaitingForPlayerPanel(g);
	
		this.i = 3;
		
		f.add(p);
		
		f.pack();
		f.setVisible(true);
		f.repaint();
	}

	
	@Test
	synchronized void test() {
	
		
		for(int i = 3; i > 0; i--) {
			g.setNumberOfMissingPlayer(i);
			p.updateMissingPlayerText();
			
			try {
				wait(2000);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		
		}
		
		assertTrue("SUCCESS", (p.getnMissingPlayer() == 1));
		
		
	}
	
	

	
}

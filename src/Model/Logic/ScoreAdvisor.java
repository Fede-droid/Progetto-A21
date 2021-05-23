package Model.Logic;

import java.awt.List;
import java.util.ArrayList;

import Model.BreakoutGame;
import Model.Core.Screen;

public class ScoreAdvisor {

	//private ArrayList<Player> players;
	
	public ScoreAdvisor(BreakoutGame g) {
		
		//this.players = (ArrayList) g.getPlayers();
	}
	
	
	public void addPoint(Player p) {
		
		p.addPoint2Player();
		
	}
	
	public void getScore(Player p) {
		
		p.getPlayerScore();
	}
	
	
}

package Database;


import java.util.ArrayList;

import Model.Core.Screen;

public class PersistenceFacade {
	
	private Screen screen;
	private SqlDAO db;
	
	
	public PersistenceFacade(Screen s) {
		
		this.screen = s;
		this.db = new SqlDAO();
	}
	
	
	
	public PersistenceFacade() {
		this.db = new SqlDAO();
		
	}

	public ArrayList<String> getAllPlayer() {
		db.allScore();
		return db.getAllPlayer();
	}

	
	public ArrayList<Integer> getAllScore() {
		db.allScore();
		return db.getAllScores();
		
	}

	
	public int getScoreByLVandUser() {
		  
	   
		return  db.getScoreByUserAndLV(screen.getName(), screen.getCurrentLevel());
	       
	}

	
	public void updateScore() {
		
		db.updateScore(screen.playerName(), screen.getCurrentLevel(), screen.getCurrentLevel());
		
	}
	
	
	
	

}

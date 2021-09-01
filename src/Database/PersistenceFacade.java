package Database;


import java.util.ArrayList;

import Model.Core.Screen;

public class PersistenceFacade {
	// Facade che permette di fare da tramite tra la logica del dominio e i DAO
	
	private SqlDAO db;
	
	
	public PersistenceFacade() {
		
		this.db = new SqlDAO();
	}
	
	

	
	/**
	 * 
	 * @return tutti i giocatori nel database in ordine decrescente al loro punteggio 
	 */
	public ArrayList<String> getAllPlayer() {
		db.allScore();
		return db.getAllPlayer();
	}

	/**
	 * @return tutti i punteggi nel database in ordine decrescente
	 */
	public ArrayList<Integer> getAllScore() {
		db.allScore();
		return db.getAllScores();
		
	}

	/**
	 * @return il punteggio migliore memorizzato del db del giocatore che sta giocando 
	 */
	public int getScoreByLVandUser(Screen screen) {
	   
		return  db.getScoreByUserAndLV(screen.getName(), screen.getCurrentLevel());
	       
	}

	/*
	 * aggiorna punteggi db 
	 */
	public void updateScore(Screen screen) {
		
		db.updateScore(screen.playerName(), screen.getCurrentLevel(), screen.getLastScore());
		
	}
	
	
	
	

}

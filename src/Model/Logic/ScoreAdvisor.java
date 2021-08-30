package Model.Logic;

public class ScoreAdvisor {
	
	/*
	 * 
	 * classe che permette il calcolo del punteggio a fine partita
	 * ovvero che il punteggio è inversamente proporzionale alla durata della partita. 
	 * se un giocatore impiega troppo tempo per perdere/vincere avrò un punteggio più
	 * basso
	 */
	
	private long time;
	
	public void start() {
		
		time = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @param lastScore
	 * @return newScore
	 */
	public int getScoreEnd(int score) {
		
		long diff = (System.currentTimeMillis() - time)/300000;
		
		System.out.println((int)diff);
		
		
		int newScore = (score-(int)diff);
		
		if(newScore < 0) newScore = 0;
		
		return newScore;
		
	}
	

}

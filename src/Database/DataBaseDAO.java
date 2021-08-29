package Database;


public interface DataBaseDAO {
	// interfaccia per i DAO 
	
	void allScore();
	
	void updateScore(String name, int lv, int score);

	int getScoreByUserAndLV(String user, int lv);

}

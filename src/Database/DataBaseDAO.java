package Database;

public interface DataBaseDAO {
	
	int getScoreByLV(int lv);
	
	void allScore();
	
	void updateScore(String name, int lv, int score);

}

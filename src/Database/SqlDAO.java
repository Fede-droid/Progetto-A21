package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlDAO implements DataBaseDAO{

	
	private ArrayList<Integer> allScore;
	private ArrayList<String> allPlayer;

	
	@Override
	public void allScore() {
		allScore = new ArrayList<Integer>();
		allPlayer = new ArrayList<String>();
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            
            String query = "SELECT NAME_PLAYER, SUM(SCORE) AS 'SCORE'" +
            		" FROM SINGLEPLAYERDB" +
            		" GROUP BY NAME_PLAYER" +
            		" ORDER BY SCORE DESC" ;
            ResultSet rs = stmt.executeQuery(query);
            
         
            while (rs.next()) {
            	allPlayer.add(rs.getString(1));
            	allScore.add(Integer.parseInt(rs.getString(2)));
            }
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
	}

	
	@Override
	public void updateScore(String name, int lv, int score) {
		
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String update = "REPLACE INTO SINGLEPLAYERDB\n"
            		+ "    (NAME_PLAYER, LV, SCORE)\n"
            		+ "VALUES\n"
            		+     "('" + name + "','" + lv + "','" + score + "')";
            
            stmt.executeQuery(update);
            
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		
	}

	public ArrayList<String> getAllPlayer() {
		return allPlayer;
	}
	
	
	public ArrayList<Integer> getAllScores() {
		return allScore;
	}


	@Override
	public int getScoreByUserAndLV(String user, int lv) {
		Connection connection = ConnectionFactory.getConnection();
		
		try {
            Statement stmt = connection.createStatement();
            String update = "SELECT SCORE "+
            		"FROM SINGLEPLAYERDB "+
            		"WHERE NAME_PLAYER = '" + user + "' AND LV = '" + lv + "'";
            
            stmt.executeQuery(update);
            
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return 0;
	}
	
	
	
	

}

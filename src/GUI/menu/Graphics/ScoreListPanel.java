package GUI.menu.Graphics;

import java.util.ArrayList;

import Database.PersistenceFacade;

public class ScoreListPanel {

	private ArrayList<Integer> allScore;
	private ArrayList<String> allPlayer;
	
	PersistenceFacade facade;
	
	public ScoreListPanel() {
		
		this.facade = new PersistenceFacade();
		
		
	
		
	}
	
	
	public void updateData() {
		allScore = new ArrayList<Integer>();
		allPlayer = new ArrayList<String>();
		
		allPlayer = facade.getAllPlayer();
		allScore = facade.getAllScore();
		
		for(int i = 0; i < allPlayer.size(); i++){
			
			System.out.println(allPlayer.get(i) + "  " + allScore.get(i));
			
		}
		
		
		
	}
	
	
	

}

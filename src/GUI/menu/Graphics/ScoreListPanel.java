package GUI.menu.Graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.PersistenceFacade;
import Utility.Utilities;


public class ScoreListPanel extends JPanel {

	private ArrayList<Integer> allScore;
	private ArrayList<String> allPlayer;
	
	private JList<String> countryList;
	DefaultListModel<String> listModel;
	
	PersistenceFacade facade;
	
	public ScoreListPanel() {
		
		
		this.facade = new PersistenceFacade();
		
		listModel = new DefaultListModel<>();
		
		updateData();
		
		countryList = new JList<>(listModel);
		
		
		this.setLayout(null);
		//create the list
		 JScrollPane p = new JScrollPane(countryList);
		 p.setSize(Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
		 countryList.setFont(new Font("Helvetica", Font.BOLD, 30));
		add(p); // se si vuole aggiungere uno ScrollPane this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); this.setTitle("JList Example");
		
		this.setVisible(true);
	}
	
	
	public void updateData() {
		allScore = new ArrayList<Integer>();
		allPlayer = new ArrayList<String>();
		
		allPlayer = facade.getAllPlayer();
		allScore = facade.getAllScore();
		
		listModel.addElement("POSITION" + "       " + "PLAYER" + "       " + "SCORE");
		listModel.addElement("________________________________");
		
		for(int i = 0; i < allPlayer.size(); i++){
			
			System.out.println(allPlayer.get(i) + "  " + allScore.get(i));
			
			listModel.addElement((i + 1) + "'" + "                      " + allPlayer.get(i) + "                " +  allScore.get(i));

		}

	}


}

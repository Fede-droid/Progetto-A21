package GUI.menu.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.PersistenceFacade;
import Utility.Utilities;

// Frame che mostra la classifica globale 
public class ScoreListPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> allScore;
	private ArrayList<String> allPlayer;
	
	
	PersistenceFacade facade;
	
	DefaultTableModel model;
	JTable table;
	
	
	public ScoreListPanel() {
		
		setTitle("CLASSIFICA");
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);
        setLocationRelativeTo(null);
		setResizable(false);
		
		
		this.model = new DefaultTableModel(); 
		this.table = new JTable(model);
		this.facade = new PersistenceFacade();
		
		
		
		table.setFont(new Font("Helvetica", Font.BOLD, 25));
		model.addColumn("POSITION");
		model.addColumn("PLAYER");
		model.addColumn("SCORE");
		table.setRowHeight(35);
		table.setBackground(Color.LIGHT_GRAY);
		table.setEnabled(false);
		updateData();
		

		this.setLayout(null);
		
		JScrollPane p = new JScrollPane(table);
		p.setSize(Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT);
	
		add(p);
		this.setVisible(true);
	}
	
	
	public void updateData() {
		allScore = new ArrayList<Integer>();
		allPlayer = new ArrayList<String>();
		
		allPlayer = facade.getAllPlayer();
		allScore = facade.getAllScore();
		
		
		for(int i = 0; i < allPlayer.size(); i++){
			
			model.addRow(new Object[]{(i+1)+"'", allPlayer.get(i), allScore.get(i)});
			

		}

	}


}

package Model.Core.Levels;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;

import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;

public class Levels {
	private List<Brick> objBricks;
	BufferedImage brick, fastBrick, flipBrick;
	private Ball objBall;
	private ArrayList<Paddle> objPaddles;
	private HashMap<Integer, List<Brick>> levels;	
	private int nLevel, nLine;
	
	public Levels(BufferedImage brick, BufferedImage fastBrick,BufferedImage flipBrick, Ball objBall, ArrayList<Paddle> objPaddles) {
		//this.level = TypeLevels.LEVEL2;
		this.brick = brick;
		this.fastBrick = fastBrick;
		this.flipBrick = flipBrick;	
		this.objBall = objBall;
		this.objPaddles = objPaddles;
		objBricks = new ArrayList<Brick>();
		this.levels = new HashMap<Integer, List<Brick>>();
		this.nLevel = 0;
		readFile();
		getLevels();
		}
	
	public Levels(BufferedImage brick, BufferedImage fastBrick,BufferedImage flipBrick, ArrayList<Paddle> objPaddles) {
		//this.level = TypeLevels.LEVEL2;
		this.brick = brick;
		this.fastBrick = fastBrick;
		this.flipBrick = flipBrick;	
		this.objPaddles = objPaddles;
		objBricks = new ArrayList<Brick>();
		}
	
	public void setLevel(int level) {
		
		this.nLevel = level;
		
		
	
	}
	
	public void setPlayersPosition(int numberOfPlayers, int playerIndex) {
        switch (numberOfPlayers) {
        case 2: {
        	if (playerIndex==0) {
        		objPaddles.get(playerIndex).setPosition(200, 580);
        	}
        	else objPaddles.get(playerIndex).setPosition(200, 3);
            break;
        }
        case 3: {
        	if (playerIndex==0) {
        		objPaddles.get(playerIndex).setPosition(50, 580);
        		objPaddles.get(playerIndex).setLimits(0, 240);
        	}
        	else if (playerIndex==1) {
        		objPaddles.get(playerIndex).setPosition(280, 580);
        		objPaddles.get(playerIndex).setLimits(240, 495);
        	}
        	else {
        		objPaddles.get(playerIndex).setPosition(280, 3);
        		objPaddles.get(playerIndex).setLimits(0, 495);
        	}
            break;
        }
        case 4: {
        	if (playerIndex==0) {
        		objPaddles.get(playerIndex).setPosition(50, 580);
        		objPaddles.get(playerIndex).setLimits(0, 240);
        	}
        	else if (playerIndex==1) {
        		objPaddles.get(playerIndex).setPosition(280, 580);
        		objPaddles.get(playerIndex).setLimits(240, 495);
        	}
        	else if (playerIndex==2) {
        		objPaddles.get(playerIndex).setPosition(50, 3);
        		objPaddles.get(playerIndex).setLimits(0, 240);
        	}
        	else {
        		objPaddles.get(playerIndex).setPosition(280, 3);
        		objPaddles.get(playerIndex).setLimits(240, 495);
        	}
            break;
        }
        }
    }
	
	public void setPlayersPosition(int numberOfPlayers) {
		switch (numberOfPlayers) {
		case 1: {
			objPaddles.get(0).setPosition(200, 580);
        	break;
		}
        case 2: {
        	objPaddles.get(0).setPosition(200, 580);
        	objPaddles.get(1).setPosition(200, 5);
        	break;
        }
		}
	}

	
	private Scanner myReader;
	private int i;

	public void readFile() {
		
		 File levelsFile = new File("./src/Model/Core/Levels/levels.txt");
	     try {
			this.myReader = new Scanner(levelsFile);
			System.out.println("LIVELLO CARICATO CORRETTAMENTE!");
		} catch (FileNotFoundException e) {
			System.err.println("LATTURA FILE LEVELS FALLITA!");
			e.printStackTrace();
		}
	     
	}
	
	public void getLevels() {
		
		 while (myReader.hasNextLine()) {
			 	String line = myReader.nextLine();
			 	
			    
			    String[] El = line.split(" ");
			    if(El[0].equals("#")) continue;

			    levelCreator(line, nLine++);
			    
			 
			   }
	}
	
	
	public void levelCreator(String line, int nLine) {
		
		
		String[] l = line.split(" ");
		
		if(l[0].equals("$")) {
			
			nLevel = Integer.parseInt(l[1]);
			this.nLine = 0;
			return;
		}
		
		if(l[0].equals("x")) {
			addLevel(nLevel, new ArrayList<Brick>(objBricks));
			objBricks.clear();
		}
		
		else {
			
		String[] li = line.split("");
		
			for(int j = 0; j < li.length; j++) {
				
				int[] posInitBrick = new int[2];
				// posizione di partenza dei Brick
				posInitBrick[0] = j * 80 + 15;  //nell'asse x
				posInitBrick[1] = nLine * 47 + 129; //nell'asse y
				if(li[j].equals("b")) objBricks.add(new Brick(brick, 65, 25, posInitBrick,4));
				if(li[j].equals("1")) {
					PowerUp speedUp = new BallSpeedUp(objBall);
					objBricks.add(new Brick(fastBrick, 35, 35, posInitBrick,1, speedUp));
				}
				if(li[j].equals("2")) {
					PowerUp flipUp = new SwitchPaddleDirection(objPaddles.get(0));
					objBricks.add(new Brick(flipBrick, 35, 35, posInitBrick,1, flipUp));
				}

				}
				
			}
		
			
		}
	
		
	
	
	public void addLevel(int nLevel, List<Brick> items) {
		
		levels.put(nLevel, items);
		
	}
	
	public int getNumberOfLevels() {
		
		return nLevel;
	}
	
	public ArrayList<Brick> getBricksDesposition(int lv) {
		
	
		return (ArrayList<Brick>) levels.get(lv);
		
	}
	
	public int getActualLevel() {
		
		return this.nLevel;
	}
}


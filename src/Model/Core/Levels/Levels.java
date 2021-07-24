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
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Model.Items.Wall;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;

public class Levels {
	private List<Brick> objBricks;
	BufferedImage brick, fastBrick, flipBrick;
	private Ball objBall;
	private ArrayList<Paddle> objPaddles;
	private HashMap<Integer, List<Brick>> levels;
	private int nLevel;
	
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
	
	/*
	public ArrayList<Brick> getBricksDesposition() {
		
		switch (level) {
			case LEVEL1: {
				for(int i = 0; i < 1; i++) {//first 2 layers down
					for (int j = 0; j < 1; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 15;  //nell'asse x
						posInitBrick[1] = j * 47 + 129; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
				for (int i = 0; i < 6; i++) {//first layers up
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 15;  //nell'asse x
					posInitBrick[1] = 30; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick,4));
				}
				
				for (int i = 0; i < 2; i++) { //2 left bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 15;  //nell'asse x
					posInitBrick[1] = 80; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				
				for (int i = 0; i < 2; i++) { //2 right bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 335;  //nell'asse x
					posInitBrick[1] = 80; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				//1 central bricks in the middle
				int[] posInitBrick = new int[2];
				posInitBrick[0] = 207+ 10;  //nell'asse x
				posInitBrick[1] = 80; //nell'asse y
				objBricks.add(new Brick(brick, 60, 25, posInitBrick,4));
				
				
				int[] posFastBrick = {171,75};
				PowerUp speedUp = new BallSpeedUp(objBall);
				objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));
				
				int[] posFlipBrick = {289,75};
				PowerUp flipUp = new SwitchPaddleDirection(objPaddles.get(0));
				objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, flipUp));
				break;
									
			}
			case LEVEL2: {
				for(int i = 0; i < 6; i++) {//first 2 layers down
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 15;  //nell'asse x
						posInitBrick[1] = j * 47 + 129; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
				for(int i = 0; i < 1; i++) {// 2 vertical brick in the left
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 15;  //nell'asse x
						posInitBrick[1] = j * 45 + 220; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
				for(int i = 0; i < 1; i++) {// 2 vertical brick down in the right
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 415;  //nell'asse x
						posInitBrick[1] = j * 45 + 220; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
				for(int i = 0; i < 2; i++) {// 2 layers down in the center
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 175;  //nell'asse x
						posInitBrick[1] = j * 45 + 220; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
				for (int i = 0; i < 6; i++) {//first layers up
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 15;  //nell'asse x
					posInitBrick[1] = 30; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick,4));
				}
				
				for (int i = 0; i < 2; i++) { //2 left bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 15;  //nell'asse x
					posInitBrick[1] = 80; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				for (int i = 0; i < 2; i++) { //2 right bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 335;  //nell'asse x
					posInitBrick[1] = 80; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				//1 central bricks in the middle
				int[] posInitBrick = new int[2];
				posInitBrick[0] = 207+ 10;  //nell'asse x
				posInitBrick[1] = 80; //nell'asse y
				objBricks.add(new Brick(brick, 60, 25, posInitBrick,4));
				
				
				int[] posFastBrick = {171,75};
				PowerUp speedUp = new BallSpeedUp(objBall);
				objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));
				
				int[] posFlipBrick = {289,75};
				PowerUp flipUp = new SwitchPaddleDirection(objPaddles.get(0));
				objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, flipUp));
				break;
						
            }
			
			
			case LEVEL3: {
				for(int i = 0; i < 6; i++) { //first 2 layers down
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 15;  //nell'asse x
						posInitBrick[1] = j * 47 + 129; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				
			
						
            }
			case MULTIPLAYER: {
				
				for(int i = 0; i < 4; i++) {//first 2 layers up
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 100;  //nell'asse x
						posInitBrick[1] = j * 43 + 193; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				for(int i = 0; i < 4; i++) {//first 2 layers down
					for (int j = 0; j < 2; j++) { 
						
						int[] posInitBrick = new int[2];
						
						// posizione di partenza dei Brick
						posInitBrick[0] = i * 80 + 100;  //nell'asse x
						posInitBrick[1] = j * 45 + 325; //nell'asse y
				
						// creo i Bricks
						objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
					}
				}
				for (int i = 0; i < 1; i++) { //1 left bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 100;  //nell'asse x
					posInitBrick[1] = 280; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				for (int i = 0; i < 1; i++) { //1 right bricks in the middle
					int[] posInitBrick = new int[2];
					posInitBrick[0] = i * 80+ 340;  //nell'asse x
					posInitBrick[1] = 280; //nell'asse y
					objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
				}
				
				//1 central bricks in the middle
				int[] posInitBrick = new int[2];
				posInitBrick[0] = 210+ 10;  //nell'asse x
				posInitBrick[1] = 280; //nell'asse y
				objBricks.add(new Brick(brick, 60, 25, posInitBrick,4));
			
				int[] posFastBrick = {175,275};//speed special brick
				objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, null));
				
				int[] posFlipBrick = {293,275};//change-direction special brick
				objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, null));
				break;
				
			}
		}
		return (ArrayList<Brick>) objBricks;
	}
	
	*/
	
	private Scanner myReader;
	private int i;

	public void readFile() {
		
		 File levelsFile = new File("./src/Model/Core/Levels/levels.txt");
	     try {
			this.myReader = new Scanner(levelsFile);
			System.out.println("LEVELS CARICATI CORRETTAMENTE!");
		} catch (FileNotFoundException e) {
			System.err.println("LATTURA FILE LEVELS FALLITA!");
			e.printStackTrace();
		}
	     
	}
	
	public void getLevels() {
		 int nLine = 0;
		 while (myReader.hasNextLine()) {
			 	String line = myReader.nextLine();
			 	
			    //System.out.println(line);
			    
			    String[] El = line.split(" ");
			    if(El[0].equals("#")) continue;
			    if(El[0].equals("end")) {
			    	nLine  = 0;
			    	continue;
			    }
			    
			    levelCreator(line, nLine++);
			    
			 
			   }
	}
	
	
	public void levelCreator(String line, int nLine) {
		
		
		String[] l = line.split(" ");
		
		if(l[0].equals("$")) {
			
			nLevel = Integer.parseInt(l[1]);
		
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
		
			addLevel(nLevel, objBricks);
		}
		
	
	
	public void addLevel(int nLevel, List<Brick> items) {
		
		levels.put(nLevel, items);
		
	}
	
	public ArrayList<Brick> getBricksDesposition() {
		
	
		return (ArrayList<Brick>) levels.get(1);
		
	}
}


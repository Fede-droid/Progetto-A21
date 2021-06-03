package Model.Core;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;

public class Levels {
	private TypeLevels level;
	private List<Brick> objBricks;
	BufferedImage brick, fastBrick, flipBrick;
	private Ball objBall;
	private Paddle objPaddle;
	
	public Levels(BufferedImage brick, BufferedImage fastBrick,BufferedImage flipBrick, Ball objBall, Paddle objPaddle) {
		this.level = TypeLevels.LEVEL1;
		this.brick = brick;
		this.fastBrick = fastBrick;
		this.flipBrick = flipBrick;	
		this.objBall = objBall;
		this.objPaddle = objPaddle;
		objBricks = new ArrayList<Brick>();
		}
	
	public void setLevel(TypeLevels level) {
		this.level = level;
	}
	
	public void setPlayersPosition(int numberOfPlayers, int playerIndex) {
        switch (numberOfPlayers) {
        case 2: {
        	if (playerIndex==0) {
        		objPaddle.setPosition(200, 580);
        	}
        	else objPaddle.setPosition(200, 20);
            break;
        }
        case 3: {
        	if (playerIndex==0) {
        		objPaddle.setPosition(50, 580);
        		objPaddle.setLimits(0, 240);
        	}
        	else if (playerIndex==1) {
        		objPaddle.setPosition(280, 580);
        		objPaddle.setLimits(240, 495);
        	}
        	else {
        		objPaddle.setPosition(280, 20);
        		objPaddle.setLimits(0, 495);
        	}
            break;
        }
        case 4: {
        	if (playerIndex==0) {
        		objPaddle.setPosition(50, 580);
        		objPaddle.setLimits(0, 240);
        	}
        	else if (playerIndex==1) {
        		objPaddle.setPosition(280, 580);
        		objPaddle.setLimits(240, 495);
        	}
        	else if (playerIndex==2) {
        		objPaddle.setPosition(50, 20);
        		objPaddle.setLimits(0, 240);
        	}
        	else {
        		objPaddle.setPosition(280, 20);
        		objPaddle.setLimits(240, 495);
        	}
            break;
        }
        }
    }
	
	public ArrayList<Brick> getBricksDesposition() {
		switch (level) {
			case LEVEL1: {
				for(int i = 0; i < 6; i++) {//first 2 layers down
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
				PowerUp flipUp = new SwitchPaddleDirection(objPaddle);
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
				PowerUp flipUp = new SwitchPaddleDirection(objPaddle);
				objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, flipUp));
				break;
						
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
				PowerUp speedUp = new BallSpeedUp(objBall);
				objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));
				
				int[] posFlipBrick = {293,275};//change-direction special brick
				PowerUp flipUp = new SwitchPaddleDirection(objPaddle);
				objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, flipUp));
				break;
				
			}
		}
		return (ArrayList<Brick>) objBricks;
	}
}


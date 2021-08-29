package Model.Core;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Model.BreakoutGame;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.BrickPowerUp;
import Model.Items.Item;
import Model.Items.Paddle;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;
import Model.Logic.CollisionAdvisor;
import Model.Logic.PowerUpListComparator;
import Model.Logic.ScreenItemFactory;
import Model.Logic.Levels.Levels;
import Music.Music;
import Music.MusicTypes;
import Utility.Utilities;

public class MultiplayerScreen extends Screen{
	
	private int numberOfPlayer, playerIndex;
	private int[] ballPosition;
	private ArrayList<Integer> paddlesPosition;
	private ArrayList<String> playersName;
	private ArrayList<String> powerUpActivation;
	private int[] tempBallPosition = {0,0};
	private boolean isXdirectionPositive = true;
	private boolean isYdirectionPositive = false;
	private Music mainMusic;
	private String scoreString;
	private int lifesLeft;
	private String isFastActiveString, isFlipActiveString;
	private int fastRemainingTime, flipRemainingTime;
	boolean victory, loss;
	private ArrayList<Integer> bricksHitLevel;




	public MultiplayerScreen(BreakoutGame game, int numberOfPlayer, int playerIndex) {
		super(game);
		this.numberOfPlayer = numberOfPlayer;
		this.playerIndex = playerIndex;
		paddlesPosition = new ArrayList<>();
		powerUpActivation = new ArrayList<>();
		playersName = new ArrayList<>();
		ballPosition = new int[2];
		this.mainMusic = new Music();
		bricksHitLevel = new ArrayList<>();
	}
	
	
	@Override
	synchronized public void update() {
		
	    objPaddles.get(playerIndex).move();
	    
	    int posPaddleX;
	    int posPaddleY;
	    
	    for (int i=0; i<numberOfPlayer; i++) {
			posPaddleX=2*i;
			posPaddleY=posPaddleX+1;
			if(i != playerIndex) {
				objPaddles.get(i).setPosition(paddlesPosition.get(posPaddleX), paddlesPosition.get(posPaddleY));
			}
		}
	    
	    
		for (int i=0; i<objBricks.size()-1; i++) {
			objBricks.get(i).setHitLevel(bricksHitLevel.get(i));
		}
		
		int i = 0;
		for (PowerUp tempPowerUp : objPowerUp.keySet()) {
			tempPowerUp.activateMultiplayer(powerUpActivation.get(i).equals("true"));
			i++;
		}

	    objBall.setPosition(ballPosition[0], ballPosition[1]);
	    
		
	}
	
	
	public void setLevel(int lv) {
		
		objBall = (Ball)ScreenItemFactory.getInstance().getScreenItem(Item.BALL);
		
		levels = new Levels(objBall, objPaddles);
		levels.setLevel(lv);
		objBricks = levels.getBricksDesposition(lv);
		
		for (int i=0; i<2*numberOfPlayer; i++) {
			paddlesPosition.add(0);
		}
		
		ArrayList<PowerUp> tempList = new ArrayList<PowerUp>();
		for(Brick tempBrick : objBricks) {
			try {
				if(!((BrickPowerUp) tempBrick == null)) {
					tempList.add(((BrickPowerUp)tempBrick).getPowerUp());
				}
			} catch(ClassCastException e) {	}
		}
		advisor = new CollisionAdvisor(objBall, mainMusic);
		objLife = ScreenItemFactory.getInstance().getScreenItem(Item.LIFE, Utilities.NUMBER_LIFE);
		
		objSpeedUpLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SPEED_UP);
		objSwitchLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SWITCH);
		objSfondo = ScreenItemFactory.getInstance().getScreenItem(Item.SFONDO);
		objBox = ScreenItemFactory.getInstance().getScreenItem(Item.BOX);
		objHit = ScreenItemFactory.getInstance().getScreenItem(Item.HIT);
		objWin = ScreenItemFactory.getInstance().getScreenItem(Item.WIN);
		objLose = ScreenItemFactory.getInstance().getScreenItem(Item.LOSE);
		objLongerLogo = ScreenItemFactory.getInstance().getScreenItem(Item.LONG_UP);
		objShorterLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SHORT_UP);
		
		objOn = ScreenItemFactory.getInstance().getScreenItem(Item.ON, tempList.size());
		
		setPlayersPosition(numberOfPlayer, playerIndex);
		// setLevel();
		PowerUpListComparator c = new PowerUpListComparator();
		tempList.sort(c);
		for(int i=0; i < tempList.size(); i++) {
			if(!objPowerUp.containsKey(tempList.get(i))) objPowerUp.put(tempList.get(i), objOn[i]);
		}
		for (int i=0; i<numberOfPlayer; i++) playersName.add("Name");
		for(int i=0; i<objBricks.size();i++) bricksHitLevel.add(0);
		
		this.scoreString = "0";
		
		lifesLeft = Utilities.NUMBER_LIFE;
		
		for (PowerUp tempPowerUp : objPowerUp.keySet()) {
			tempPowerUp.activateMultiplayer(false);
		}
		
		for(int i=0; i<objPowerUp.size(); i++) {
			powerUpActivation.add("false");
		}

		
		
	}
	
	public void setStringGameStatus(String gameStatus) {
		String gameStatusString= new String();
		gameStatusString=gameStatus;
		String gameStatusStringSplitted[] = gameStatusString.split(" ");
				
		for (int i=0; i<2*numberOfPlayer; i++) {
			paddlesPosition.set(i, Integer.parseInt(gameStatusStringSplitted[i]));
		}
		for (int j=2*numberOfPlayer; j<objBricks.size()+2*numberOfPlayer; j++) {
			bricksHitLevel.set(j-2*numberOfPlayer, Integer.parseInt(gameStatusStringSplitted[j]));
		}
		
		int k = objBricks.size()+2*numberOfPlayer;
		tempBallPosition[0]=ballPosition[0];
		tempBallPosition[1]=ballPosition[1];
		ballPosition[0] = Integer.parseInt(gameStatusStringSplitted[k++]);
		ballPosition[1] = Integer.parseInt(gameStatusStringSplitted[k++]);
		if (isXdirectionPositive && (tempBallPosition[0]-ballPosition[0])>0 || !isXdirectionPositive && (tempBallPosition[0]-ballPosition[0])<0
			|| isYdirectionPositive && (tempBallPosition[1]-ballPosition[1])>0 || !isYdirectionPositive && (tempBallPosition[1]-ballPosition[1])<0)
			mainMusic.playMusic(MusicTypes.HIT);
		isXdirectionPositive = (tempBallPosition[0]-ballPosition[0]<0) ? true : false;
		isYdirectionPositive = (tempBallPosition[1]-ballPosition[1]<0) ? true : false;
		scoreString=gameStatusStringSplitted[k++];
		lifesLeft = Integer.parseInt(gameStatusStringSplitted[k++]);
		
		for (PowerUp powerUp : objPowerUp.keySet()) {
			powerUpActivation.add(gameStatusStringSplitted[k++]);
		}
			
		/*isFastActiveString=gameStatusStringSplitted[k++];
		//this.fastRemainingTime=Integer.parseInt(gameStatusStringSplitted[k++]);
		k++;
		isFlipActiveString=gameStatusStringSplitted[k++];
		//this.flipRemainingTime=Integer.parseInt(gameStatusStringSplitted[k++]);
		k++;*/
		
		victory = Boolean.parseBoolean(gameStatusStringSplitted[k++]);
		loss = Boolean.parseBoolean(gameStatusStringSplitted[k++]);
		for (int i=0; i<numberOfPlayer; i++) {
			playersName.set(i, gameStatusStringSplitted[k++]);
		}
	}
	
	
	@Override 
	public void render() {
		
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(2);
			return;	
		}
		g = buffer.getDrawGraphics();
		g.setFont(new Font("Courier", Font.BOLD, 25)); 
		g.setColor(Color.WHITE);
		
		drawer.loadGraphics(g);
		
		drawer.draw(objSfondo);
		drawer.draw(objBall);
		drawer.draw(objBox);
		drawer.draw(objHit);
		drawer.draw(objSpeedUpLogo);
		drawer.draw(objSwitchLogo);
		drawer.draw(objLongerLogo);
		drawer.draw(objShorterLogo);
		
		for(int i=0; i < lifesLeft; i++) drawer.draw(objLife[i]);
		
		
		
		for(int i=0; i<numberOfPlayer; i++) { 
			drawer.draw(objPaddles.get(i));
			drawer.draw(playersName.get(i), objPaddles.get(i).getXPosition()+7, objPaddles.get(i).getYPosition()+21);
			}
		

		for(Brick tempBrick: objBricks) {
			if(!tempBrick.isDestroyed()) drawer.draw(tempBrick);	
		}
		
		for(PowerUp powerUp: objPowerUp.keySet()) {
			if(powerUp.isActive()) {
				drawer.draw(objPowerUp.get(powerUp));
			}
		}
		/*
		if(isFastActiveString.equals("true")) {
        	if (fastRemainingTime<4) 
        		drawer.draw(""+fastRemainingTime, 510, 170);
        	// else drawer.draw(on, 508, 153, 25, 25, null);  CREMO CREMO CREMO
        }
		*/
		// da mettere win e lose
		
		g.dispose();
		buffer.show();
		
		
	}
	

	
	public void setPlayersPosition(int numberOfPlayers, int playerIndex) {
        switch (numberOfPlayers) {
        case 2: {
        	if (playerIndex==0) {
        		objPaddles.get(playerIndex).setPosition(Utilities.INITIAL_POSITION_PADDLE_X, Utilities.INITIAL_POSITION_PADDLE_Y);
        	}
        	else objPaddles.get(playerIndex).setPosition(Utilities.INITIAL_POSITION_PADDLE_X, 3);
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
	
	public void setLevel() {
		
		for(int i = 0; i < 4; i++) {//first 2 layers up
			for (int j = 0; j < 2; j++) { 
				
				int[] posInitBrick = new int[2];
				
				// posizione di partenza dei Brick
				posInitBrick[0] = i * 80 + 100;  //nell'asse x
				posInitBrick[1] = j * 43 + 193; //nell'asse y
		
				// creo i Bricks
				objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
			}
		}
		for(int i = 0; i < 4; i++) {//first 2 layers down
			for (int j = 0; j < 2; j++) { 
				
				int[] posInitBrick = new int[2];
				
				// posizione di partenza dei Brick
				posInitBrick[0] = i * 80 + 100;  //nell'asse x
				posInitBrick[1] = j * 45 + 325; //nell'asse y
		
				// creo i Bricks
				objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
			}
		}
		for (int i = 0; i < 1; i++) { //1 left bricks in the middle
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 80+ 100;  //nell'asse x
			posInitBrick[1] = 280; //nell'asse y
			objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
		}
		
		for (int i = 0; i < 1; i++) { //1 right bricks in the middle
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 80+ 340;  //nell'asse x
			posInitBrick[1] = 280; //nell'asse y
			objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
		}
		
		//1 central bricks in the middle
		int[] posInitBrick = new int[2];
		posInitBrick[0] = 210+ 10;  //nell'asse x
		posInitBrick[1] = 280; //nell'asse y
		objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
		
		PowerUp speedUp = new BallSpeedUp(objBall);
		int[] posFastBrick = {175,275};//speed special brick
		objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posFastBrick, speedUp));
		
		PowerUp flipUp = new SwitchPaddleDirection(objPaddles.get(0));
		int[] posFlipBrick = {293,275};//change-direction special brick
		objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posFlipBrick, flipUp));
		
	}
	
	public int getPaddleXPosition() {
		return objPaddles.get(playerIndex).getXPosition();
	}
	
	public int getPaddleYPosition() {
		return objPaddles.get(playerIndex).getYPosition();
	}
	

}

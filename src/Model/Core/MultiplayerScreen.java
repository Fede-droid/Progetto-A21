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
	
	// classe principale per la gestione di una partita in modalità multiplayer
	
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
	
	
	// PATTERN GAME LOOP -> UPDATE, RENDER 
	// tutte le informazioni rivebute dal server saranno utilizzate per aggiornare 
	// le varie componenti (Update) che saranno successivamente disegnate (Render)
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
	    
	    
		for (int i=0; i<objBricks.size(); i++) {
			objBricks.get(i).setHitLevel(bricksHitLevel.get(i));
		}
		
		int i = 0;
		for (PowerUp tempPowerUp : objPowerUp.keySet()) {
			tempPowerUp.activateMultiplayer(powerUpActivation.get(i).equals("true"), objPaddles);
			i++;
		}

	    objBall.setPosition(ballPosition[0], ballPosition[1]);
	    
	    
	    if(victory) gameWin = true;
	    
	    if(loss) gameOver = true;
		
	}
	
	// inzializzazione livello per il multiplayer
	// inzializzazione componenti
	// costruzione degli oggetti utilizzati
	
	synchronized public void setLevel(int lv) {
		
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
		
		// carimento oggetti e inizializzazione con la factory 
		
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
		
		
		PowerUpListComparator c = new PowerUpListComparator();
		tempList.sort(c);
		for(int i=0; i < tempList.size(); i++) {
			if(!objPowerUp.containsKey(tempList.get(i))) objPowerUp.put(tempList.get(i), objOn[i]);
		}
		for (int i=0; i<numberOfPlayer; i++) playersName.add("Name");
		for(int i=0; i<objBricks.size();i++) bricksHitLevel.add(4);
		
		this.scoreString = "0";
		
		lifesLeft = Utilities.NUMBER_LIFE;
		
		for (PowerUp tempPowerUp : objPowerUp.keySet()) {
			tempPowerUp.activateMultiplayer(false, objPaddles);
		}
		
		for(int i=0; i<objPowerUp.size(); i++) {
			powerUpActivation.add("false");
		}
		
		players.get(playerIndex).getObjPaddle().setImageMainPaddle();

	
	}
	
	// Lettura informazioni rivevute dal server
	synchronized public void setStringGameStatus(String gameStatus) {
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
		
	
		int j = 0;
        for (PowerUp powerUp : objPowerUp.keySet()) {
            powerUpActivation.set(j, gameStatusStringSplitted[k++]);
            j++;
        }
			
		victory = Boolean.parseBoolean(gameStatusStringSplitted[k++]);
		loss = Boolean.parseBoolean(gameStatusStringSplitted[k++]);
		
		System.out.println(victory + " " + loss);
		for (int i=0; i<numberOfPlayer; i++) {
			playersName.set(i, gameStatusStringSplitted[k++]);
		}
	}
	
	
	// disegno oggetti dopo aggiornamento
	@Override 
	synchronized public void render() {
		
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
	
		if(gameWin) {
			drawer.draw(objWin);
			g.dispose();
			buffer.show();
			try {
				Thread.sleep(3000);
				gameWin();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		if(gameOver) {
			drawer.draw(objLose);
			g.dispose();
			buffer.show();
			try {
				Thread.sleep(3000);
				gameLose();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
		g.dispose();
		buffer.show();
		
	}
	
	
	public void gameWin(){
		
		gameStatus = false;
		game.inizializeMultiplayerAP();
		
		
	}
	
	public void gameLose() {
		
		gameStatus = false;
		game.inizializeMultiplayerAP();
		
		
	}
	
	// set posizione paddle dei vari giocatori nelle varie parti dello screen 
	// 2 sotto e 2 sopra (se 4 player), ognuno ha metà schermo per muoversi 
	
	synchronized public void setPlayersPosition(int numberOfPlayers, int playerIndex) {
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
	
	
	synchronized public int getPaddleXPosition() {
		return objPaddles.get(playerIndex).getXPosition();
	}
	
	synchronized public int getPaddleYPosition() {
		return objPaddles.get(playerIndex).getYPosition();
	}
	

}


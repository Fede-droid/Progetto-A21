
package Model.Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Core.Multiplayer.ClientThread;

import Model.Items.Ball;
import Model.Items.Box;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.PowerUpTypes;
import Model.Items.PowerUp.SwitchPaddleDirection;
import Model.Logic.CollisionAdvisor;
import Model.Logic.LifeAdvisor;
import Model.Logic.Player;
import Music.Music;
import Music.MusicTypes;

public class MultiplayerScreen extends Canvas implements Runnable{
	
private static final long serialVersionUID = 1L;
	
	BufferedImage box, ball, brick, brick1, brick2, brick3, fastBrick, hitBox, flipBrick, sfondo, youWin, youLose, on, off, fastLogo, flipLogo, life;
	//SpecialBrick objFlip, objFast;
	private boolean gameStatus = false;
	private boolean gameOver = false;
	private boolean gameWin = false;
	private Ball objBall;
	private int[] ballPosition;
	private List<Brick> objBricks;
	private Box objBox;
	private ArrayList<Integer> paddlesPosition;
	private ArrayList<Integer> bricksHitLevel;
	//private List<SpecialBrick> objSpecialBricks;
	private ScreenItem objSfondo;
	private ImagesLoader loader;
	private ArrayList<Paddle> objPaddles;
	Clip win,hit;
	boolean isMusicOn;
	private Graphics g;
	CollisionAdvisor ball1;
	private Music mainMusic;
	private BreakoutGame game;
	private String scoreString;
	private int lifesLeft;
	private Levels levels;
	double fastStartTime = 0;
	double flipStartTime = 0;
	double switchStart = 0;
	int i = 0;
	private int numberOfPlayer, playerIndex;
	private String isFastActiveString, isFlipActiveString;
	private int fastRemainingTime, flipRemainingTime;

	
	public MultiplayerScreen(BreakoutGame game, int numberOfPlayer, int playerIndex) {
		this.game = game;
		objBricks = new ArrayList<>();
		objPaddles = new ArrayList<>();
		//objSpecialBricks = new ArrayList<SpecialBrick>();
		uploadImages();
		this.mainMusic = new Music();
		ballPosition = new int[2];
		this.numberOfPlayer = numberOfPlayer;
		this.playerIndex = playerIndex;
		paddlesPosition = new ArrayList<>();
		bricksHitLevel = new ArrayList<>();
	}
	
	


	// ciclo di gioco
	@Override
	public void run() {
		
		double previous = System.nanoTime(); 
		double delta = 0.0;
		double fps = 100.0;
		double ns = 1e9/fps; // numero di nano sec per fps
		gameStatus = true;
		
		//switchare off/on
		//if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOOP);
		
		while (gameStatus) {
			double current = System.nanoTime();
			
			double elapsed = current - previous;
			previous = current;
			delta += elapsed;

				while (delta >= ns) {
				   update();	
				   delta -= ns;
				}
			render();
		}
	}
	
		// caricamento immagini 
		private void uploadImages() {
			
			loader = new ImagesLoader();
			box = loader.uploadImage("../Images/box.png");
			hitBox = loader.uploadImage("../Images/hit.png");
			ball = loader.uploadImage("../Images/ball.png");
			sfondo = loader.uploadImage("/Images/sfondo.jpeg");
			brick = loader.uploadImage("/Images/brick.png");
			brick1 = loader.uploadImage("/Images/brick1.png");
			brick2 = loader.uploadImage("/Images/brick2.png");
			brick3 = loader.uploadImage("/Images/brick3.png");
			fastBrick = loader.uploadImage("/Images/fast.png");
			flipBrick = loader.uploadImage("/Images/flip.png");
			youWin = loader.uploadImage("/Images/youWin.png");
			youLose = loader.uploadImage("/Images/lose.png");
			on = loader.uploadImage("/Images/on.png");
			off = loader.uploadImage("/Images/off.png");
			fastLogo = loader.uploadImage("/Images/fastLogo.png");
			flipLogo = loader.uploadImage("/Images/flipLogo.png");
			life = loader.uploadImage("/Images/life.png");
		}

		// disegno di oggetti grafici a schermo
		synchronized public void render() {
			
			// creazione di 2 buffer cos� che l'immagine venga aggiornata su uno e mostrata sull'altro 
			// modo ciclico, evita gli scatti.
			
			BufferStrategy buffer = this.getBufferStrategy();
			
			if(buffer == null) {
				createBufferStrategy(2);
				return;	
			}
			
			this.g = buffer.getDrawGraphics();// oggetto di tipo Canvas su cui si pu� disegnare
			
			g.setFont(new Font("Courier", Font.BOLD, 25)); 
			g.setColor(Color.WHITE);
			
			objSfondo.render(g, this);
			objBall.render(g);
			for (Paddle tempPaddle : objPaddles) tempPaddle.render(g);
			objBox.render(g);
            
			g.drawImage(hitBox, 508, 3, 30, 30, null);
            
            g.drawImage(fastLogo, 508, 120, 25, 25, null);
            if(isFastActiveString.equals("true")) {
            	if (fastRemainingTime<4) 
            		g.drawString(""+fastRemainingTime, 510, 170);
            	else g.drawImage(on, 508, 153, 25, 25, null);
            }
            else g.drawImage(off, 508, 153, 25, 25, null);
            
            g.drawImage(flipLogo, 508, 195, 25, 25, null);
            if(isFlipActiveString.equals("true")) {
            	if (flipRemainingTime<4) 
            		g.drawString(""+flipRemainingTime, 510, 245);
            	else g.drawImage(on, 508, 228, 25, 25, null);
            }
            else g.drawImage(off, 508, 228, 25, 25, null);
		
			g.drawString(scoreString, 505, 58);
			
			for (Brick tempBrick : objBricks) {
				if (tempBrick.getHitLevel()!=0) {
					if (!tempBrick.getHasPowerUp()) {
						int hitLevel = tempBrick.getHitLevel();
						switch (hitLevel) {
							case 1:
								tempBrick.setImage(brick3); 
								break;
							case 2:
								tempBrick.setImage(brick2); 
								break;
							case 3:
								tempBrick.setImage(brick1); 
								break;
						    default:
						    	tempBrick.setImage(brick);
					        }
					}
					tempBrick.render(g);
				}
			}
			
			
			switch (lifesLeft) {
				case 1:
					g.drawImage(life, 505, 78, 20, 20, null); 
					break;
				case 2:
					g.drawImage(life, 505, 78, 20, 20, null); 
					g.drawImage(life, 505, 88, 20, 20, null); 
					break;
				case 3:
					g.drawImage(life, 505, 78, 20, 20, null); 
					g.drawImage(life, 505, 88, 20, 20, null); 
					g.drawImage(life, 505, 98, 20, 20, null);
			}

		    /*if (!gameWin) endGameOver();
			
			if (gameOver) g.drawImage(youLose, 495/2 - 250, Utilities.SCREEN_HEIGHT/2 - 250, 500, 500, null);*/
			
			g.dispose();
			
			buffer.show();
		}
		
		// aggiornamento ciclo di gioco
		synchronized public void update() {
		    
		    objPaddles.get(playerIndex).move();
		    objPaddles.get(playerIndex).switchDirectionMultiplayer(isFlipActiveString.equals("true"));
		    int posPaddleX;
		    int posPaddleY;
			for (int i=0; i<numberOfPlayer; i++) {
				posPaddleX=2*i;
				posPaddleY=posPaddleX+1;
				if(i != playerIndex) {
					objPaddles.get(i).setPosition(posPaddleX, posPaddleY);;
				}
			}
			if (isFlipActiveString.equals("true")) {
				
			}
			
			for (int i=0; i<objBricks.size(); i++) {
				objBricks.get(i).setHitLevel(bricksHitLevel.get(i));
			}
			
			objBall.setPosition(ballPosition[0], ballPosition[1]);
		
		}
		
		// inzializzazione partita
		public void start() {
			
			
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, posInitSfondo);
			
			int[] posBox = new int[2];
			posBox[0] = 495;  //nell'asse x
			posBox[1] = 0; //nell'asse y
			objBox = new Box(box, 80, 700, posBox);
						
			// posizione di partenza ball
			int[] posInitBall = new int[2];

			posInitBall[0] = Utilities.INTIAL_POSITION_BALL_X;  // x
			posInitBall[1] = Utilities.INITIAL_POSITION_BALL_Y;  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
						
			//creazione e posizionamento dei Bricks
			levels = new Levels(brick, fastBrick, flipBrick, null, objPaddles.get(playerIndex));
			levels.setPlayersPosition(numberOfPlayer, playerIndex);
			setLevel(TypeLevels.MULTIPLAYER);
		}
		
		public void setStringGameStatus(String gameStatus) {
			String gameStatusString= new String();
			gameStatusString=gameStatus;
			String gameStatusStringSplitted[] = gameStatusString.split(" ");
			
			paddlesPosition = new ArrayList<>();
			bricksHitLevel = new ArrayList<>();
			
			for (int i=0; i<2*numberOfPlayer; i++) {
				paddlesPosition.add(Integer.parseInt(gameStatusStringSplitted[i]));
			}
			for (int j=2*numberOfPlayer; j<objBricks.size()+2*numberOfPlayer; j++) {
				bricksHitLevel.add(Integer.parseInt(gameStatusStringSplitted[j]));
			}
			int k = objBricks.size()+2*numberOfPlayer;
			ballPosition[0] = Integer.parseInt(gameStatusStringSplitted[k++]);
			ballPosition[1] = Integer.parseInt(gameStatusStringSplitted[k++]);
			scoreString=gameStatusStringSplitted[k++];
			lifesLeft=Integer.parseInt(gameStatusStringSplitted[k++]);
			isFastActiveString=gameStatusStringSplitted[k++];
			fastRemainingTime=Integer.parseInt(gameStatusStringSplitted[k++]);
			isFlipActiveString=gameStatusStringSplitted[k++];
			flipRemainingTime=Integer.parseInt(gameStatusStringSplitted[k++]);
		}

		private void endGameOver() {
			if(!gameStatus) {
				if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOSE);

				// ho perso
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}	
				game.gameWin(false);	
			}
		}
		
		private void endGameWin() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			game.gameWin(false);
		}
		

		//Aggiungo player alla partita
		public void addPlayers(ArrayList<Player> players) {
			for (int i=0; i<numberOfPlayer; i++) {
				this.objPaddles.add(players.get(i).getObjPaddle());
			}
		}
		
		//modifico musica 
		public void setMusic(Boolean b) {
			mainMusic.setMusic(b);
		}
		
		
		public void setLevel(TypeLevels lv) {
			levels.setLevel(lv);
			objBricks = levels.getBricksDesposition();
		}
		
		public int getPaddleXPosition() {
			return objPaddles.get(playerIndex).getXPosition();
		}
		
		public int getPaddleYPosition() {
			return objPaddles.get(playerIndex).getYPosition();
		}

		public Graphics getG() {
			return g;
		}
	}
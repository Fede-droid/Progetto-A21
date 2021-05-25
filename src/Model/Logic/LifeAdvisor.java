package Model.Logic;

import Model.Core.Screen;
import Model.Items.Ball;
import Music.Music;
import Music.MusicTypes;

public class LifeAdvisor {
	
	private Player p;
	private Music loseLifeMusic;
	private CollisionAdvisor collision;
	private Ball ball;
	
	public LifeAdvisor(Player p, Music loseLife, CollisionAdvisor collision, Ball ball) {
		
		this.p = p;
		this.loseLifeMusic = loseLife;
		this.collision = collision;
		this.ball = ball;
	}
	
	// false se è ancora in vita, true se ha perso
	
	public boolean checkLife() {
		if(collision.checkGameOver()) {
			if (loseLifeMusic.isMusicOn()) loseLifeMusic.playMusic(MusicTypes.LOSE_LIFE);
			p.loseLife();
			if(p.getLife() < 1) return true;
			ball.refresh();
		}
		return false;
		
		}
	
	public void resetLife() {
		
		p.resetLife();
	}
		
	}
	
	



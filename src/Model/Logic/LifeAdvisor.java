package Model.Logic;

import Model.Core.Screen;
import Model.Items.Ball;
import Music.Music;
import Music.MusicTypes;
import Utility.Utilities;

public class LifeAdvisor {
	
	// classe per la gestione delle vite 
	// le vite si possono settare in utilities

	private Music loseLifeMusic;
	private CollisionAdvisor collision;
	private Ball ball;
    private int life;
	
	public LifeAdvisor(Music loseLife, CollisionAdvisor collision, Ball ball) {
		
		this.loseLifeMusic = loseLife;
		this.collision = collision;
		this.ball = ball;
        life = Utilities.NUMBER_LIFE;
	}
	
	public LifeAdvisor(CollisionAdvisor collision, Ball ball) {
		this.collision = collision;
		this.ball = ball;
        life = Utilities.NUMBER_LIFE;
	}
	
	// false se è ancora in vita, true se ha perso
	
	public boolean checkLife(int numberOfPlayers) {
		if(collision.checkGameOver(numberOfPlayers)) {
			if (loseLifeMusic.isMusicOn() && getLife() > 1) loseLifeMusic.playMusic(MusicTypes.LOSE_LIFE);
            life -= 1;
			if(life < 1) return true;
			ball.refresh();
		}
		return false;	
	}
	
    public int getLife() {
        return life;
    }
}
	
	



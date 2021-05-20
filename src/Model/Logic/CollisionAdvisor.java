package Model.Logic;

import Model.Items.Ball;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Music.Music;
import Music.MusicTypes;

public class CollisionAdvisor {
	Ball ball;
	Music collisionMusic;

	public CollisionAdvisor(Ball ball, Music collisionMusic) {
		this.ball = ball;
		this.collisionMusic = collisionMusic;
	}
	
	public boolean checkBorderCollision() {
        if ((ball.getPosition()[0] + ball.getImageHeight()) >= Utilities.SCREEN_WIDTH) {
            ball.setXdir(-1);
        }

        if (ball.getPosition()[0] <= 0) {
            ball.setXdir(1);
        }

        if (ball.getPosition()[1] <= 0) {
            ball.setYdir(1);
        }
        
        if((ball.getPosition()[1] + ball.getImageHeight()) >= Utilities.SCREEN_HEIGHT) {
               return false;
        }
        else return true;
    }
	
	public boolean checkCollisionLato(ScreenItem item) {
		
		if ((ball.getPosition()[1] + ball.getImageHeight()) >= item.getPosition()[1]  &&  ball.getPosition()[1] <= (item.getPosition()[1]+item.getImageHeight())) {  
			if (ball.getPosition()[0] == (item.getPosition()[0]+item.getImageWidth())) {
				ball.setXdir(1);
				item.hit();
				if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
			else if ((ball.getPosition()[0]+ball.getImageWidth()) == item.getPosition()[0]) {
				ball.setXdir(-1);
				item.hit(); 
				if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
		    }
		}
		return false;
	}
	
	public boolean checkCollision(ScreenItem item) {
		if ((ball.getPosition()[0]+ball.getImageWidth()) >= item.getPosition()[0] && ball.getPosition()[0] <= (item.getPosition()[0] + item.getImageWidth())) {
			if (ball.getPosition()[1] == (item.getPosition()[1]+item.getImageHeight())) {
				ball.setYdir(1);
				item.hit();
				if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
			else if ((ball.getPosition()[1] + ball.getImageHeight()) == (item.getPosition()[1])) {
				ball.setYdir(-1);
				item.hit();
				if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollision(Paddle item) {
		if ((ball.getPosition()[0]+ball.getImageWidth()) >= item.getPosition()[0] && ball.getPosition()[0] <= (item.getPosition()[0] + item.getImageWidth())) {
			if ((ball.getPosition()[1] + ball.getImageHeight()) == (item.getPosition()[1])) {
				if (ball.getPosition()[0] >= (item.getPosition()[0] + item.getImageWidth()/2)) {
					ball.setXdir(1);
					ball.setYdir(-1);
					item.hit();
					if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
					return true;
				}
				else ball.setXdir(-1);
				ball.setYdir(-1);
				item.hit();
				if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
		}
		return false;
	}

}
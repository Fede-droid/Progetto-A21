package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Logic.ScoreAdvisor;

class ScoreAdviosrTest {

	private ScoreAdvisor s;
	
	@BeforeEach
	void startMatch() {
		this.s = new ScoreAdvisor();
		
		s.start();
		
	}
	
	@Test
	void test() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		int score = s.getScoreEnd(100);
		System.out.println(score);
		
	}

}

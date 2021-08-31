package JunitTester;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Logic.ScoreAdvisor;

class ScoreAdviosrTest {

	private ScoreAdvisor s;
	private int score;
	
	@BeforeEach
	void run() {
		
		  this.s = new ScoreAdvisor();
			
		  s.start();
		
		
	}

	@Test
	synchronized void test() {
		
		
		try {
			wait(60000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		 score = s.getScoreEnd(20);
		 assertTrue("FAILS", (score != 20));
		

		
	}

}

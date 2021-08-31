package JunitTester;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Logic.ScoreAdvisor;

class ScoreAdviosrTest {

	private ScoreAdvisor s;
	
	
	@Test
	void test() {
		
	  this.s = new ScoreAdvisor();
			
	  s.start();
			
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		int score = s.getScoreEnd(100);
		
		assertTrue("FAILS", (score == 100));
		
		
	}

}

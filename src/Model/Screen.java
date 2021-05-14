package Model;

import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;

//screen=bullet

public class Screen {
	
	private Ball ball;
	private Brick brick;
	private Paddle paddle;
	int i = 0;
	
	// ciclo di gioco
	public void s() {
		
		double previous = System.nanoTime(); 
		double delta = 0.0;
		double fps = 10.0;
		double ns = 1e9/fps; // numero di nano sec per fps
		
		while (true)
		{
		double current = System.nanoTime();
		
		double elapsed = current - previous;
		previous = current;
		delta += elapsed;

		//processInput();

		while (delta >= ns)
		{
		update();
			
		delta -= ns;
		}

		render(i);
		}
	}
	
		
		private void render(int i) {
			
			System.out.println(i);
		
		}

		private void update() {
			
			if (i > 100000) i = 0;
			i = i + 1;
		}
		
		
		
		
	}
	


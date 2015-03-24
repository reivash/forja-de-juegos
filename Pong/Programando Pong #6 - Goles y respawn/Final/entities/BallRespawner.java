package entities;

import core.Main;
import util.Updatable;

public class BallRespawner implements Updatable {

	private int respawn_interval = 60;
	private int to_respawn       = respawn_interval;
	
	public void update() {
		
		if(Main.ball == null) {
			
			if(to_respawn > 0) {
				to_respawn--;
			} else {
				// Respawn ball
				Main.ball = new Ball(Main.SCREEN_WIDTH/2, Main.SCREEN_HEIGHT/2, 40, 40);
				Main.addList.add(Main.ball);
				to_respawn = respawn_interval;
			}
		}
	}

}

package entities;

import org.lwjgl.input.Keyboard;

public class PlayerBarController {

	private Bar bar;
	private float speed = 5;
	
	public PlayerBarController(Bar bar) {
		super();
		this.bar = bar;
	}

	public void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			bar.setDY(-speed);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			bar.setDY(speed);
	}
}

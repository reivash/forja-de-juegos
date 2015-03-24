package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Vector2f;

import core.Main;

import util.Updatable;

public class Bar implements Renderable, Updatable {

	public float x, y, 
	             w, h, 
	             dy, 
	             friction=0.9f;
	
	private BarController ctrll;
	
	public final static int PLAYER = 0;
	public final static int CPU    = 1;
	
	private float speed = 5;
	
	public Bar(float x, float y, float w, float h, int barType) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		if(barType == PLAYER) {
			ctrll = new PlayerBarController();
		}
		else {
			ctrll = new CPUBarController();
		}
	}

	public void update() {
	
		ctrll.update();
		
		// Update vertical position
		y += dy;
		
		// Reduce speed
		dy *= friction;
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,   y);
			GL11.glVertex2f(x,   y+h);
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}

	public void setDY(float dy) {
		this.dy = dy;
	}
	
	
	public interface BarController {
		public void update();
	}
	
	public float getCenterY() {
		return y + h/2;
	}
	
	// CPU CONTROLLER
	public class CPUBarController implements BarController {

		public void update() {
			
			// Where is the ball?
			boolean ballDownwards = Main.ball.getCenterY() - getCenterY() > 0;
			
			// Move to the ball
			if(ballDownwards) 
				setDY(speed);
			else 
				setDY(-speed);
		}
	}
	
	// PLAYER CONTROLLER
	public class PlayerBarController implements BarController {

		public void update() {
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
				setDY(-speed);
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				setDY(speed);
		}
	}

	public boolean contains(Vector2f p) {
		return x <= p.x && p.x < x+w
			&& y <= p.y && p.y < y+h;
	}

}

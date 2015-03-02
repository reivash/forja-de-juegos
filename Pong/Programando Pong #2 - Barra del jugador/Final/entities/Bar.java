package entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;

import util.Updatable;

public class Bar implements Renderable, Updatable {

	private float x, y, 
	              w, h, 
	              dy, 
	              friction=0.9f;
	
	private PlayerBarController ctrll = new PlayerBarController(this);
	
	public Bar(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
}

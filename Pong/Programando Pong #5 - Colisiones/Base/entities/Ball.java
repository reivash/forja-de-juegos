package entities;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;

import core.Main;

import util.Updatable;

public class Ball implements Updatable, Renderable {

	public float  x, y, 
    			  w, h, 
    			  dx, dy,
    			  default_speed = 5;
    
	public Ball(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		Random r = new Random();
		
		// Random x speed
		if(r.nextBoolean())
			dx =  default_speed;
		else
			dx = -default_speed;
		
		// Random y speed
		if(r.nextBoolean())
			dy =  default_speed;
		else
			dy = -default_speed;
	}
	
	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,   y);
			GL11.glVertex2f(x,   y+h);
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}

	public void update() {
		
		// Update ball position
		x += dx;
		y += dy;
		
		// Horizontal screen collision
		if(x < 0 || x+w > Main.SCREEN_WIDTH)
			dx *= -1;
		
		// Vertical   screen collision
		if(y < 0 || y+h > Main.SCREEN_HEIGHT)
			dy *= -1;
	}

	public float getCenterY() {
		return y + h/2;
	}

}

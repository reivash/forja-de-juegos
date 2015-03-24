package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Vector2f;

import util.Updatable;
import core.Main;

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
		
		// Player bar collision
		checkCollision(Main.playerBar);
		
		// CPU    bar collision
		checkCollision(Main.cpuBar);
		
		// CPU goals
		if(x < 0) {
			System.out.println("CPU goal!!");
			deleteBall();
		}
		
		// Player goals
		if(Main.SCREEN_WIDTH < x+w) {
			System.out.println("Player goal!!");
			deleteBall();
		}
	}

	private void deleteBall() {
		Main.renderList.remove(this);
		Main.removeList.add(this);
		Main.ball = null;
	}

	private void checkCollision(Bar bar) {

		// Generate points
		List<Vector2f> points = new ArrayList<Vector2f>();
		points.add(new Vector2f(x,   y));
		points.add(new Vector2f(x,   y+h));
		points.add(new Vector2f(x+w, y+h));
		points.add(new Vector2f(x+w, y));
		
		// Check points
		for(Vector2f p : points) {
			if(bar.contains(p)) {
				resolveCollision(bar, p);
				
				// Increase ball speed
				dx = Math.signum(dx) * (Math.abs(dx) + 1);
				dy = Math.signum(dy) * (Math.abs(dy) + 1);
				
				break;
			}
		}
	}

	private void resolveCollision(Bar bar, Vector2f p) {
		float distX = Math.min(p.x - bar.x, bar.x + bar.w - p.x);
		float distY = Math.min(p.y - bar.y, bar.y + bar.h - p.y);
		
		if(distX < distY) {
			dx *= -1;
		} else {
			dy *= -1;
		}
	}

	public float getCenterY() {
		return y + h/2;
	}

}

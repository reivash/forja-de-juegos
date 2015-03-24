package entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;

public class Rectangle implements Renderable {

	private float x,y,w,h;

	public Rectangle(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,   y);
			GL11.glVertex2f(x,   y+h);
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}
}

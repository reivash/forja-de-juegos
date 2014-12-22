import org.lwjgl.opengl.GL11;


public class Square {

	float x = 0;
	float y = 0;
	float w = 0; // width
	float h = 0; // height
	
	float red = 0;
	
	float counter = 0;
	float scale   = 4;
	
	public Square(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void update() {
		// Update position
		x = (float) (x + Math.sin(counter)*scale);
		y = (float) (y + Math.cos(counter)*scale);
		counter = counter + 0.2f;
		
		// Update color
		red = red + 0.1f;
		if(red > 1)
			red = 0;
	}
	
	public void render() {
		// White square
		GL11.glColor3f(red, 1f, 0f); // Colors range from 0 to 1
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,   y);
			GL11.glVertex2f(x,   y+h);
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}
}

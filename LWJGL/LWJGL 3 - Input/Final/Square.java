import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


public class Square {

	float x = 0;
	float y = 0;
	float w = 0; // width
	float h = 0; // height
	
	float red = 0;
	
	float counter = 0;
	float scale   = 4;
	
	// Color
	float r=0f,g=1f,b=1f;
	
	Random random = new Random();
	
	float speed = 2f;
	
	public Square(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void update() {
//		checkMouse();
		checkKeyboard();
	}
	
	private void checkKeyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			x = x - speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			x = x + speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			y = y - speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			y = y + speed;

		// Generate random color
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			generateRandomColor();
	}

	private void checkMouse() {
		this.x = Mouse.getX();
		this.y = Main.HEIGHT - Mouse.getY();
		
		// On left click
		if(Mouse.isButtonDown(0)) 
			generateRandomColor();
	}

	private void generateRandomColor() {
		// Random color
		r = random.nextFloat();
		g = random.nextFloat();
		b = random.nextFloat();
	}

	public void render() {
		// White square
		GL11.glColor3f(r, g, b); // Colors range from 0 to 1
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,   y);
			GL11.glVertex2f(x,   y+h);
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}
}

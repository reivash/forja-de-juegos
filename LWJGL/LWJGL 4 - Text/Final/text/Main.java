package text;

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;


public class Main {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static void main(String args[]) {
		createDisplay();
		createOpenGLCtx();

		TrueTypeFont font = new TrueTypeFont(new Font("Verdana", Font.BOLD, 40), false);

		float x = SCREEN_WIDTH/2 - 200;
		float y = SCREEN_HEIGHT/2;
		
		float k = 0;
		float scale = 20;
		
		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			font.drawString(x + (float) Math.sin(k) * scale, y + (float) Math.cos(k) * scale, "Hello Slick!");
			
			k += 0.1f;
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}

	private static void createOpenGLCtx() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	private static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle("Text");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}

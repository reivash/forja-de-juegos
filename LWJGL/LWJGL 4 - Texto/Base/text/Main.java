package text;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Main {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static void main(String args[]) {
		createDisplay();
		createOpenGLCtx();

		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}

	private static void createOpenGLCtx() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
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

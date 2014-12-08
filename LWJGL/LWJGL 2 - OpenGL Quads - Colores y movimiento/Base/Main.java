import org.lwjgl.LWJGLException;

public class Main {

	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Hello World! LWJGL");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void initOpenGL() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0,  800, 600, 0,  -1,  1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	}
	
	/* Main */
	public static void main(String[] args) {
		initDisplay();
		initOpenGL();
		
		float x = 175;
		float y = 256;
		float w = 200; // width
		float h = 200; // height
		
		while(!Display.isCloseRequested()) {
			
			// White square
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x,   y);
				GL11.glVertex2f(x,   y+h);
				GL11.glVertex2f(x+w, y+h);
				GL11.glVertex2f(x+w, y);
			GL11.glEnd();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
}

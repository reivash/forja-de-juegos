import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

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
		
		while(!Display.isCloseRequested()) {
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
}
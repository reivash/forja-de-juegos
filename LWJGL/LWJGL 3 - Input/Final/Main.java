import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main {

	public final static int WIDTH  = 800;
	public final static int HEIGHT = 600;
	
	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Hello World! LWJGL");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void initOpenGL() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, WIDTH, HEIGHT, 0,  -1,  1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	}
	
	/* Main */
	public static void main(String[] args) {
		initDisplay();
		initOpenGL();
		
		Square square = new Square(175, 256, 200, 200);
		
		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			square.update();
			square.render();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
}

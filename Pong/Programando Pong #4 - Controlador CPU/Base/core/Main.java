package core;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;

import entities.Ball;
import entities.Bar;

import util.Updatable;

public class Main {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static ArrayList<Updatable>  updateList = new ArrayList<Updatable>();
	public static ArrayList<Renderable> renderList = new ArrayList<Renderable>();
	public static ArrayList<Object> 	addList    = new ArrayList<Object>();
	public static ArrayList<Object> 	removeList = new ArrayList<Object>();
	
	public static void main(String args[]) {
		createDisplay();
		createOpenGLCtx();
		createEntities();
		
		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			update();
			render();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}

	private static void createOpenGLCtx() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
	}

	private static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle("Pong");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void createEntities() {
		
		// Create Player
//		Bar playerBar = new Bar(40, 220, 40, 120);
//		renderList.add(playerBar);
//		updateList.add(playerBar);
		
		// Create Ball
		Ball ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 40, 40);
		renderList.add(ball);
		updateList.add(ball);
	}
	
	private static void render() {
		for(Renderable r : renderList) {
			r.render();
		}
	}

	private static void update() {
		for(Object o : addList) {
			if(o instanceof Renderable)
				renderList.add((Renderable) o);
			if(o instanceof Updatable)
				updateList.add((Updatable) o);
		}
		addList.clear();
		
		for(Updatable u : updateList) {
			u.update();
		}
		
		for(Object o : removeList) {
			renderList.remove(o);
			updateList.remove(o);
		}
		removeList.clear();
	}

}

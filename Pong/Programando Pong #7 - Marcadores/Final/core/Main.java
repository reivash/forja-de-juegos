package core;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;

import entities.Ball;
import entities.BallRespawner;
import entities.Bar;
import entities.Rectangle;
import entities.ScoreMarker;

import util.Updatable;

public class Main {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static ArrayList<Updatable>  updateList = new ArrayList<Updatable>();
	public static ArrayList<Renderable> renderList = new ArrayList<Renderable>();
	public static ArrayList<Object> 	addList    = new ArrayList<Object>();
	public static ArrayList<Object> 	removeList = new ArrayList<Object>();
	
	public static Bar playerBar;
	public static Bar    cpuBar;
	public static Ball     ball;
	
	public static ScoreMarker playerMarker;
	public static ScoreMarker    cpuMarker;
	
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
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
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
		playerBar = new Bar(40, 220, 40, 120, Bar.PLAYER);
		renderList.add(playerBar);
		updateList.add(playerBar);
		
		// Create CPU
		cpuBar = new Bar(SCREEN_WIDTH - 80, 220, 40, 120, Bar.CPU);
		renderList.add(cpuBar);
		updateList.add(cpuBar);
		
		// Create Ball
		ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 40, 40);
		renderList.add(ball);
		updateList.add(ball);
		
		// Create ball respawner
		updateList.add(new BallRespawner());
		
		// Score markers
		playerMarker = new ScoreMarker(SCREEN_WIDTH/3 - 20, 40);
		cpuMarker    = new ScoreMarker(SCREEN_WIDTH*2/3 - 20, 40);
		renderList.add(playerMarker);
		renderList.add(cpuMarker);
		
		// Beautiful elements of Pong
		renderList.add(new Rectangle(SCREEN_WIDTH/2-10, 0, 20, SCREEN_HEIGHT));
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

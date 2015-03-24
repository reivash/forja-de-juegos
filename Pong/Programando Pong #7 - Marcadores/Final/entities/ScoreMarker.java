package entities;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;
import org.newdawn.slick.TrueTypeFont;

public class ScoreMarker implements Renderable {

	private float x, y;
	
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 80), true);
	
	public int score = 0;
	
	public ScoreMarker(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void render() {
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		font.drawString(x, y, String.valueOf(score));
		GL11.glDisable(GL11.GL_BLEND);
	}

}

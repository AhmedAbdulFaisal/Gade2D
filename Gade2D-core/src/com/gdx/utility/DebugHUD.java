package com.gdx.utility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.entity.Player;
import com.gdx.states.GameState;

/* class handles debug output
 * 
 */

public class DebugHUD { //little hack makes DebugHUD extend Player

	public TextManager tm;
	public static String text;
	private int timer = 0;
	private final int TIMER_LIMIT = 500;
	public ArrayList<textWidget> console;
	private int integer = 0;
	
	private OrthographicCamera camera;
	
	public static String getText() {
		return text;
	}

	public static void setText(String text) {
		DebugHUD.text = text;
	}

	public DebugHUD(OrthographicCamera cam, float xPos, float yPos) {
		this.camera = cam;
		
		text = "";
		tm = new TextManager(12);
		console = new ArrayList<textWidget>();
		//console.add(new textWidget(5, "xPos"));
		
		
	}
	
	public void updateConsoleList() {
		
		
	}
	
	public void tick() {
		float[] pp = {EntityManager.getPlayer().getX(),EntityManager.getPlayer().getY(),EntityManager.getPlayer().getZ()};
		float[] pv = {EntityManager.getPlayer().getxVel(),EntityManager.getPlayer().getyVel()};
		float[] pj = {}; //dummy for boolean debugs
		float[] cameraPOS = {GameState.getCamera().position.x, GameState.getCamera().position.y};
		float[] md = {EntityManager.getPlayer().getClickDistance()};
		float[] wmc = { MathStuff.generateWorldMouseCoords(camera).x, MathStuff.generateWorldMouseCoords(camera).y};
		float[] health = {EntityManager.getPlayer().getHealth()};
		
		
		console.add(new textWidget(pp, "PlayerPosition(XYZ):"));
		console.add(new textWidget(pv, "PlayerVelocity(XY):"));
		console.add(new textWidget(pj, "Jumping:" + EntityManager.getPlayer().isJumping()));
		console.add(new textWidget(cameraPOS, "Camera Position:"));
		console.add(new textWidget(md, "Mouse Distance:"));
		console.add(new textWidget(wmc, "World Mouse Coords"));
		console.add(new textWidget(health, "Player Health"));
		console.add(new textWidget(pj, "ReadiedItems " + EntityManager.getPlayer().getInvent().toString()));
	}
	
	public void render(SpriteBatch batch) {
		float WINDOW_SIDE = GameState.getCamera().position.x - (Gdx.graphics.getWidth() / 2);
		float WINDOW_TOP = GameState.getCamera().position.y + (Gdx.graphics.getHeight() / 2) - 5;
		
		for (int i = 0; i < console.size(); i++) {
			textWidget widget = console.get(i);
			tm.drawTextNormal(batch, Color.GREEN, widget.getMessage() + " " + Arrays.toString(widget.getNums()), WINDOW_SIDE, (WINDOW_TOP - 10) - ((i * 10) - 10));
		}
		console.clear(); //purge textwidget list to refresh again.
		
		
		
		drawTextEvent(batch, text);
		
		if (text.length() > 0) {
			timer++;
		}
		if (timer >= TIMER_LIMIT) {
			text = "";
			timer = 0;
		}
	}
	
	public void drawTextEvent(SpriteBatch sb, String text) { //draws text event
		tm.drawTextNormal(sb, Color.GREEN, text, GameState.getCamera().position.x - (Gdx.graphics.getWidth() / 2),GameState.getCamera().position.y - (Gdx.graphics.getHeight() / 2) + 15);
	}		
	
}
class textWidget {
	private float[] nums;
	private String message;
	
	textWidget(float[] n, String m) {
		this.nums = n;
		this.message = m;
	}

	public float[] getNums() {
		return nums;
	}

	public void setNums(float[] nums) {
		this.nums = nums;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
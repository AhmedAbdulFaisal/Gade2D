package com.gdx.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Class manages GUI actions in the gameState class, including HUD windows
 * Also holds the player HUD, showing it when necessary.
 */

public class GUI {

	private Window pauseMenu;
	private Window inventory;
	private Window dialogue;
	private Window minimap;
	private Window interact;
	
	public static boolean inv = false; //when inventory is selected
	public static boolean pauseM = false;
	public static boolean paused = false; //when 
	public static boolean hud = false;
	public static boolean convo = false; //when player is interacting
	
	
	public float CameraX, CameraY;
	
	public GUI(int x, int y) {
		
		this.CameraX = x;
		this.CameraY = y;
		
		pauseMenu = new Window(CameraX - (700/2),CameraY + (500/2),700,500, "Paused", "Test Pause Screen :D");
		inventory = new Window(CameraX - (700/2),CameraY - (500/2),700,500, "Inventory", "Test Inventory");
		dialogue = new Window (CameraX - (700/2),100, 700,100, "Dialogue","Test dialogue");
		minimap = new Window ((CameraX * 2) - 100, (CameraY * 2)- 100, 100,100, "Minimap", "Test Minimap");
	}
	
	public void dynamicallyAdjustCoords(float x, float y) {
		this.CameraX = x;
		this.CameraY = y;
	}
	
	public void tick() {
		if (inv) {
			EntityManager.getPlayer().getInvent().tick();
		}
	}
	
	public void render(SpriteBatch batch) {
		
		//NEW CODE
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !paused) {
			paused = true;
			pauseM = true;
			
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && paused) {
			paused = false;
			pauseM = false;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.I) && !paused) {
			paused = true;
			inv = true;
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.I) && paused) {
			paused = false;
			inv = false;
		}
		
		if (pauseM) {
			pauseMenu.setRectPos(CameraX - (700 / 2), CameraY + (500 / 2));
			pauseMenu.render(batch);
		}
		
		if (inv) {
			renderInventory(batch);
			EntityManager.getPlayer().getInvent().render(batch, CameraX - (((Assets.inv.getRegionWidth() / 2) + 12)),CameraY + ((Assets.inv.getRegionHeight() / 2) - 12));
		}
	}
	
	public void renderInventory(SpriteBatch batch) {
		batch.draw(Assets.inv,CameraX  - ((Assets.inv.getRegionWidth())),CameraY  - ((Assets.inv.getRegionHeight())), Assets.inv.getRegionWidth() * 2, Assets.inv.getRegionHeight() * 2);
	}
	
}

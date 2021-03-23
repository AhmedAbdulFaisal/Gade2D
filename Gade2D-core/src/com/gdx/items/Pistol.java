package com.gdx.items;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity.states;
import com.gdx.entity.PBullet;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.EntityManager;

public class Pistol extends Item{
	
	public int ammunition; //value of 10mm ammo given to player in pistols scattered around the world
	public int bulletTime;
	public final int READY_TIME = 50;
	private Random r;
	public Pistol(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.GUN;
		
		id = 005;
		
		itemName = "10MM AF-02 Pistol";
		itemDesc = " Manufactured in the early 2480s, the AF-02 is a suitable weapon from explorers to soldiers and is usefull in a variety of combat situations. Non automatic, up to 5 10mm bullets per round, AP rounds ONLY";
		inventoryTexture = Assets.pistol;
		bulletTime = 0;
		
		bounds = new Rectangle(xPos,yPos,(WIDTH * 3) * 2,(HEIGHT * 2) * 2);
	}
	
	public void load(PistolMag newMag) {
		ammunition = 0; //clear ammo cache
		ammunition += newMag.getAMMO(); //add new ammo
	}


	public void clickedON() {
		if (ifObjectClicked()) {
			setDead(true);
		}
	}
	
	public void tick() {
		bulletTime++;
		
		if (bulletTime == READY_TIME) {
			bulletTime = 1000;
		}
		
		//System.out.println(ammunition);
		followMouse();
		addInventoryCollisions();
	}


	public void rendertoInventory(SpriteBatch batch, int x, int y) {
		batch.draw(Assets.pistol, x, y, bounds.width, bounds.height);
	}

	public void renderNormally(SpriteBatch batch) {
		batch.draw(Assets.pistol_world, x, y, FDW, FDH);
	}


	@Override
	public void render(SpriteBatch batch) {
		if (!pickedUp) {
			renderNormally(batch);
		}
		
	}

	//Code for whenever the player uses. When a player has an object in their inventory, they can press left click to use it 
	public String printBulletNum() {
		String ammocount = "";
		for (int i = 0; i < ammunition; i++) {
			ammocount += "|";
		}
		return ammocount;
	}
	
	public float deviation() {
		r = new Random();
		float ran = r.nextInt(5);
		return ran;
	}
	@Override
	public void use(float xPos, float yPos, float dirLength, float playerX, float playerY) {
		if ((bulletTime >= READY_TIME) && (ammunition != 0)) {
			
			EntityManager.getEntityList().add(new PBullet(playerX + (float) (dirLength * 0.10), playerY + (float) (dirLength * 0.10), 14,(xPos/(dirLength  + deviation())), (yPos/(dirLength  + deviation()))));
			bulletTime = 0;
			ammunition--;
			DebugHUD.setText("Ammo: " + printBulletNum());
		}else if (ammunition == 0) {
			DebugHUD.setText("You're out of ammunition");
		}
		
		
	}

	@Override
	public void use(float x, float y) {
		// TODO Auto-generated method stub
		
	}
}

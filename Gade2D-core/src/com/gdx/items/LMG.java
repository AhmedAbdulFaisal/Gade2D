package com.gdx.items;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.PBullet;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.EntityManager;

public class LMG extends Item{

	public int ammunition; // round size tends to be 100 bullets. So, on the ammocounter
	//it'll display a "|" for every 10 rounds
	public int bulletTime;
	public final int READY_TIME = 10;
	private Random r;
	
	public LMG(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.GUN;
		id = 007;
		
		itemName = "7.62MM AR-01 Light Machine Gun"; 
		itemDesc = "A standard LMG adopted by various nations after the 2100s, the AR-01 is a imitation of an ancient Japanese LMG from the 20th century. It is a common weapon among soldiers in most armies, and must be used prone";
		inventoryTexture = Assets.lmg;
		bulletTime = 0;
		
		bounds = new Rectangle(xPos, yPos,(WIDTH * 3) * 2,(HEIGHT * 2) * 2);
	}
	public void load(LMGAmmo newRound) {
		ammunition = 0;
		ammunition += newRound.getAMMO();
	}
	
	@Override
	public void tick() {
		bulletTime++;
		
		if (bulletTime == READY_TIME) {
			bulletTime = 1000;
		}
		
		followMouse();
		addInventoryCollisions();
		
	}
	
	public void rendertoInventory(SpriteBatch batch, int x, int y) {
		batch.draw(Assets.lmg, x, y, bounds.width, bounds.height);
	}

	public void renderNormally(SpriteBatch batch) {
		batch.draw(Assets.black, x, y, FDW, FDH);
	}

	public String printBulletNum() {
		String ammocount = "";
		for (int i = 0; i < (int) (ammunition / 10); i++) {
			ammocount += "|";
		}
		return ammocount;
	}
	
	public float deviation() {
		r = new Random();
		float ran = r.nextInt(100);
		return ran;
	}
	
	public void use(float xPos, float yPos, float dirLength, float playerX, float playerY) {
		if ((bulletTime >= READY_TIME) && (ammunition != 0)) {
			
			EntityManager.getEntityList().add(new PBullet(playerX + (float) (dirLength * 0.10), playerY + (float) (dirLength * 0.10), 2,(xPos/(dirLength  + deviation())), (yPos/(dirLength  + deviation()))));
			bulletTime = 0;
			ammunition--;
			DebugHUD.setText("Ammo: " + printBulletNum());
		}else if (ammunition == 0) {
			DebugHUD.setText("You're out of ammunition");
		}
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		if (!pickedUp) {
			renderNormally(batch);
		}
		
	}
}

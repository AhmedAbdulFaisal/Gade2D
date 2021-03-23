package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.items.Item.item_states;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.Inventory;
import com.gdx.utility.Assets;

public class LMGAmmo extends Item{

	public final int AMMO = 100; 
	
	public LMGAmmo(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.AMMO;
		id = 010;
		itemName = "7.62MM LMG (100 rounds)";
		itemDesc = "7.62MM LMG with 100 bullets inside"; 
		inventoryTexture = Assets.lmgammo;
		
		bounds = new Rectangle(xPos,yPos,(WIDTH * 3) * 2,(HEIGHT * 2) * 2);
	}

	public void use(float x, float y, Inventory i) {
		int lmg = i.findItem(007); 
		LMG newG = (LMG) i.getReadyslots().get(lmg);
		newG.load(this);
		i.getReadyslots().set(lmg, newG);
		i.getReadyslots().remove(this);
		DebugHUD.setText("You hastily load a box (" + AMMO + " ROUNDS) into your LMG");
		
	}
	
	public int getAMMO() {
		return AMMO;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}

package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.utility.Assets;
import com.gdx.utility.Inventory;

public class DataReader extends Item{

	public DataReader(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.GEAR;
		id = 013;
		
		itemName = "Data Reader";
		itemDesc = "A PDA. It appears to read magnetic disks of some kind";
		inventoryTexture = Assets.datareader;
		
		bounds = new Rectangle(xPos, yPos, (WIDTH * 3) * 2, (HEIGHT * 2) * 2);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	public void use(Inventory i) {
		
	}

}

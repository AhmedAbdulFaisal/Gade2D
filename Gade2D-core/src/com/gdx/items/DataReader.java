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
		
		itemName = "C-Tech DataReader";
		itemDesc = "(SPECS: 1Mhz, 8KB of RAM)A Suitable information reader that takes disks as input medium and outputs readable text only. With the DataReader, you can read any disk log you come across, but can't transmit data disks. Usefull for any investigator on the scene.";
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

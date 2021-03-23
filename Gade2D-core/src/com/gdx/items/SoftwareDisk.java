package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.Inventory;

public class SoftwareDisk extends Item{

	public String text;  
	
	public SoftwareDisk(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.MEDIA;
		id = 011;
		itemDesc = "Multi-Purpose Data Disk for using programs.";
		itemName = "High Density Data disk"; 
	}

	public void use(float xPos, float yPos, Inventory invent) {
		if (invent.findItemInInv(012) != -1) {
			DebugHUD.setText("You put the disk in and execute the program");
		}else {
			DebugHUD.setText("You need a computer to do this action");
		}
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

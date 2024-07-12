package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.utility.Assets;
import com.gdx.utility.Inventory;

public class Computer extends Item{

	public Computer(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.GEAR;
		id = 012;
		itemName = "A Computer";
		itemDesc = "A luggable computer. It doesn't seem to require a plug, but is rather primitive";
		inventoryTexture = Assets.computer;
		
		bounds = new Rectangle(xPos, yPos, (WIDTH * 3) * 2, (HEIGHT * 2) * 2);
		price = 2500; //computers can only be brought at electronics stores
		// TODO Auto-generated constructor stub
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

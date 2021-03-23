package com.gdx.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.items.Item;
import com.gdx.utility.Assets;

public class LootableBox extends StaticEntity{

	private boolean opened;
	private ArrayList<Item> storage;
	
	
	public LootableBox(float xPos, float yPos) {
		super(xPos, yPos);
		opened = false;
		classif = states.QUEST;
		health = 10;
		//this.storage = lootlist;
		id = 007;
		bounds = new Rectangle();
		bounds.set(xPos, yPos, FDW, FDH);
		desc = "You decide to open the box...";
	}

	@Override
	public void tick() {
		updateBounds();
		getClickMessage(); 
		if (ifObjectClicked()) {
			opened = true;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (!opened) {
			batch.draw(Assets.boxC, x, y, FDW, FDH);
		}else if (opened) {
			batch.draw(Assets.boxO, x, y, FDW, FDH);
		}
	}

}

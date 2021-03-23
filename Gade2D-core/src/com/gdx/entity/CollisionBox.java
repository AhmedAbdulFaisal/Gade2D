package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity.states;

public class CollisionBox extends StaticEntity{

	public CollisionBox(float xPos, float yPos, float width, float height) {
		super(xPos, yPos);
		classif = states.QUEST;
		id = 2; bounds = new Rectangle(); 
		depth = 0; full3d = false; bounds.set(xPos, yPos, width, height);
		desc = "You see a wall.";
	}

	@Override
	public void tick() {
		updateBounds();
		getClickMessage(); 
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
}

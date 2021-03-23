package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity;

/* Class is a cube which has 3d collision data
 * 
 */

public class Cube extends StaticEntity{

	public Cube(float xPos, float yPos) {
		super(xPos, yPos);
		
		bounds = new Rectangle(0,0,32,32);
		
		id = 1; depth = 100; full3d = true;
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

package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity.states;
import com.gdx.utility.Assets;
import com.gdx.utility.Graphics;

public class Crate extends StaticEntity{

	/* Class creates a textured cube with it's own collision box
	 * 
	 */
	
	public Crate(float xPos, float yPos, int x) {
		super(xPos, yPos); 
		classif = states.WOOD;
		id = 1; bounds = new Rectangle();
		teritiary = x; full3d = true; bounds.set(x, y, x, x);
		desc = "You see a sturdy wooden crate. It's not openable, from the looks of it.";
	}

	@Override
	public void tick() {
		updateBounds();
		getClickMessage(); 
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.draw(Assets.crate_top,getBounds().x,getBounds().y, getBounds().width, getBounds().height); //bottom
		batch.draw(Assets.crate_top,getBounds().x,calculateGraphicZ((int) getTeritiary()), getBounds().width, getBounds().height); //top
		batch.draw(Assets.crate_side,getBounds().x, calculateGraphicZ((int) getTeritiary()), getBounds().width, -calculateZ( getTeritiary())); //front
		
	}

}

package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.items.SoftwareDisk;



public class PanelMK1 extends StaticEntity{

	public boolean activated;
	public final float HACKDISTANCE = 128; 
	
	public PanelMK1(float xPos, float yPos, int tag) { //this panel opens all items with the tag
		super(xPos, yPos);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public void activate(SoftwareDisk SD) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}

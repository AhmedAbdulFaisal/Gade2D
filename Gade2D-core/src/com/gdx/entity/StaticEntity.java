package com.gdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.utility.DebugHUD;

public abstract class StaticEntity extends Entity{
	
	protected String desc; 
	protected int tag;
	
	public StaticEntity(float xPos, float yPos) {
		super(xPos, yPos);
		desc = "You see the raw form of a static object. Lacking of any shape or composition, you decide not to touch it...";
	}

	@Override
	public abstract void tick();

	@Override
	public abstract void render(SpriteBatch batch);
	
	public boolean ifObjectClicked() {
		if (ifMouseTouches()) {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void getClickMessage() {
		if (ifObjectClicked()) {
			DebugHUD.setText(desc);
		}
	}
}

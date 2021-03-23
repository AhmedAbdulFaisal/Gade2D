package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.gdx.entity.Entity.states;
import com.gdx.utility.Assets;
import com.gdx.utility.Graphics;

public class Pillar extends StaticEntity{

	public TiledDrawable brick;
	
	public Pillar(float xPos, float yPos, int height) {
		super(xPos, yPos);
		classif = states.STONE;
		brick = new TiledDrawable(Assets.stonebrick);
		id = 4; bounds = new Rectangle();
		teritiary = height; full3d = true; bounds.set(x, y, FDW, FDH);
		desc = "You see a large stone pillar"; 
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		updateBounds();
		getClickMessage(); 
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.stonetile,getBounds().x,getBounds().y, getBounds().width, getBounds().height); //bottom
		Graphics.setBrightness(batch, 1.8f);
		batch.draw(Assets.stonetile,getBounds().x,calculateGraphicZ((int) getTeritiary()), getBounds().width, getBounds().height); //top
		Graphics.setBrightness(batch, 1.0f);
		//batch.draw(Assets.stonebrick,getBounds().x, calculateGraphicZ((int) getDepth()), getBounds().width, -calculateZ(getDepth())); //front
		//brick.draw(batch, getBounds().x, calculateGraphicZ((int) getDepth()), getBounds().width, -calculateZ(getDepth()));
		Graphics.repeatTextureVertical(batch, Assets.stonebrick, teritiary, getBounds().x, calculateGraphicZ((int) getTeritiary()) - (getTeritiary() - 32));
		
	}

}

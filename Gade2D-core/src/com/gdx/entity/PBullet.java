package com.gdx.entity;

import java.awt.geom.Line2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity;
import com.gdx.entity.Entity.states;
import com.gdx.utility.Assets;
import com.gdx.utility.EntityManager;

//pBullet refers to the Bullet entity that is only useable by the player.
public class PBullet extends Creature{
	
	
	public float xVelocity, yVelocity;
	
	public PBullet(float xPos, float yPos,float zPos, float f, float g) {
		super(xPos, yPos);
		classif = states.PFIRE;
		id = 006;
		this.xVelocity = f;
		this.yVelocity = g;
		bounds = new Rectangle(x + 12,y, 4, 4);
		z = zPos;
		//bounds = new Rectangle();
		//bounds.set(x + (FDW / 4), y, FDW / 2, (FDH / 2));
	}

	@Override
	public void tick() {
		x+= (xVelocity * 4);
		y+= (yVelocity * 4);
		updateBounds();
		BulletDamageTable();
		deleteDead(); 
	}
	
	public void updateBounds() {
		bounds.set(x + 12,y, 4, 4);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//batch.draw(Assets.FX, x, y, FDW / 2, FDH / 2, FDW, FDW, 1, 1, Radians, 0, 0, 16, 16, false, false);
		batch.draw(Assets.bulletShot, x, y, FDW, FDH);
	}

}

package com.gdx.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.entity.Entity;

public class Graphics {

	public static Color c;
	
	public static void transparency(SpriteBatch batch) {
		c = batch.getColor();
		batch.setColor(c.r ,c.b,c.g,0.5f);
	}
	
	public static void opaque(SpriteBatch batch) {
		c = batch.getColor();
		batch.setColor(c.r ,c.b,c.g,1f);
	}
	
	public static void setBrightness(SpriteBatch batch, float a) {
		c = batch.getColor();
		batch.setColor(a, a, a, 1F); //0.5F, 0.5F, 0.5F;
	}
	
	public static void repeatTextureVertical(SpriteBatch batch,TextureRegion region, int distance, float xPos, float yPos){
		for (int i = 0; i < distance; i+=32) {
			batch.draw(region, xPos, yPos + i, Entity.FDW, -Entity.FDH);
		}
	}
}

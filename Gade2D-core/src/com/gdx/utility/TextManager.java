package com.gdx.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class TextManager {

	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;
		
	BitmapFont font12;
	
	public TextManager(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/TANDY.ttf")); 
		parameter = new FreeTypeFontParameter();
		parameter.size = size;
		font12 = generator.generateFont(parameter);
	}
	
	public void setSize(int size) {
		parameter.size = size;
	}
	
	public void drawTextWrapped(SpriteBatch batch, Color c, String text,float xPos,float yPos, int targetWidth, int halign) {
		font12.setColor(c);
		font12.draw(batch, text, xPos, yPos, targetWidth, halign, true);
	}
	
	public void drawTextNormal(SpriteBatch batch, Color c, String text,float xPos,float yPos) {
		font12.setColor(c);
		font12.draw(batch, text, xPos, yPos);
	}
	
	public void drawTextCentered(SpriteBatch batch, Color c, String text, float xPos, float yPos) {
		font12.setColor(c);
		font12.draw(batch, text, xPos - ((text.length() * font12.getSpaceXadvance()) / 2), yPos);
	}
	
	public void dispose() {
		
	}
}
